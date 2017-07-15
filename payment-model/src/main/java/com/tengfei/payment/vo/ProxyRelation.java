package com.tengfei.payment.vo;

import java.util.Date;

/**
 * 代理商户关系表
 * @author miaoxin.chen
 * @date   2017年4月07日
 *
 */
public class ProxyRelation {
    private String id;
    
	private String userId;
    
    private String merchantId;
    
    private Float rate;
    
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


		public String getMerchantId() {
			return merchantId;
		}

		public void setMerchantId(String merchantId) {
			this.merchantId = merchantId;
		}

		public Float getRate() {
			return rate;
		}

		public void setRate(Float rate) {
			this.rate = rate;
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
