package com.tengfei.payment.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tengfei.payment.dto.OrderDto;
import com.tengfei.payment.dto.OrderValidateDto;
import com.tengfei.payment.dto.Result;
import com.tengfei.payment.dto.Status;
import com.tengfei.payment.service.OrderService;
import com.tengfei.payment.service.UserRateService;
import com.tengfei.payment.service.UserService;
import com.tengfei.payment.system.OrderStatus;
import com.tengfei.payment.system.TradeType;
import com.tengfei.payment.tools.Utility;
import com.tengfei.payment.util.Tools;
import com.tengfei.payment.util.UserUtil;
import com.tengfei.payment.vo.Order;
import com.tengfei.payment.vo.Page;
import com.tengfei.payment.vo.User;
import com.tengfei.payment.vo.UserRate;

/**
 * 订单管理
 * 
 * @author miaoxin.chen
 * @date 2016年8月30日
 */
@Controller
@RequestMapping("/orderService")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserRateService userRateService;

	@Autowired
	private UserService userService;

	@ResponseBody
	@RequestMapping("/page")
	public ModelAndView page(Page page) {
		User user = UserUtil.getCurrentUser();
		if (page.getMapBean() == null) {
			HashMap<String, Object> mapBean = new HashMap<>();
			mapBean.put("userId", user.getId());
			mapBean.put("userType", user.getType());
			page.setMapBean(mapBean);
		}
		ModelAndView mav = new ModelAndView("orderService/orderCenter");
		page.setPageState(true);
		orderService.pageQueryOrder(page);
		mav.addObject("page", page);
		mav.addObject("orderStatus", OrderStatus.kv());
		mav.addObject("tradeType", TradeType.kv());
		return mav;
	}

	@RequestMapping("/userOrderInfo")
	public @ResponseBody OrderDto userOrderInfo() {
		User user = UserUtil.getCurrentUser();
		OrderDto orderDto = orderService.getUserOrderInfo(user.getId());
		return orderDto;
	}

	
	@RequestMapping(value="/validateOrder",method = RequestMethod.POST)
	public @ResponseBody OrderValidateDto validateOrder(String user_id,String bussiness_id) {
		OrderValidateDto validateDto = new OrderValidateDto();
		if(Utility.isEmpty(user_id)||Utility.isEmpty(bussiness_id)){
			validateDto.setParamRight(false);
			return validateDto;
		}
		validateDto.setParamRight(true);
		User user = userService.getByUserId(user_id);
		if(user ==null){
			validateDto.setUserExist(false);
			return validateDto;
		}else{
			validateDto.setUserExist(true);
		}
		if(null == orderService.getUserOrderByBussinessId(bussiness_id)){
			validateDto.setOrderExist(false);
		}else{
			validateDto.setOrderExist(true);
		}
		
		return validateDto;
	}

	@RequestMapping(value = "/saveOrder", method = RequestMethod.POST)
	public @ResponseBody String saveOrUpdateOrder(String user_id, String bussiness_id, String bussiness_money,
			String bussiness_channel, String status, String date) {
		if (Utility.isEmpty(user_id) || Utility.isEmpty(bussiness_id) || Utility.isEmpty(bussiness_channel)
				|| Utility.isEmpty(status) || Utility.isEmpty(bussiness_money) || Utility.isEmpty(date)) {
			return "param error";
		}
		User user = userService.getByUserId(user_id);
		if (user == null) {
			return "no user";
		}
		Order order = orderService.getUserOrderByBussinessId(bussiness_id);
		if (order == null) {
			order = new Order();
			order.setId(Tools.generatorId());
			order.setUserId(user_id);
			order.setBusinessId(bussiness_id);
			try {
				order.setBusinessMoney(Float.valueOf(bussiness_money));
			} catch (Exception e) {
				return "money format error";
			}
			try {
				order.setBusinessTime(Utility.parseStringToDate(date));
			} catch (ParseException e) {
				return "date format error";
			}
			order.setStatus(status);
			// 获取用户利率，计算平台抽成 TODO 很小的时候怎么处理
			UserRate userRate = userRateService.getUserRateById(user_id);
			float platformChargeFloat = 0;
			float platformCharge = 0;
			if (userRate == null) {
				// 默认千分之5
				platformChargeFloat = 0.005f;
			} else {
				if (bussiness_channel.equals(TradeType.ALIPAYPC.code())) {
					platformChargeFloat = userRate.getAlipayPc();
				} else if (bussiness_channel.equals(TradeType.ALIPAYWAP.code())) {
					platformChargeFloat = userRate.getAlipayWap();
				} else if (bussiness_channel.equals(TradeType.CARDWAP.code())) {
					platformChargeFloat = userRate.getCardWap();
				} else if (bussiness_channel.equals(TradeType.CARDPC.code())) {
					platformChargeFloat = userRate.getCardPc();
				} else if (bussiness_channel.equals(TradeType.WECHATPC.code())) {
					platformChargeFloat = userRate.getWechatPc();
				} else if (bussiness_channel.equals(TradeType.WECHATWAP.code())) {
					platformChargeFloat = userRate.getWechatWap();
				} else {
					return "channel error";
				}
			}
			order.setBusinessChannel(bussiness_channel);
			platformCharge = new BigDecimal(platformChargeFloat).multiply(new BigDecimal(bussiness_money))
					.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
			//最低收费1分钱
			if(platformCharge < 0.01) {
				platformCharge = 0.01f;
			}
			order.setPlatformCharge(platformCharge);
			order.setCreateDate(new Date());
			if (status.equals(OrderStatus.SUCCESS.code())) {
				// 修改账户余额
				user.setBalance(
						user.getBalance().add(new BigDecimal(order.getBusinessMoney() - order.getPlatformCharge())));
				userService.saveUser(user);
			}
			orderService.saveOrUpdateOrder(order);
		} else {
			return "order existed";
		}
		return "success";
	}

}
