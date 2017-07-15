package com.tengfei.payment.dto;

public class UpdatePhoneDto {

	private String id;
	
	private String verifyCode;
	
	private String updatePhone;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getUpdatePhone() {
		return updatePhone;
	}

	public void setUpdatePhone(String updatePhone) {
		this.updatePhone = updatePhone;
	}
	
}
