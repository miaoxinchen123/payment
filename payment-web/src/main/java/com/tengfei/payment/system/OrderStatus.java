package com.tengfei.payment.system;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 订单
 */
public enum OrderStatus {
	SUCCESS("1", "成功"), DEALING("2", "处理中"), FAIL("3", "失败");
	
	private String code;
	
	private String name;
	
	private OrderStatus(String code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public String code() {
		return this.code;
	}
	
	public static Map<String, String> kv() {
		OrderStatus[] userTypes = OrderStatus.values();
		Map<String, String> retMap = new LinkedHashMap<String, String>(userTypes.length);
		for(OrderStatus userType : userTypes) {
			retMap.put(userType.code, userType.name);
		}
		
		return retMap;
	}
	
	public static String toName(String code) {
		OrderStatus[] userTypes = OrderStatus.values();
		for(OrderStatus userType : userTypes) {
			if(userType.code.equals(code)) return userType.name;
		}
		
		return "";
	}
	
}
