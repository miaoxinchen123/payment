package com.tengfei.payment.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tengfei.payment.dto.Menu;
import com.tengfei.payment.dto.OrderDto;
import com.tengfei.payment.service.OrderService;
import com.tengfei.payment.service.ProxyRelationService;
import com.tengfei.payment.service.UserCardService;
import com.tengfei.payment.service.UserService;
import com.tengfei.payment.system.UserType;
import com.tengfei.payment.tools.Utility;
import com.tengfei.payment.util.UserUtil;
import com.tengfei.payment.vo.User;

/**
 * 登录控制
 * @author jianfei.xu
 * @date   2016年8月25日
 *
 */
@Controller
public class LogonController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserCardService userCardService;
	
	@Autowired
	private ProxyRelationService proxyRelationService;

	@RequestMapping("/logon")
	public ModelAndView logon() {
		UserType userType = UserUtil.getCurrentUserType();
		ModelAndView mav = new ModelAndView("logon");
		if(null == userType) return mav;
		if(userType == UserType.SYS) {
			mav = new ModelAndView("logon");
			if(UserUtil.isLogged()) {
				List<Menu> menuList = loadMenu();
				mav.addObject("menuList", menuList);
				mav.addObject("logOnUserName", UserUtil.getCurrentUserName());
				
				if(!Utility.isEmpty(menuList)) {//默认一级菜单名称
					mav.addObject("selTopMenuTitle", menuList.get(0).getMenuName());
				}
			}
		} else {
			mav = new ModelAndView("merchant");
			mav.addObject("logOnUserName", UserUtil.getCurrentUserName());
			User user = userService.getByUserId(UserUtil.getCurrentUserId());
			user.setPassword(null);
			OrderDto order = orderService.getUserOrderInfo(UserUtil.getCurrentUserId());
			mav.addObject("user", user);
			mav.addObject("order", order);
			mav.addObject("cardCount", userCardService.getUserCardCount(UserUtil.getCurrentUserId()));
			if(userType == UserType.PROXY) {//提取商户
				mav.addObject("merchantList", proxyRelationService.queryByProxyId(UserUtil.getCurrentUserId()));
			}
		}
		
		return mav;
	}
	
	protected List<Menu> loadMenu() {
		List<Menu> menuList = new LinkedList<Menu>();
		Menu menu = new Menu();
		menuList.add(menu);
		menu.setMenuId("firstMenu_01");
		menu.setMenuName("后台管理");
		menu.setLeaf(false);
		menu.setOrderNum(1);
		List<Menu> secondMenuList = new LinkedList<Menu>();
		menu.setChildrenMenus(secondMenuList);
		menu = new Menu();
		secondMenuList.add(menu);
		menu.setParentMenuId("firstMenu_01");
		menu.setMenuId("secondMenu_01_01");
		menu.setMenuName("用户管理");
		menu.setLeaf(true);
		menu.setMenuUrl("payment/user/page");
		menu.setOrderNum(1);
		menu = new Menu();
		secondMenuList.add(menu);
		menu.setParentMenuId("firstMenu_01");
		menu.setMenuId("secondMenu_01_02");
		menu.setMenuName("客服管理");
		menu.setLeaf(true);
		menu.setMenuUrl("payment/customerService/page");
		menu.setOrderNum(2);
		menu = new Menu();
		secondMenuList.add(menu);
		menu.setParentMenuId("firstMenu_01");
		menu.setMenuId("secondMenu_01_03");
		menu.setMenuName("银行卡管理");
		menu.setLeaf(true);
		menu.setMenuUrl("payment/cardService/page");
		menu.setOrderNum(3);
		menu = new Menu();
		secondMenuList.add(menu);
		menu.setParentMenuId("firstMenu_01");
		menu.setMenuId("secondMenu_01_04");
		menu.setMenuName("提现管理");
		menu.setLeaf(true);
		menu.setMenuUrl("payment/withDrawService/adminPage");
		menu.setOrderNum(4);
		return menuList;
	}
	
}