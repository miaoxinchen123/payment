package com.tengfei.payment.vo;

import java.util.Date;

/**
 * 用户银行卡信息
 * @author miaoxin.chen
 * @date   2017年4月07日
 *
 */
public class UserCard {
	
    private String id;
    
	private String userId;
    
    private Card bank;
    
    private String province;
    
    private String city;
    
    private String county;
    
    private String banckName;
    
    private String cardName;
    
    private String cardNo;
    
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

		public Card getBank() {
			return bank;
		}

		public void setBank(Card bank) {
			this.bank = bank;
		}

		public String getProvince() {
			return province;
		}

		public void setProvince(String province) {
			this.province = province;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getCounty() {
			return county;
		}

		public void setCounty(String county) {
			this.county = county;
		}

		public String getBanckName() {
			return banckName;
		}

		public void setBanckName(String banckName) {
			this.banckName = banckName;
		}

		public String getCardName() {
			return cardName;
		}

		public void setCardName(String cardName) {
			this.cardName = cardName;
		}

		public String getCardNo() {
			return cardNo;
		}

		public void setCardNo(String cardNo) {
			this.cardNo = cardNo;
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
