package com.tengfei.payment.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tengfei.payment.dao.OrderDao;
import com.tengfei.payment.dao.ProxyRelationDao;
import com.tengfei.payment.dao.UserDao;
import com.tengfei.payment.system.OrderStatus;
import com.tengfei.payment.system.UserStatu;
import com.tengfei.payment.system.UserType;
import com.tengfei.payment.tools.Utility;
import com.tengfei.payment.vo.Order;
import com.tengfei.payment.vo.ProxyRelation;
import com.tengfei.payment.vo.User;

@Service  
public class ProxyTimeTask {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ProxyRelationDao proxyRelationDao;
	
	@Autowired
	private OrderDao orderDao;
	
	
	public void jobDailySummary(){  
		User searchUser = new User();
		searchUser.setStatus(UserStatu.OK.code());
		searchUser.setType(UserType.PROXY.code());
		List<User> proxyUsers = userDao.getNormalProxy(searchUser);
		if(null != proxyUsers && !proxyUsers.isEmpty()){
			for(User proxy : proxyUsers){
				List<ProxyRelation> relations = proxyRelationDao.getProxyUserId(proxy.getId());
				if(relations !=null && !relations.isEmpty()){
					for(ProxyRelation tempProxyRelation : relations){
						//获取用户下面的所有商户
						//查询用户昨天订单
						Map<String,Object> yestodayProxyUserSuccessMap = orderDao.getUserOrderCount(tempProxyRelation.getMerchantId(), Utility.getYesDateStart(), Utility.getYesDateEnd(), OrderStatus.SUCCESS.code());
						if(yestodayProxyUserSuccessMap != null && !yestodayProxyUserSuccessMap.isEmpty()){
							float yestodayProxyMoney = new BigDecimal(yestodayProxyUserSuccessMap.get("money").toString()).multiply(new BigDecimal(tempProxyRelation.getRate())).divide(new BigDecimal("100"), 2, RoundingMode.UP).floatValue(); 
							//分批插入数据库
							Order order =new Order();
							order.setId(tempProxyRelation.getMerchantId()+Utility.getYesDateStart().toString());
							order.setUserId(proxy.getId());
							order.setBusinessMoney(yestodayProxyMoney);
							order.setCreateDate(new Date());
							order.setStatus(OrderStatus.SUCCESS.code());
							order.setUpdateDate(new Date());
							orderDao.addOrder(order);
							
						}
					}
				}		 
			}
		}
	} 
}
