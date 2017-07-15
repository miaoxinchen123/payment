package com.tengfei.payment.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tengfei.payment.dto.Result;
import com.tengfei.payment.dto.WithDrawDto;
import com.tengfei.payment.service.CardService;
import com.tengfei.payment.service.UserService;
import com.tengfei.payment.service.WithDrawService;
import com.tengfei.payment.system.BalanceType;
import com.tengfei.payment.system.OrderStatus;
import com.tengfei.payment.system.TradeType;
import com.tengfei.payment.system.UserStatu;
import com.tengfei.payment.system.UserType;
import com.tengfei.payment.system.WithDrawStatus;
import com.tengfei.payment.tools.Utility;
import com.tengfei.payment.util.Tools;
import com.tengfei.payment.util.UserUtil;
import com.tengfei.payment.vo.Page;
import com.tengfei.payment.vo.User;
import com.tengfei.payment.vo.WithDraw;

/**
 * 体现管理
 * @author miaoxin.chen
 * @date   2016年8月30日
 */
@Controller
@RequestMapping("/withDrawService")
public class WithDrawController {

	@Autowired
	private WithDrawService withDrawService;
	
	@Autowired
	private CardService cardService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/page")
	public ModelAndView page(Page page) {
		User user = UserUtil.getCurrentUser();
		if(page.getMapBean() == null){
			HashMap<String, Object> mapBean = new HashMap<>();
			mapBean.put("userId", user.getId());
			page.setMapBean(mapBean);
		}
		ModelAndView mav = new ModelAndView("withDrawService/withDrawCenter");
		page.setPageState(true);
		withDrawService.pageQueryWithDraw(page);
		mav.addObject("page", page);
		mav.addObject("withDrawStatus", WithDrawStatus.kv());
		return mav;
	}
	
	
	@RequestMapping("/withDraw")
	public ModelAndView withDraw(WithDrawDto withDraw) {
		User user = UserUtil.getCurrentUser();
		ModelAndView mav = new ModelAndView("withDrawService/withDraw");
		//获取用户的银行卡
		mav.addObject("cardList",cardService.getUserCard(user.getId()));
		//获取用户的提现方式
		User fullUser = userService.getByUserId(user.getId());
		mav.addObject("user",fullUser);
		//添加默认提现对象
		withDraw.setUserId(user.getId());
		mav.addObject("entity",withDraw);
		return mav;
	}
	
	@ResponseBody
	@RequestMapping("/saveWithDraw")
	public Result saveWithDraw(WithDraw withDraw) {
		Result result = Tools.buildOkResult();
		
		withDrawService.saveWithDraw(withDraw);
		result.setMessage("您的提现请求正在处理中");
		return result;
	}
	
	@RequestMapping("/adminPage")
	public ModelAndView adminPage(Page page) {
		ModelAndView mav = new ModelAndView("withDrawService/page");
		page.setPageState(true);
		withDrawService.adminPageQueryWithDraw(page);
		mav.addObject("page", page);
		mav.addObject("withDrawStatus", WithDrawStatus.kv());
		return mav;
	}
	
	@RequestMapping("/editWithdraw")
	public ModelAndView editWithdraw(String id) {
		ModelAndView mav = new ModelAndView("withDrawService/editWithdraw");
		WithDraw withDraw = null;
		if(Utility.isNotEmpty(id)) {
			withDraw= withDrawService.getWithDraw(id);
		}
		mav.addObject("withDraw", withDraw);
		mav.addObject("withDrawStatus", WithDrawStatus.kv());
		return mav;
	}
	
	@ResponseBody
	@RequestMapping("/updateWithdraw")
	public Result updateWithdraw(WithDraw withDraw) {
		Result result = Tools.buildOkResult();
		withDrawService.updateByStatus(withDraw);
		result.setMessage("OK");
		return result;
	}
}
