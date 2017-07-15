package com.tengfei.payment.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tengfei.payment.dao.UserRateDao;
import com.tengfei.payment.vo.Page;
import com.tengfei.payment.vo.UserRate;


@Repository
public class UserRateDaoImpl implements UserRateDao{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public void updateUserRate(UserRate userRate) {
		sqlSessionTemplate.update(UserRateDao.prefix + ".updateUserRate", userRate);
		
	}

	@Override
	public void addUserRate(UserRate userRate) {
		sqlSessionTemplate.insert(UserRateDao.prefix + ".addUserRate", userRate);
		
	}

	@Override
	public boolean delUserRateById(UserRate userRate) {
		sqlSessionTemplate.delete(UserRateDao.prefix + ".delUserRateById", userRate);
		return true;
	}

	@Override
	public UserRate getUserRateById(String userId) {
		return sqlSessionTemplate.selectOne(UserRateDao.prefix + ".getUserRateById", userId);
	}

}
