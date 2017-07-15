package com.tengfei.payment.system;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 用户类型
 */
public enum UserType {
	SYS("1", "系统"), PROXY("2", "代理"), MERCHANT("3", "商户");
	
	private String code;
	
	private String name;
	
	private UserType(String code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public String code() {
		return this.code;
	}
	
	public String val() {
		return this.name;
	}
	
	public static Map<String, String> kv() {
		UserType[] userTypes = UserType.values();
		Map<String, String> retMap = new LinkedHashMap<String, String>(userTypes.length);
		for(UserType userType : userTypes) {
			retMap.put(userType.code, userType.name);
		}
		
		return retMap;
	}
	
	public static String toName(String code) {
		UserType[] userTypes = UserType.values();
		for(UserType userType : userTypes) {
			if(userType.code.equals(code)) return userType.name;
		}
		
		return "";
	}
	
	public static UserType fromCode(String code) {
		UserType[] userTypes = UserType.values();
		for(UserType ut : userTypes) {
			if(ut.code.equals(code)) return ut;
		}
		
		return null;
	}
	
}
