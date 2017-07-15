package com.tengfei.payment.util;

import java.util.Arrays;

import org.springframework.util.StringUtils;

import com.tengfei.payment.dto.MsgVo;
import com.tengfei.payment.sms.CodeCache;
import com.tengfei.payment.sms.JsonRestClient;
import com.tengfei.payment.sms.impl.DefaultCodeCacheImpl;

@SuppressWarnings("all")
public class MsgUtil {
	private static JsonRestClient client = new JsonRestClient();
	private static CodeCache codeCache = SpringUtils.getApplicationContext().getBean("codeCache", CodeCache.class);
	
	public static MsgVo sendCode(String phone) {
		MsgVo retVal = new MsgVo();
		String code;
		synchronized (MsgUtil.class) {
			code = codeCache.getCode(phone);
			if(StringUtils.isEmpty(code)) {
				code = codeCache.makeAndPutCode(phone);
			}
		}
		retVal.setObj(code);
		client.sendCode(phone, code);

		return retVal;
	}
	
	public static boolean notify(String phone, String[] params) {
		if(null == params || 0 == params.length) return false;
		
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < params.length; i++) {
			if(0 != i) sb.append(",");
			sb.append(params[i]);
		}
		client.notify(phone, sb.toString());
		
		return true;
	}
	
	public static boolean validateCode(String phone, String code) {
        if(StringUtils.isEmpty(code)) return false;
        
        return code.equals(codeCache.getCode(phone));
	}
	
	public static void removeCode(String phone) {
		codeCache.removeCode(phone);
	}
	
}
