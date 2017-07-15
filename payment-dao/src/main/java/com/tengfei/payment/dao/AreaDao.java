package com.tengfei.payment.dao;

import java.util.List;

import com.tengfei.payment.vo.Area;

public interface AreaDao {
	
	public String prefix = "com.tengfei.payment.dao.AreaDao";

	List<Area> getArea(Area area);
	
}
