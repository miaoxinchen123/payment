package com.tengfei.payment.dto;

public enum Status {
	
	OK("OK"), ERROR("ERROR");
	
	private String code;
	
	private Status(String code) {
		this.code = code;
	}
	
	public String code() {
		return this.code;
	}
	
}
