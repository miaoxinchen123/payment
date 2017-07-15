package com.tengfei.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tengfei.payment.dto.Register;
import com.tengfei.payment.dto.Result;
import com.tengfei.payment.dto.Status;
import com.tengfei.payment.service.RegisterService;
import com.tengfei.payment.system.RegisterException;
import com.tengfei.payment.util.Tools;

@Controller
public class RegisterController {

	@Autowired
	private RegisterService registerService;
	
	@ResponseBody
	@RequestMapping("/register")
	public Result register(Register register) {
		Result result = Tools.buildOkResult();
		try {
			registerService.register(register);
		} catch (RegisterException e) {
			result.setStatus(Status.ERROR);
			result.setMessage(e.getMessage());
		}
		
		return result;
	}
	
}