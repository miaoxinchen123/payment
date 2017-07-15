package com.tengfei.payment.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tengfei.payment.dao.ProxyRelationDao;
import com.tengfei.payment.dao.UserDao;
import com.tengfei.payment.dao.WithDrawDao;
import com.tengfei.payment.dto.WithDrawDto;
import com.tengfei.payment.tools.Utility;
import com.tengfei.payment.vo.Page;
import com.tengfei.payment.vo.ProxyRelation;
import com.tengfei.payment.vo.User;
import com.tengfei.payment.vo.WithDraw;

@Repository
public class WithDrawDaoImpl implements WithDrawDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public void addWithDraw(WithDraw withDraw) {
		sqlSessionTemplate.insert(WithDrawDao.prefix + ".addWithDraw", withDraw);
	}

	@Override
	public Page pageQueryWithDraw(Page page) {
		List<WithDraw> withDrawList = sqlSessionTemplate.selectList(WithDrawDao.prefix + ".pageQueryWithDraw", page);
		page.setRes(withDrawList);
		return page;
	}


	@Override
	public Page adminPageQueryWithDraw(Page page) {
		List<WithDrawDto> withDrawList = sqlSessionTemplate.selectList(WithDrawDao.prefix + ".adminPageQueryWithDraw", page);
		page.setRes(withDrawList);
		return page;
	}

	@Override
	public WithDraw getWithDraw(String id) {
		return sqlSessionTemplate.selectOne(WithDrawDao.prefix + ".getWithDraw", id);
	}

	@Override
	public void updateByStatus(WithDraw withDraw) {
		sqlSessionTemplate.update(WithDrawDao.prefix + ".updateWithDraw", withDraw);
		
	}

}
