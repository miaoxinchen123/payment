package com.tengfei.payment.vo;

import java.util.Date;

public class UserRate {
	
    private String id;
    
	private String userId;
	
	private float alipayPc;
	
	private float alipayWap;
	
	private float cardPc;
	
	private float cardWap;
	
	private float wechatPc;
	
	private float wechatWap;
	
	private Date createDate;
	    
    private Date updateDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public float getAlipayPc() {
		return alipayPc;
	}

	public void setAlipayPc(float alipayPc) {
		this.alipayPc = alipayPc;
	}

	public float getAlipayWap() {
		return alipayWap;
	}

	public void setAlipayWap(float alipayWap) {
		this.alipayWap = alipayWap;
	}

	public float getCardPc() {
		return cardPc;
	}

	public void setCardPc(float cardPc) {
		this.cardPc = cardPc;
	}

	public float getCardWap() {
		return cardWap;
	}

	public void setCardWap(float cardWap) {
		this.cardWap = cardWap;
	}

	public float getWechatPc() {
		return wechatPc;
	}

	public void setWechatPc(float wechatPc) {
		this.wechatPc = wechatPc;
	}

	public float getWechatWap() {
		return wechatWap;
	}

	public void setWechatWap(float wechatWap) {
		this.wechatWap = wechatWap;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}
