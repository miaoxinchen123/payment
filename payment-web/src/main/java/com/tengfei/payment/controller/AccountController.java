package com.tengfei.payment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tengfei.payment.dto.Result;
import com.tengfei.payment.dto.Status;
import com.tengfei.payment.dto.UpdatePhoneDto;
import com.tengfei.payment.service.ProxyRelationService;
import com.tengfei.payment.service.UserRateService;
import com.tengfei.payment.service.UserService;
import com.tengfei.payment.system.UserType;
import com.tengfei.payment.util.MsgUtil;
import com.tengfei.payment.util.Tools;
import com.tengfei.payment.util.UserUtil;
import com.tengfei.payment.vo.ProxyRelation;
import com.tengfei.payment.vo.User;
import com.tengfei.payment.vo.UserRate;

@Controller
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRateService userRateService;
	
	@Autowired
	private  ProxyRelationService proxyRelationService;
	
	@RequestMapping("/edit")
	public ModelAndView edit(User user) {
		ModelAndView mav = new ModelAndView("account/edit");
		user.setId(UserUtil.getCurrentUserId());
		user = userService.getByUserId(user.getId());
		user.setPassword(null);
		mav.addObject("user", user);
		
		UserRate userRate = userRateService.getUserRateById(UserUtil.getCurrentUserId());
		
		if (user.getType().equals(UserType.MERCHANT.code())) {
			List<ProxyRelation> proxyRelationList = proxyRelationService.getProxyMerchantId(user.getId());
			if(null != proxyRelationList && !proxyRelationList.isEmpty()){
				User proxyUser = userService.getByUserId(proxyRelationList.get(0).getUserId());
				mav.addObject("proxyUser", proxyUser);
			}
		}
		
		mav.addObject("userRate", userRate);
		
		return mav;
	}
	
	@RequestMapping("/editBasicInfo")
	public ModelAndView editBasicInfo(User user) {
		ModelAndView mav = new ModelAndView("account/editBasicInfo");
		user.setId(UserUtil.getCurrentUserId());
		user = userService.getByUserId(user.getId());
		user.setPassword(null);
		mav.addObject("user", user);
		
		return mav;
	}
	
	@RequestMapping("/editPwd")
	public ModelAndView editPwd(User user) {
		ModelAndView mav = new ModelAndView("account/editPwd");
		user.setId(UserUtil.getCurrentUserId());
		user.setCount(UserUtil.getCurrentUserName());
		mav.addObject("user", user);
		
		return mav;
	}
	
	@RequestMapping("/editPhone")
	public ModelAndView editPhone(User user) {
		ModelAndView mav = new ModelAndView("account/editPhone");
		user.setId(UserUtil.getCurrentUserId());
		user = userService.getByUserId(user.getId());
		user.setPassword(null);
		mav.addObject("user", user);
		
		return mav;
	}
	
	@ResponseBody
	@RequestMapping("/updatePhone")
	public Result saveUser(UpdatePhoneDto updatePhone) {
		Result result = Tools.buildOkResult();
		User user = userService.getByUserId(UserUtil.getCurrentUserId());
		if(null == user) {
			result.setStatus(Status.ERROR);
			result.setMessage("账户异常或者登录失效");
			return result;
		}
		
		String oldPhone = user.getPhone();
		if(!MsgUtil.validateCode(oldPhone, updatePhone.getVerifyCode())) {
			result.setStatus(Status.ERROR);
			result.setMessage("验证码输入错误或者失效");
			return result;
		}
		
		user = new User();
		user.setId(updatePhone.getId());
		user.setPhone(updatePhone.getUpdatePhone());
		userService.saveUser(user);
		MsgUtil.removeCode(oldPhone);
		
		return result;
	}
	
}
