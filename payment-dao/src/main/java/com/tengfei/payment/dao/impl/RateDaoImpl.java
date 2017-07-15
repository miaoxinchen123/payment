package com.tengfei.payment.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tengfei.payment.dao.RateDao;
import com.tengfei.payment.vo.Rate;

@Repository
public class RateDaoImpl implements RateDao {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public Rate getRate() {
		List<Rate> rateList = sqlSessionTemplate.selectList(RateDao.prefix + ".getRate");
		return rateList.get(0);
	}

}
