package com.tengfei.payment.dao;

import com.tengfei.payment.vo.Rate;

/**
 * 基准费率dao
 * @author miaoxin.chen
 * @date   2017年04月08日
 *
 */
public interface RateDao {
	
	public String prefix = "com.tengfei.payment.dao.RateDao";
		
	
	
	/**
	 * 获取基准利率
	 */
	public Rate getRate();
	
	
}
