package com.tengfei.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tengfei.payment.dto.ProxyRelationDto;
import com.tengfei.payment.dto.Result;
import com.tengfei.payment.service.ProxyRelationService;
import com.tengfei.payment.system.UserType;
import com.tengfei.payment.tools.Utility;
import com.tengfei.payment.util.Tools;
import com.tengfei.payment.vo.Page;
import com.tengfei.payment.vo.ProxyRelation;

@Controller
@RequestMapping("/proxy_relation")
public class ProxyRelationController {
	
	@Autowired
	private ProxyRelationService proxyRelationService;

	@RequestMapping("/{userId}")
	public ModelAndView list(@PathVariable String userId) {
		ModelAndView mav = new ModelAndView("proxyRelation/list");
		mav.addObject("merchantList", proxyRelationService.queryByProxyId(userId));
		mav.addObject("userId", userId);
		
		return mav;
	}
	
	@RequestMapping("/pageProxyRelation")
	public ModelAndView pageProxyRelation(Page page) {
		ModelAndView mav = new ModelAndView("proxyRelation/add");
		page.setPageState(true);
		page.getMapBean().put("type", UserType.MERCHANT.code());
		page = proxyRelationService.pageAvailProxyRelation(page);
		mav.addObject("page", page);
		
		return mav;
	}
	
	@RequestMapping("/editRate")
	public ModelAndView editRate(ProxyRelationDto proxyRelation) {
		ModelAndView mav = new ModelAndView("proxyRelation/editRate");
		mav.addObject("entity", proxyRelation);
		
		return mav;
	}
	
	@ResponseBody
	@RequestMapping("/add")
	public Result add(ProxyRelation proxyRelation) {
		Result result = Tools.buildOkResult();
		if(!Utility.isEmpty(proxyRelation.getRate())) {
			proxyRelation.setRate(proxyRelation.getRate() / 100);
		}
		proxyRelationService.addProxyRelation(proxyRelation);
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/edit")
	public Result edit(ProxyRelation proxyRelation) {
		Result result = Tools.buildOkResult();
		if(!Utility.isEmpty(proxyRelation.getRate())) {
			proxyRelation.setRate(proxyRelation.getRate() / 100);
		}
		proxyRelationService.editProxyRelation(proxyRelation);
		
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/del")
	public Result del(ProxyRelation proxyRelation) {
		Result result = Tools.buildOkResult();
		proxyRelationService.delProxyRelation(proxyRelation);
		
		return result;
	}
	
}
