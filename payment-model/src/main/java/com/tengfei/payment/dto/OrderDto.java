package com.tengfei.payment.dto;

public class OrderDto {
	
	public float money;
	
	public int count;
	
	/**商户订单统计数据**/
	public float successMoney;
	
	public int successCount;
	
	public float dealingMoney;
	
	public int dealingCount;
	
    public float failMoney;
	
	public int failCount;
	
	public float yestodaySuccessMoney;
	
	public float sevenSuccessMoney;
	
	public float totalSuccessMoney;
	
	/**代理订单统计数据**/
	public float proxySuccessMoney;
	
	public int proxySuccessCount;
	
	public float proxyExpectMoney;
	
	public float proxyYestodayMoney;
	
	public float proxySevenMoney;
	
	public float proxyTotalMoney;

	public float getMoney() {
		return money;
	}

	public void setMoney(float money) {
		this.money = money;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public float getSuccessMoney() {
		return successMoney;
	}

	public void setSuccessMoney(float successMoney) {
		this.successMoney = successMoney;
	}

	public int getSuccessCount() {
		return successCount;
	}

	public void setSuccessCount(int successCount) {
		this.successCount = successCount;
	}

	public float getDealingMoney() {
		return dealingMoney;
	}

	public void setDealingMoney(float dealingMoney) {
		this.dealingMoney = dealingMoney;
	}

	public int getDealingCount() {
		return dealingCount;
	}

	public void setDealingCount(int dealingCount) {
		this.dealingCount = dealingCount;
	}

	public float getFailMoney() {
		return failMoney;
	}

	public void setFailMoney(float failMoney) {
		this.failMoney = failMoney;
	}

	public int getFailCount() {
		return failCount;
	}

	public void setFailCount(int failCount) {
		this.failCount = failCount;
	}

	public float getYestodaySuccessMoney() {
		return yestodaySuccessMoney;
	}

	public void setYestodaySuccessMoney(float yestodaySuccessMoney) {
		this.yestodaySuccessMoney = yestodaySuccessMoney;
	}

	public float getSevenSuccessMoney() {
		return sevenSuccessMoney;
	}

	public void setSevenSuccessMoney(float sevenSuccessMoney) {
		this.sevenSuccessMoney = sevenSuccessMoney;
	}

	public float getTotalSuccessMoney() {
		return totalSuccessMoney;
	}

	public void setTotalSuccessMoney(float totalSuccessMoney) {
		this.totalSuccessMoney = totalSuccessMoney;
	}

	public float getProxySuccessMoney() {
		return proxySuccessMoney;
	}

	public void setProxySuccessMoney(float proxySuccessMoney) {
		this.proxySuccessMoney = proxySuccessMoney;
	}

	public int getProxySuccessCount() {
		return proxySuccessCount;
	}

	public void setProxySuccessCount(int proxySuccessCount) {
		this.proxySuccessCount = proxySuccessCount;
	}

	public float getProxyExpectMoney() {
		return proxyExpectMoney;
	}

	public void setProxyExpectMoney(float proxyExpectMoney) {
		this.proxyExpectMoney = proxyExpectMoney;
	}

	public float getProxyYestodayMoney() {
		return proxyYestodayMoney;
	}

	public void setProxyYestodayMoney(float proxyYestodayMoney) {
		this.proxyYestodayMoney = proxyYestodayMoney;
	}

	public float getProxySevenMoney() {
		return proxySevenMoney;
	}

	public void setProxySevenMoney(float proxySevenMoney) {
		this.proxySevenMoney = proxySevenMoney;
	}

	public float getProxyTotalMoney() {
		return proxyTotalMoney;
	}

	public void setProxyTotalMoney(float proxyTotalMoney) {
		this.proxyTotalMoney = proxyTotalMoney;
	}

}
