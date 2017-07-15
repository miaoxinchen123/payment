package com.tengfei.payment.sms;

public interface CodeCache {

	public String getCode(String key);
	
	public void putCode(String key, String code);
	
	public String makeAndPutCode(String key);
	
	public void removeCode(String key);
	
}
