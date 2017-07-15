package com.tengfei.payment.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tengfei.payment.dao.OrderDao;
import com.tengfei.payment.dao.ProxyRelationDao;
import com.tengfei.payment.dao.UserDao;
import com.tengfei.payment.dao.WithDrawDao;
import com.tengfei.payment.dto.OrderDto;
import com.tengfei.payment.tools.Utility;
import com.tengfei.payment.vo.Order;
import com.tengfei.payment.vo.Page;
import com.tengfei.payment.vo.ProxyRelation;
import com.tengfei.payment.vo.User;
import com.tengfei.payment.vo.WithDraw;

@Repository
public class OrderDaoImpl implements OrderDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public void addOrder(Order order) {
		sqlSessionTemplate.insert(OrderDao.prefix + ".addOrder", order);
	}

	@Override
	public void updateOrder(Order order) {
		sqlSessionTemplate.update(OrderDao.prefix + ".updateOrder", order);
	}

	@Override
	public Page pageQueryOrder(Page page) {
		List<Order> orderList = sqlSessionTemplate.selectList(OrderDao.prefix + ".pageQueryOrder", page);
		page.setRes(orderList);
		return page;
	}
	
	@Override
	public Map<String,Object> getUserOrderCount(String userId,Date startDate,Date endDate,String orderStatus) {
		Map<String,Object> orderMap = new HashMap<String,Object>();
		orderMap.put("userId", userId);
		orderMap.put("startDate", startDate);
		orderMap.put("endDate", endDate);
		if(!Utility.isEmpty(orderStatus)){
			orderMap.put("status", orderStatus);
		}
		Map<String,Object> countData = sqlSessionTemplate.selectOne(OrderDao.prefix + ".userOrderByDate", orderMap);
		return countData;
	}

	@Override
	public Order getUserOrderByBussinessId(String bussinessId) {
		Order order = sqlSessionTemplate.selectOne(OrderDao.prefix + ".getUserOrderByBussinessId", bussinessId);
		return order;
	}

}
