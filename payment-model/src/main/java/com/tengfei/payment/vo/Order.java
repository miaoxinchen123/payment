package com.tengfei.payment.vo;

import java.util.Date;

/**
 * 订单表
 * @author miaoxin.chen
 * @date   2017年4月07日
 *
 */
public class Order {
    private String id;
    
	private String userId;
    
    private String businessId;
    
    private Date businessTime;
    
    private Float businessMoney;
    
    private String businessChannel;
    
    private Float platformCharge;
    
    private String status;

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

		public String getBusinessId() {
			return businessId;
		}

		public void setBusinessId(String businessId) {
			this.businessId = businessId;
		}

		public Date getBusinessTime() {
			return businessTime;
		}

		public void setBusinessTime(Date businessTime) {
			this.businessTime = businessTime;
		}

		public Float getBusinessMoney() {
			return businessMoney;
		}

		public void setBusinessMoney(Float businessMoney) {
			this.businessMoney = businessMoney;
		}


		public Float getPlatformCharge() {
			return platformCharge;
		}

		public void setPlatformCharge(Float platformCharge) {
			this.platformCharge = platformCharge;
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

		public String getBusinessChannel() {
			return businessChannel;
		}

		public void setBusinessChannel(String businessChannel) {
			this.businessChannel = businessChannel;
		}
		
		  
	    public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}
    
}
