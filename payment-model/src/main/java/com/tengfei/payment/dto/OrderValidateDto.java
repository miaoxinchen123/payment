package com.tengfei.payment.dto;

public class OrderValidateDto {
	
	public boolean isUserExist;
	
	public boolean isOrderExist;
	
	public boolean isParamRight;

	public boolean isUserExist() {
		return isUserExist;
	}

	public void setUserExist(boolean isUserExist) {
		this.isUserExist = isUserExist;
	}

	public boolean isOrderExist() {
		return isOrderExist;
	}

	public void setOrderExist(boolean isOrderExist) {
		this.isOrderExist = isOrderExist;
	}

	public boolean isParamRight() {
		return isParamRight;
	}

	public void setParamRight(boolean isParamRight) {
		this.isParamRight = isParamRight;
	}
	
}
