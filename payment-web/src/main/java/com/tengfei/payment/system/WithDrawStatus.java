package com.tengfei.payment.system;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 取现订单
 */
public enum WithDrawStatus {
	SUCCESS("1", "成功"), DEALING("2", "处理中");
	
	private String code;
	
	private String name;
	
	private WithDrawStatus(String code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public String code() {
		return this.code;
	}
	
	public static Map<String, String> kv() {
		WithDrawStatus[] userTypes = WithDrawStatus.values();
		Map<String, String> retMap = new LinkedHashMap<String, String>(userTypes.length);
		for(WithDrawStatus userType : userTypes) {
			retMap.put(userType.code, userType.name);
		}
		
		return retMap;
	}
	
	public static String toName(String code) {
		WithDrawStatus[] userTypes = WithDrawStatus.values();
		for(WithDrawStatus userType : userTypes) {
			if(userType.code.equals(code)) return userType.name;
		}
		
		return "";
	}
	
}
