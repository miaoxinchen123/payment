package com.tengfei.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tengfei.payment.dto.Result;
import com.tengfei.payment.service.UserRateService;
import com.tengfei.payment.system.OrderStatus;
import com.tengfei.payment.tools.Utility;
import com.tengfei.payment.util.Tools;
import com.tengfei.payment.vo.Card;
import com.tengfei.payment.vo.Page;
import com.tengfei.payment.vo.User;
import com.tengfei.payment.vo.UserRate;

/**
 * 用户利率管理
 * @author miaoxin.chen
 * @date   2016年8月30日
 */
@Controller
@RequestMapping("/rateService")
public class UserRateController {
	

	@Autowired
	private UserRateService usrRateService;

	
	@RequestMapping("/{userId}")
	public ModelAndView addOrEditCard(@RequestParam("id")String id) {
		if(Utility.isNotEmpty(id)){
			UserRate userRate = usrRateService.getUserRateById(id);
			ModelAndView mav = new ModelAndView("rateService/addOrEditUserRate");
			if(null == userRate){
				userRate = usrRateService.saveUserRate(id);
			}
			mav.addObject("userRate", userRate);
			return mav;
		}
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/saveUserRate")
	public Result saveCard(UserRate userRate) {
		Result result = Tools.buildOkResult();
		userRate.setAlipayPc(userRate.getAlipayPc()/1000);
		userRate.setAlipayWap(userRate.getAlipayWap()/1000);
		userRate.setCardPc(userRate.getCardPc()/1000);
		userRate.setCardWap(userRate.getCardWap()/1000);
		userRate.setWechatPc(userRate.getWechatPc()/1000);
		userRate.setWechatWap(userRate.getWechatWap()/1000);
		usrRateService.updateUserRate(userRate);
		return result;
	}
	
}
