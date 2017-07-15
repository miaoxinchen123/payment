package com.tengfei.payment.sms;

@SuppressWarnings("all")
public class SMSConfig {
	
	private static String server = "api.ucpaas.com";
	
	private static String version = "2014-06-30";
	
	private static String accountSid = "e7fae0d0d7c20ca97a4f51167d06d26a";
	
	private static String authToken = "59b2a82ab50fb8f65ffd5408f1d87384";
	
	private static String appId;
	
	private static String codeTemplateId;
	
	private static String notifyTemplateId;

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getAccountSid() {
		return accountSid;
	}

	public void setAccountSid(String accountSid) {
		this.accountSid = accountSid;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getCodeTemplateId() {
		return codeTemplateId;
	}

	public void setCodeTemplateId(String codeTemplateId) {
		this.codeTemplateId = codeTemplateId;
	}

	public String getNotifyTemplateId() {
		return notifyTemplateId;
	}

	public void setNotifyTemplateId(String notifyTemplateId) {
		this.notifyTemplateId = notifyTemplateId;
	}
	
}
