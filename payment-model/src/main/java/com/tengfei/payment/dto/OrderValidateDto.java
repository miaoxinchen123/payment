package com.tengfei.payment.dto;

public class OrderValidateDto {
	
	public boolean userExist;
	
	public boolean orderExist;
	
	public boolean paramRight;

	public boolean isUserExist() {
		return userExist;
	}

	public void setUserExist(boolean userExist) {
		this.userExist = userExist;
	}

	public boolean isOrderExist() {
		return orderExist;
	}

	public void setOrderExist(boolean orderExist) {
		this.orderExist = orderExist;
	}

	public boolean isParamRight() {
		return paramRight;
	}

	public void setParamRight(boolean paramRight) {
		this.paramRight = paramRight;
	}

	
}
