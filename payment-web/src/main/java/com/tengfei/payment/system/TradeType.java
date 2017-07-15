package com.tengfei.payment.system;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 订单
 */
public enum TradeType {
	ALIPAYPC("1", "支付宝pc"), ALIPAYWAP("2", "支付宝wap"), WECHATPC("3", "微信pc"),WECHATWAP("4", "微信wap"),CARDPC("5", "网银pc"),CARDWAP("6", "网银wap");
	
	private String code;
	
	private String name;
	
	private TradeType(String code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public String code() {
		return this.code;
	}
	
	public static Map<String, String> kv() {
		TradeType[] userTypes = TradeType.values();
		Map<String, String> retMap = new LinkedHashMap<String, String>(userTypes.length);
		for(TradeType userType : userTypes) {
			retMap.put(userType.code, userType.name);
		}
		
		return retMap;
	}
	
	public static String toName(String code) {
		TradeType[] userTypes = TradeType.values();
		for(TradeType userType : userTypes) {
			if(userType.code.equals(code)) return userType.name;
		}
		
		return "";
	}
	
}
