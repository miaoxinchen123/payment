package com.tengfei.payment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tengfei.payment.service.AreaService;
import com.tengfei.payment.vo.Area;

@Controller
public class AreaController {
	
	@Autowired
	private AreaService areaService;
	
	@ResponseBody
	@RequestMapping("/area")
	public List<Area> getArea(Area area) {
		
		return areaService.getArea(area);
	}
	
}
