package com.tengfei.payment.dao;

import com.tengfei.payment.vo.UserRate;

/**
 * 客户利率dao
 * @author miaoxin.chen
 * @date   2017年04月08日
 *
 */
public interface UserRateDao {
	
	public String prefix = "com.tengfei.payment.dao.UserRateDao";
		
	
	/**
	 *添加用户利率
	 * @param order
	 */
	public void addUserRate(UserRate userRate);
	
	
	/**
	 * 修改用户利率状态
	 * @param order
	 */
	public boolean delUserRateById(UserRate userRate);

	/**
	 * 获取用户利率状态
	 * @param order
	 */
	public UserRate getUserRateById(String userId);
	
	
	/**
	 * 修改用户利率状态
	 * @param order
	 */
	public void updateUserRate(UserRate userRate);
	
	
	
}
