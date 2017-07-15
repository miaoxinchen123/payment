package com.tengfei.payment.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tengfei.payment.dto.Result;
import com.tengfei.payment.service.AreaService;
import com.tengfei.payment.service.CardService;
import com.tengfei.payment.service.UserCardService;
import com.tengfei.payment.util.Tools;
import com.tengfei.payment.util.UserUtil;
import com.tengfei.payment.vo.Area;
import com.tengfei.payment.vo.Page;
import com.tengfei.payment.vo.UserCard;

@Controller
@RequestMapping("/user_card")
public class UserCardController {

	@Autowired
	private UserCardService userCardService;
	
	@Autowired
	private CardService cardService;
	
	@Autowired
	private AreaService areaService;
	
	@RequestMapping("/page")
	public ModelAndView page(Page page) {
		ModelAndView mav = new ModelAndView("userCard/list");
		page.setMapBean(new HashMap<>(0));
		page.getMapBean().put("userId", UserUtil.getCurrentUserId());
		page = userCardService.listUserCard(page);
		mav.addObject("page", page);
		
		return mav;
	}
	
	@RequestMapping("/add")
	public ModelAndView add(UserCard entity) {
		ModelAndView mav = new ModelAndView("userCard/add");
		mav.addObject("entity", entity);
		mav.addObject("cardList", cardService.getAvailableCard(UserUtil.getCurrentUserId()));
		Area area = new Area();
		area.setParentId("0");
		mav.addObject("provinces", areaService.getArea(area));
		
		return mav;
	}
	
	@ResponseBody
	@RequestMapping("/save")
	public Result save(UserCard userCard) {
		Result result = Tools.buildOkResult();
		userCardService.insertUserCard(userCard);
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/del")
	public Result del(UserCard userCard) {
		Result result = Tools.buildOkResult();
		userCardService.delUserCard(userCard);
		
		return result;
	}
	
}
