package com.tengfei.payment.service;

import com.tengfei.payment.dto.OrderDto;
import com.tengfei.payment.vo.Order;
import com.tengfei.payment.vo.Page;

/**
 * 订单服务
 * @author miaoxin.chen
 * @date   2016年8月24日
 *
 */
public interface OrderService {

	public Page pageQueryOrder(Page page);
	
	public OrderDto getUserOrderInfo(String userId);
	
	public Order getUserOrderByBussinessId(String bussinessId);
	
	public boolean saveOrUpdateOrder(Order order);
	
}
