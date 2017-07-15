package com.tengfei.payment.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tengfei.payment.dao.UserDao;
import com.tengfei.payment.tools.Utility;
import com.tengfei.payment.vo.Page;
import com.tengfei.payment.vo.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public User getByUserCount(String count) {
		List<User> userList = sqlSessionTemplate.selectList(UserDao.prefix + ".getByUserCount", count);
		if(Utility.isEmpty(userList)) return null;
		
		return userList.get(0);
	}

	@Override
	public Page pageQueryUser(Page page) {
		List<User> userList = sqlSessionTemplate.selectList(UserDao.prefix + ".pageQueryUser", page);
		page.setRes(userList);
		return page;
	}

	@Override
	public User getByUserId(String userId) {
		List<User> userList = sqlSessionTemplate.selectList(UserDao.prefix + ".getByUserId", userId);
		if(Utility.isEmpty(userList)) return null;
		return userList.get(0);
	}

	@Override
	public boolean insertUser(User user) {
		sqlSessionTemplate.insert(UserDao.prefix + ".insertUser", user);
		
		return true;
	}

	@Override
	public boolean updateUserById(User user) {
		sqlSessionTemplate.update(UserDao.prefix + ".updateByUserId", user);
		
		return true;
	}

	@Override
	public boolean delUserById(User user) {
		sqlSessionTemplate.delete(UserDao.prefix + ".delUserById", user);
		
		return true;
	}

	@Override
	public List<User> getNormalProxy(User user) {
		List<User> userList = sqlSessionTemplate.selectList(UserDao.prefix + ".getNormalProxy",user);
		return userList;
	}

	@Override
	public User getByPhone(String phone) {
		List<User> userList = sqlSessionTemplate.selectList(UserDao.prefix + ".getByPhone", phone);
		if(Utility.isEmpty(userList)) return null;
		
		return userList.get(0);
	}

}
