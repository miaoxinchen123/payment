package com.tengfei.payment.system;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 到账类型
 */
public enum BalanceType {
	T0("T0", "T0"), T1("T1", "T1");
	
	private String code;
	
	private String name;
	
	private BalanceType(String code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public String code() {
		return this.code;
	}
	
	public static Map<String, String> kv() {
		BalanceType[] balanceTypes = BalanceType.values();
		Map<String, String> retMap = new LinkedHashMap<String, String>(balanceTypes.length);
		for(BalanceType balanceType : balanceTypes) {
			retMap.put(balanceType.code, balanceType.name);
		}
		
		return retMap;
	}
	
	public static String toName(String code) {
		BalanceType[] balanceTypes = BalanceType.values();
		for(BalanceType balanceType : balanceTypes) {
			if(balanceType.code.equals(code)) return balanceType.name;
		}
		
		return "";
	}
	
}
