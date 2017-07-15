package com.tengfei.payment.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tengfei.payment.vo.Order;
import com.tengfei.payment.vo.Page;

/**
 * 订单dao
 * @author miaoxin.chen
 * @date   2017年04月08日
 *
 */
public interface OrderDao {
	
	public String prefix = "com.tengfei.payment.dao.OrderDao";
		
	/**
	 * 添加订单
	 * @param order
	 */
	public void addOrder(Order order);
	
	/**
	 * 修改订单状态
	 * @param order
	 */
	public void updateOrder(Order order);
	
	/**
	 * 分页查询订单
	 * @param order
	 */
	public Page pageQueryOrder(Page page);
	
	/**
	 * 统计商户订单信息
	 * @param userId
	 */
	public Map<String, Object> getUserOrderCount(String userId,Date startDate,Date endDate,String orderStatus);

	public Order getUserOrderByBussinessId(String bussinessId);
	
}
