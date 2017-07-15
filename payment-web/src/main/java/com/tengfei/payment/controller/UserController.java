package com.tengfei.payment.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tengfei.payment.dto.Result;
import com.tengfei.payment.service.CustomerService;
import com.tengfei.payment.service.ProxyRelationService;
import com.tengfei.payment.service.UserService;
import com.tengfei.payment.system.BalanceType;
import com.tengfei.payment.system.UserStatu;
import com.tengfei.payment.system.UserType;
import com.tengfei.payment.tools.Utility;
import com.tengfei.payment.util.Tools;
import com.tengfei.payment.vo.Page;
import com.tengfei.payment.vo.ProxyRelation;
import com.tengfei.payment.vo.User;

/**
 * 用户管理
 * @author jianfei.xu
 * @date   2016年8月30日
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ProxyRelationService proxyRelationService;
	 
	@RequestMapping("/page")
	public ModelAndView page(Page page) {
		ModelAndView mav = new ModelAndView("user/page");
		page.setPageState(true);
		userService.pageQueryUser(page);
		mav.addObject("page", page);
		mav.addObject("userTypeMap", UserType.kv());
		mav.addObject("userStatuMap", UserStatu.kv());
		
		return mav;
	}
	
	@RequestMapping("/addOrEditUser")
	public ModelAndView addOrEditUser(User user) {
		ModelAndView mav = new ModelAndView("user/addOrEditUser");
		if(Utility.isNotEmpty(user.getId())) user = userService.getByUserId(user.getId());
		mav.addObject("entity", user);
		mav.addObject("userTypeMap", UserType.kv());
		mav.addObject("userStatuMap", UserStatu.kv());
		mav.addObject("balanceTypeMap", BalanceType.kv());
		mav.addObject("customerServiceMap", getCustomerServiceKV());
	
		
		return mav;
	}
	
	@RequestMapping("/chgPwd")
	public ModelAndView chgPwd(User user) {
		ModelAndView mav = new ModelAndView("user/changePassword");
		
		return mav;
	}
	
	protected Map<String, String> getCustomerServiceKV() {
		Map<String, String> retMap = new LinkedHashMap<>();
		Page page = customerService.pageQueryCustomerService(new Page());
		if(null == page || null == page.getRes()) return retMap;
		List<com.tengfei.payment.vo.CustomerService> customerServiceList = (List<com.tengfei.payment.vo.CustomerService>) page.getRes();
		for(com.tengfei.payment.vo.CustomerService tmp : customerServiceList) {
			retMap.put(tmp.getQq(), tmp.getName() + "(" + tmp.getQq() + ")");
		}
			
		return retMap;
	}
	
	@RequestMapping("/view")
	public ModelAndView view(User user) {
		ModelAndView mav = new ModelAndView("user/view");
		if(Utility.isNotEmpty(user.getId())) user = userService.getByUserId(user.getId());
		List<ProxyRelation> proxyRelationList = proxyRelationService.getProxyMerchantId(user.getId());
		if(null != proxyRelationList && !proxyRelationList.isEmpty()){
			User proxyUser = userService.getByUserId(proxyRelationList.get(0).getUserId());
			proxyUser.setPassword(null);
			mav.addObject("proxyUser", proxyUser);
		}
		mav.addObject("entity", user);
		
		return mav;
	}
	
	@ResponseBody
	@RequestMapping("/saveUser")
	public Result saveUser(User user) {
		Result result = Tools.buildOkResult();
		userService.saveUser(user);
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/delUserById")
	public Result delUserById(User user) {
		Result result = Tools.buildOkResult();
		userService.delUserById(user);
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/updateUserPwdById")
	public Result updateUserPwdById(User user) {
		Result result = Tools.buildOkResult();
		userService.updateUserPwdById(user);
		
		return result;
	}
	
}
