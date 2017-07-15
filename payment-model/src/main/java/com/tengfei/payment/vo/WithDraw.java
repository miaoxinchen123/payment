package com.tengfei.payment.vo;

import java.util.Date;

/**
 * 订单表
 * @author miaoxin.chen
 * @date   2017年4月07日
 *
 */
public class WithDraw {
    private String id;
    
	private String userId;
    
    private Float money;
    
	private String cardId;
	
	private String cardNo;
    
    private Float platformCharge;
    
    private String Status;
    
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


	public Float getPlatformCharge() {
		return platformCharge;
	}

	public void setPlatformCharge(Float platformCharge) {
		this.platformCharge = platformCharge;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
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

	public Float getMoney() {
		return money;
	}

	public void setMoney(Float money) {
		this.money = money;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
    
}
