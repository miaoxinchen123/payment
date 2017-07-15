package com.tengfei.payment.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tengfei.payment.dao.AreaDao;
import com.tengfei.payment.service.AreaService;
import com.tengfei.payment.vo.Area;

@Service
public class AreaServiceImpl implements AreaService {

	@Autowired
	private AreaDao areaDao;
	
	@Override
	public List<Area> getArea(Area area) {
		return areaDao.getArea(area);
	}

}
