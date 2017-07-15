package com.tengfei.payment.service;

import com.tengfei.payment.vo.UserRate;

/**
 * 商户费率服务
 * @author miaoxin.chen
 * @date   2016年8月24日
 *
 */
public interface UserRateService {

	public UserRate getUserRateById(String id);
	
	public void updateUserRate(UserRate userRate);
	
	public UserRate saveUserRate(String userId);
	
}
