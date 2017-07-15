package com.tengfei.payment.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tengfei.payment.dao.AreaDao;
import com.tengfei.payment.vo.Area;

@Repository
public class AreaDaoImpl implements AreaDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List<Area> getArea(Area area) {
		return sqlSessionTemplate.selectList(AreaDao.prefix + ".getArea", area);
	}

}
