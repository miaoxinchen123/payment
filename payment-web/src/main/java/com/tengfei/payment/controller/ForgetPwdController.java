package com.tengfei.payment.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tengfei.payment.dto.MsgVo;
import com.tengfei.payment.dto.Result;
import com.tengfei.payment.dto.Status;
import com.tengfei.payment.service.UserService;
import com.tengfei.payment.util.MsgUtil;
import com.tengfei.payment.util.Tools;
import com.tengfei.payment.vo.User;

@Controller
@RequestMapping("/forgetPwd")
public class ForgetPwdController {
	
	@Autowired
	private UserService userService;

	@ResponseBody
	@RequestMapping("/sendCode")
	public Map<String, Object> register(String phone) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", Status.OK);
		result.put("message", "操作成功");
		User user = userService.getByPhone(phone);
		if(null == user) {
			result.put("status", Status.ERROR);
			result.put("message", "手机号码未注册");
			return result;
		}
		
		MsgVo msgVo = MsgUtil.sendCode(phone);
		if(null == msgVo) {
			result.put("status", Status.ERROR);
			result.put("message", "验证码发送失败");
			return result;
		}
		
		result.put("token", user.getId());
		result.put("code", msgVo.getObj());
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/updatePwd")
	public Result updatePwd(User user) {
		Result result = Tools.buildOkResult();
		User tmpUser = userService.getByUserId(user.getId());
		if(null == tmpUser || !tmpUser.getPhone().equals(user.getPhone())) {
			result.setStatus(Status.ERROR);
			result.setMessage("参数传入不合法");
			return result;
		}
		
		if(!MsgUtil.validateCode(user.getPhone(), user.getQq())) {
			result.setStatus(Status.ERROR);
			result.setMessage("修改操作超时, 请重新点击忘记密码");
			return result;
		}
		
		userService.updateUserPwdById(user);
		MsgUtil.removeCode(user.getPhone());
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/validateCode")
	public Result validate(String phone,String code) {
		Result mvcResult = Tools.buildOkResult();
		if(!MsgUtil.validateCode(phone, code)) {
			mvcResult.setStatus(Status.ERROR);
			mvcResult.setMessage("验证码输入错误");
		}
		
		return mvcResult;
	}
	
}