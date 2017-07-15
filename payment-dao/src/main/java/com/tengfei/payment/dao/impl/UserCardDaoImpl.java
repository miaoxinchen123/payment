package com.tengfei.payment.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tengfei.payment.dao.UserCardDao;
import com.tengfei.payment.vo.Page;
import com.tengfei.payment.vo.UserCard;

@Repository
public class UserCardDaoImpl implements UserCardDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public boolean insertUserCard(UserCard userCard) {
		sqlSessionTemplate.insert(UserCardDao.prefix + ".insertUserCard", userCard);
		
		return true;
	}

	@Override
	public boolean delUserCard(UserCard userCard) {
		sqlSessionTemplate.insert(UserCardDao.prefix + ".delUserCard", userCard);
		
		return true;
	}

	@Override
	public Page listUserCard(Page page) {
		List<UserCard> userCardList = sqlSessionTemplate.selectList(UserCardDao.prefix + ".listUserCard", page);
		page.setRes(userCardList);
		return page;
	}

	@Override
	public int getUserCardCount(String userId) {
		Map<String, Object> res = sqlSessionTemplate.selectOne(UserCardDao.prefix + ".getUserCardCount", userId);
		
		return Integer.parseInt(res.get("count").toString());
	}

}
