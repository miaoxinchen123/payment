package com.tengfei.payment.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tengfei.payment.dao.OrderDao;
import com.tengfei.payment.dao.ProxyRelationDao;
import com.tengfei.payment.dao.UserDao;
import com.tengfei.payment.dto.OrderDto;
import com.tengfei.payment.service.OrderService;
import com.tengfei.payment.system.OrderStatus;
import com.tengfei.payment.system.UserType;
import com.tengfei.payment.tools.Utility;
import com.tengfei.payment.util.Tools;
import com.tengfei.payment.util.UserUtil;
import com.tengfei.payment.vo.Order;
import com.tengfei.payment.vo.Page;
import com.tengfei.payment.vo.ProxyRelation;
import com.tengfei.payment.vo.User;

/**
 * 订单服务
 * @author  miaoxin.chen
 * @date   2016年8月25日
 *
 */
@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private ProxyRelationDao proxyRelationDao;
	
	
	@Override
	public Page pageQueryOrder(Page page) {
		Map<String, Object> mapBean = page.getMapBean();
		if(null!=mapBean){
			String endDate = (String) mapBean.get("endDate"); 
			if(Utility.isNotEmpty(endDate)){
				Date end = new Date(endDate);
				mapBean.put("endDate", end);
			}
			
			String startDate = (String) mapBean.get("startDate");
			if(Utility.isNotEmpty(startDate)){
				Date start = new Date(startDate);
				mapBean.put("startDate", start);
			}
		}
		User user = UserUtil.getCurrentUser();
		if(user.getType().equals(UserType.MERCHANT.code())){
			mapBean.put("userId",user.getId());
			return orderDao.pageQueryOrder(page);
		}else{
			List<String> merchantIds = new ArrayList<String>();
			if(null !=mapBean.get("userCount") && "" !=mapBean.get("userCount") ){
				
				User merchantIdUser = userDao.getByUserCount((String) mapBean.get("userCount"));
				if(null!=merchantIdUser){
					List<ProxyRelation> proxyRelations = proxyRelationDao.getProxyMerchantId(merchantIdUser.getId());
					if(null !=proxyRelations && !proxyRelations.isEmpty()){
						merchantIds.add(merchantIdUser.getId());
					}
				}
			}else{
				List<ProxyRelation> proxyRelations = proxyRelationDao.getProxyUserId(user.getId());
				if(null !=proxyRelations && !proxyRelations.isEmpty()){
					for(ProxyRelation proxyRelation:proxyRelations){
						merchantIds.add(proxyRelation.getMerchantId());
					}
				}
			}
			if(null !=merchantIds && !merchantIds.isEmpty()){
				mapBean.put("merchantIds", Utility.ListToString(merchantIds));
				return orderDao.pageQueryOrder(page);
			}
		}
		return page;
		
	}

	@Override
	public OrderDto getUserOrderInfo(String userId) {
		if(Utility.isEmpty(userId)) return null;
		
		User user = userDao.getByUserId(userId);
		
		if(user == null) return null;
		
		OrderDto orderDto = new OrderDto();
		
		if(user.getType().equals(UserType.MERCHANT.code())){
			
			//查询用户当天订单
			Map<String,Object> todaySuccessMap = orderDao.getUserOrderCount(userId, Utility.getCurDateStart(), Utility.getCurDateEnd(), OrderStatus.SUCCESS.code());
			
			//查询用户当天处理中订单
			Map<String,Object> todayDealingMap = orderDao.getUserOrderCount(userId, Utility.getCurDateStart(), Utility.getCurDateEnd(), OrderStatus.DEALING.code());
			
			//查询用户当天失败订单
			Map<String,Object> todayFailedMap = orderDao.getUserOrderCount(userId, Utility.getCurDateStart(), Utility.getCurDateEnd(),  OrderStatus.FAIL.code());
			
			//查询用户昨天订单
			Map<String,Object> yestodayMap = orderDao.getUserOrderCount(userId, Utility.getYesDateStart(), Utility.getYesDateEnd(), OrderStatus.SUCCESS.code());
			
			//查询用户七天订单
			Map<String,Object> sevenDayMap  =orderDao.getUserOrderCount(userId, Utility.getSevenDateStart(), Utility.getCurDateEnd(), OrderStatus.SUCCESS.code());
			
			//查询用户七天订单
			Map<String,Object> allDayMap  =orderDao.getUserOrderCount(userId,  null, null, OrderStatus.SUCCESS.code());
			
			
			if(todaySuccessMap != null && !todaySuccessMap.isEmpty()){
				orderDto.setSuccessCount(Integer.valueOf(todaySuccessMap.get("count").toString()));
				orderDto.setSuccessMoney(Float.valueOf(todaySuccessMap.get("money").toString()));
			}else{
				orderDto.setSuccessCount(Integer.valueOf("0"));
				orderDto.setSuccessMoney(Float.valueOf("0"));
			}
			
			if(todayDealingMap != null && !todayDealingMap.isEmpty()){
				orderDto.setDealingCount(Integer.valueOf(todayDealingMap.get("count").toString()));
				orderDto.setDealingMoney(Float.valueOf(todayDealingMap.get("money").toString()));
			}else{
				orderDto.setDealingCount(Integer.valueOf("0"));
				orderDto.setDealingMoney(Float.valueOf("0"));
			}
			
			if(todayFailedMap != null && !todayFailedMap.isEmpty()){
				orderDto.setFailCount(Integer.valueOf(todayFailedMap.get("count").toString()));
				orderDto.setFailMoney(Float.valueOf(todayFailedMap.get("money").toString()));
			}else{
				orderDto.setFailCount(Integer.valueOf("0"));
				orderDto.setFailMoney(Float.valueOf("0"));
			}
			
			if(yestodayMap != null  && !yestodayMap.isEmpty()){
				orderDto.setYestodaySuccessMoney(Float.valueOf(yestodayMap.get("money").toString()));		
			}else{
				orderDto.setYestodaySuccessMoney(Float.valueOf("0"));
			}

			if(sevenDayMap != null  && !sevenDayMap.isEmpty()){
				orderDto.setSevenSuccessMoney(Float.valueOf(sevenDayMap.get("money").toString()));
			}else{
				orderDto.setSevenSuccessMoney(Float.valueOf("0"));
			}
			
			if(allDayMap != null  && !allDayMap.isEmpty()){
				orderDto.setTotalSuccessMoney(Float.valueOf(allDayMap.get("money").toString()));
			}else{
				orderDto.setTotalSuccessMoney(Float.valueOf("0"));
			}
			
		}else if(user.getType().equals(UserType.PROXY.code())){
			//当天数据查询
			//查询代理和商户关系
			List<ProxyRelation> relations = proxyRelationDao.getProxyUserId(userId);
			if(relations !=null && !relations.isEmpty()){
				int count = 0;
				float money = 0f;
				float proxyMoney = 0f;
				float yestodayProxyMoney = 0f;
				float sevenProxyMoney = 0f;
				float totalProxyMoney = 0f;
				
				for(ProxyRelation tempProxyRelation : relations){
				
					float yestodayMoney = 0f;
					float sevenMoney = 0f;
					float totalMoney = 0f;
					
					//查询用户当天订单
					Map<String,Object> todayProxyUserSuccessMap = orderDao.getUserOrderCount(tempProxyRelation.getMerchantId(), Utility.getCurDateStart(), Utility.getCurDateEnd(), OrderStatus.SUCCESS.code());
					if(todayProxyUserSuccessMap != null && !todayProxyUserSuccessMap.isEmpty()){
						count += Integer.valueOf(todayProxyUserSuccessMap.get("count").toString());
						money = money + Float.valueOf(todayProxyUserSuccessMap.get("money").toString());
						BigDecimal tempUserMoney = new BigDecimal(todayProxyUserSuccessMap.get("money").toString()).multiply(new BigDecimal(tempProxyRelation.getRate())).divide(new BigDecimal("100"), 2, RoundingMode.UP); 
						proxyMoney = proxyMoney + tempUserMoney.floatValue();
					}
					
					//查询用户昨天订单
					Map<String,Object> yestodayProxyUserSuccessMap = orderDao.getUserOrderCount(tempProxyRelation.getMerchantId(), Utility.getYesDateStart(), Utility.getYesDateEnd(), OrderStatus.SUCCESS.code());
					if(yestodayProxyUserSuccessMap != null && !yestodayProxyUserSuccessMap.isEmpty()){
						yestodayMoney = yestodayMoney + Float.valueOf(yestodayProxyUserSuccessMap.get("money").toString());
						BigDecimal tempUserMoney = new BigDecimal(yestodayProxyUserSuccessMap.get("money").toString()).multiply(new BigDecimal(tempProxyRelation.getRate())).divide(new BigDecimal("100"), 2, RoundingMode.UP); 
						yestodayProxyMoney = yestodayProxyMoney + tempUserMoney.floatValue();
					}
					
					//查询用户七天订单
					Map<String,Object> sevenProxyUserSuccessMap = orderDao.getUserOrderCount(tempProxyRelation.getMerchantId(), Utility.getSevenDateStart(), Utility.getCurDateEnd(), OrderStatus.SUCCESS.code());
					if(sevenProxyUserSuccessMap != null && !sevenProxyUserSuccessMap.isEmpty()){
						sevenMoney = sevenMoney + Float.valueOf(sevenProxyUserSuccessMap.get("money").toString());
						BigDecimal tempUserMoney = new BigDecimal(sevenProxyUserSuccessMap.get("money").toString()).multiply(new BigDecimal(tempProxyRelation.getRate())).divide(new BigDecimal("100"), 2, RoundingMode.UP); 
						sevenProxyMoney = sevenProxyMoney + tempUserMoney.floatValue();
					}
					
					//查询用户所有订单
					Map<String,Object> totalProxyUserSuccessMap = orderDao.getUserOrderCount(tempProxyRelation.getMerchantId(), null, null, OrderStatus.SUCCESS.code());
					if(totalProxyUserSuccessMap != null && !totalProxyUserSuccessMap.isEmpty()){
						totalMoney = totalMoney + Float.valueOf(totalProxyUserSuccessMap.get("money").toString());
						BigDecimal tempUserMoney = new BigDecimal(totalProxyUserSuccessMap.get("money").toString()).multiply(new BigDecimal(tempProxyRelation.getRate())).divide(new BigDecimal("100"), 2, RoundingMode.UP); 
						totalProxyMoney = totalProxyMoney + tempUserMoney.floatValue();
					}
					
				
				}
				orderDto.setProxyExpectMoney(proxyMoney);
				orderDto.setProxySuccessCount(count);
				orderDto.setProxySuccessMoney(money);
				orderDto.setProxyYestodayMoney(yestodayProxyMoney);
				orderDto.setProxySevenMoney(sevenProxyMoney);
				orderDto.setProxyTotalMoney(totalProxyMoney);
			}else{
				orderDto.setProxyExpectMoney(Float.valueOf("0"));
				orderDto.setProxySuccessCount(0);
				orderDto.setProxySuccessMoney(Float.valueOf("0"));
				orderDto.setProxyYestodayMoney(Float.valueOf("0"));
				orderDto.setProxySevenMoney(Float.valueOf("0"));
				orderDto.setProxyTotalMoney(Float.valueOf("0"));
			}
		}else{
			return null;
		}
		return orderDto;
	}

	@Override
	public Order getUserOrderByBussinessId(String bussinessId) {
		return orderDao.getUserOrderByBussinessId(bussinessId);
	}

	@Override
	public boolean saveOrUpdateOrder(Order order) {
		orderDao.addOrder(order);
		return true;
		
	}


}
