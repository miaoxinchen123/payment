package com.tengfei.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tengfei.payment.dto.Result;
import com.tengfei.payment.tools.Utility;
import com.tengfei.payment.util.Tools;
import com.tengfei.payment.vo.CustomerService;
import com.tengfei.payment.vo.Page;

/**
 * 客服管理
 * @author jianfei.xu
 * @date   2016年8月30日
 */
@Controller
@RequestMapping("/customerService/")
public class CustomerServiceController {

	@Autowired
	private com.tengfei.payment.service.CustomerService customerService;
	 
	@RequestMapping("/page")
	public ModelAndView page(Page page) {
		ModelAndView mav = new ModelAndView("customerService/page");
		page.setPageState(true);
		customerService.pageQueryCustomerService(page);
		mav.addObject("page", page);
		
		return mav;
	}
	
	@RequestMapping("/addOrEdit")
	public ModelAndView addOrEdit(CustomerService entity) {
		ModelAndView mav = new ModelAndView("customerService/addOrEdit");
		if(Utility.isNotEmpty(entity.getQq())) entity = customerService.getByQQ(entity.getQq());
		mav.addObject("entity", entity);
		
		return mav;
	}
	
	@RequestMapping("/view")
	public ModelAndView view(CustomerService entity) {
		ModelAndView mav = new ModelAndView("customerService/view");
		if(Utility.isNotEmpty(entity.getQq())) entity = customerService.getByQQ(entity.getQq());
		mav.addObject("entity", entity);
		
		return mav;
	}
	
	@ResponseBody
	@RequestMapping("/save")
	public Result save(CustomerService entity) {
		Result result = Tools.buildOkResult();
		customerService.saveCustomerService(entity);
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/delByQQ")
	public Result delByQQ(CustomerService entity) {
		Result result = Tools.buildOkResult();
		customerService.delByQQ(entity);
		
		return result;
	}
	
}
