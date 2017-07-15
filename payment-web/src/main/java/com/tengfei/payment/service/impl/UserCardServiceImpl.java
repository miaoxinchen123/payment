package com.tengfei.payment.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tengfei.payment.dao.UserCardDao;
import com.tengfei.payment.service.UserCardService;
import com.tengfei.payment.tools.Utility;
import com.tengfei.payment.util.Tools;
import com.tengfei.payment.util.UserUtil;
import com.tengfei.payment.vo.Page;
import com.tengfei.payment.vo.UserCard;

@Service
public class UserCardServiceImpl implements UserCardService {

	@Autowired
	private UserCardDao userCardDao;
	
	@Override
	public int getUserCardCount(String userId) {
		return userCardDao.getUserCardCount(userId);
	}

	@Override
	public boolean insertUserCard(UserCard userCard) {
		if(Utility.isEmpty(userCard.getId())) {
			userCard.setId(Tools.generatorId());
			userCard.setUserId(UserUtil.getCurrentUserId());
			userCard.setCreateDate(new Date());
		}
		userCardDao.insertUserCard(userCard);
		
		return true;
	}

	@Override
	public boolean delUserCard(UserCard userCard) {
		userCardDao.delUserCard(userCard);
		
		return true;
	}

	@Override
	public Page listUserCard(Page page) {
		return userCardDao.listUserCard(page);
	}

}
