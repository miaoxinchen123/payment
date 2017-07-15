package com.tengfei.payment.system;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 用户状态
 */
public enum UserStatu {
	OK("1", "已认证"), WAIT_FOR_AUDIT("2", "待审核"), ABNORMAL("3", "账户异常");
	
	private String code;
	
	private String name;
	
	private UserStatu(String code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public String code() {
		return this.code;
	}
	
	public static Map<String, String> kv() {
		UserStatu[] userSts = UserStatu.values();
		Map<String, String> retMap = new LinkedHashMap<String, String>(userSts.length);
		for(UserStatu userSt : userSts) {
			retMap.put(userSt.code, userSt.name);
		}
		
		return retMap;
	}
	
	public static String toName(String code) {
		UserStatu[] userSts = UserStatu.values();
		for(UserStatu userSt : userSts) {
			if(userSt.code.equals(code)) return userSt.name;
		}
		
		return "";
	}
	
	public static UserStatu fromCode(String code) {
		UserStatu[] userSts = UserStatu.values();
		for(UserStatu userSt : userSts) {
			if(userSt.code.equals(code)) return userSt;
		}
		
		return null;
	}
	
}
