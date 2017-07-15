package com.tengfei.payment.dao;

import com.tengfei.payment.vo.Page;
import com.tengfei.payment.vo.WithDraw;

/**
 * 提现记录dao
 * @author miaoxin.chen
 * @date   2017年04月08日
 *
 */
public interface WithDrawDao {
	
	public String prefix = "com.tengfei.payment.dao.withDrawDao";
		
	/**
	 * 添加提现请求
	 * @param order
	 */
	public void addWithDraw(WithDraw withDraw);
	
	/**
	 * 分页查询订单
	 * @param order
	 */
	public Page pageQueryWithDraw(Page page);
	
    /** ======================系统管理员部分逻辑 ========================/
   
	
	/**
	 * 分页查询订单
	 * @param order
	 */
	public Page adminPageQueryWithDraw(Page page);

	public WithDraw getWithDraw(String id);

	public void updateByStatus(WithDraw withDraw);
	
}
