package com.tengfei.payment.sms;

import java.io.ByteArrayInputStream;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.tengfei.payment.sms.util.DateUtil;
import com.tengfei.payment.sms.util.EncryptUtil;

@SuppressWarnings("all")
public class JsonRestClient {

	private SMSConfig smsConfig = new SMSConfig();
	
	public String sendCode(String phone, String code) {
		return templateSMS(smsConfig.getCodeTemplateId(), phone, code);
	}
	
	public String notify(String phone, String param) {
		return templateSMS(smsConfig.getNotifyTemplateId(), phone, param);
	}
	
	public String templateSMS(String templateId, String phone, String param) {
		String result = "";
		DefaultHttpClient httpclient = new DefaultHttpClient();
		try {
			EncryptUtil encryptUtil = new EncryptUtil();
			String timestamp = DateUtil.dateToStr(new Date(), DateUtil.DATE_TIME_NO_SLASH);// 时间戳
			String signature =getSignature(smsConfig.getAccountSid(), smsConfig.getAuthToken(), timestamp, encryptUtil);
			String url = getStringBuffer().append("/").append(smsConfig.getVersion())
					.append("/Accounts/").append(smsConfig.getAccountSid())
					.append("/Messages/templateSMS")
					.append("?sig=").append(signature).toString();
			TemplateSMS templateSMS=new TemplateSMS();
			templateSMS.setAppId(smsConfig.getAppId());
			templateSMS.setTemplateId(templateId);
			templateSMS.setTo(phone);
			templateSMS.setParam(param);
			String body = JSONObject.toJSONString(templateSMS);
			body="{\"templateSMS\":"+body+"}";
			HttpResponse response=post("application/json", smsConfig.getAccountSid(), smsConfig.getAuthToken(), timestamp, url, httpclient, encryptUtil, body);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity, "UTF-8");
			}
			EntityUtils.consume(entity);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {// 关闭连接
		    httpclient.getConnectionManager().shutdown();
		}
		
		return result;
	}
	
	protected HttpResponse post(String cType,String accountSid,String authToken,String timestamp,String url,DefaultHttpClient httpclient,EncryptUtil encryptUtil,String body) throws Exception{
		HttpPost httppost = new HttpPost(url);
		httppost.setHeader("Accept", cType);
		httppost.setHeader("Content-Type", cType+";charset=utf-8");
		String src = accountSid + ":" + timestamp;
		String auth = encryptUtil.base64Encoder(src);
		httppost.setHeader("Authorization", auth);
		BasicHttpEntity requestBody = new BasicHttpEntity();
        requestBody.setContent(new ByteArrayInputStream(body.getBytes("UTF-8")));
        requestBody.setContentLength(body.getBytes("UTF-8").length);
        httppost.setEntity(requestBody);
        // 执行客户端请求
		HttpResponse response = httpclient.execute(httppost);
		return response;
	}
	
	protected String getSignature(String accountSid, String authToken,String timestamp,EncryptUtil encryptUtil) throws Exception{
		String sig = accountSid + authToken + timestamp;
		String signature = encryptUtil.md5Digest(sig);
		return signature;
	}
	
	protected StringBuffer getStringBuffer() {
		StringBuffer sb = new StringBuffer("https://");
		sb.append(smsConfig.getServer());
		return sb;
	}
	
}
