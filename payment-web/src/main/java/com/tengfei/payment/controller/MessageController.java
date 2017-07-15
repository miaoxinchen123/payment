
package com.tengfei.payment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tengfei.payment.dto.MsgVo;
import com.tengfei.payment.dto.Result;
import com.tengfei.payment.dto.Status;
import com.tengfei.payment.util.MsgUtil;
import com.tengfei.payment.util.Tools;

@Controller
@RequestMapping("/messageService")
public class MessageController {
	
	@ResponseBody
	@RequestMapping("/send")
	public Result save(String phoneNum) {
		Result mvcResult = Tools.buildOkResult();
		MsgVo msgVo = MsgUtil.sendCode(phoneNum);
		if(null == msgVo) mvcResult.setStatus(Status.ERROR);
		
		return mvcResult;
	}
	
	@ResponseBody
	@RequestMapping("/validate")
	public Result validate(String phoneNum,String code) {
		Result mvcResult = Tools.buildOkResult();
		if(!MsgUtil.validateCode(phoneNum, code)) {
			mvcResult.setStatus(Status.ERROR);
			mvcResult.setMessage("验证码输入错误");
		}
		
		return mvcResult;
	}

}
