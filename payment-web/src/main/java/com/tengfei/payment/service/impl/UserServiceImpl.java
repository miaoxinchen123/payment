package com.tengfei.payment.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.SystemWideSaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tengfei.payment.dao.UserDao;
import com.tengfei.payment.service.UserRateService;
import com.tengfei.payment.service.UserService;
import com.tengfei.payment.tools.Utility;
import com.tengfei.payment.util.Tools;
import com.tengfei.payment.vo.Page;
import com.tengfei.payment.vo.User;

/**
 * 用户服务
 * @author jianfei.xu
 * @date   2016年8月25日
 *
 */
@SuppressWarnings("deprecation")
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserRateService userRateService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SystemWideSaltSource saltSource;

	@Override
	public User getByUserCount(String count) {
		if(Utility.isEmpty(count)) return null;
		
		return userDao.getByUserCount(count);
	}

	@Override
	public Page pageQueryUser(Page page) {
		return userDao.pageQueryUser(page);
	}

	@Override
	public User getByUserId(String userId) {
		return userDao.getByUserId(userId);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean saveUser(User user) {
		if(Utility.isEmpty(user.getBalanceType())) user.setBalanceType(null);
		if(Utility.isEmpty(user.getId())) {
			user.setId(Tools.generatorId());
			if(Utility.isNotEmpty(user.getPassword())) {
				user.setPassword(passwordEncoder.encodePassword(user.getPassword()
						, saltSource.getSystemWideSalt()));
			}
			if(Utility.isEmpty(user.getBalance())) user.setBalance(new BigDecimal(0));
			user.setCreateDate(new Date());
			userDao.insertUser(user);
			userRateService.saveUserRate(user.getId());
			
			return true;
		}
		
		user.setUpdateDate(new Date());
		userDao.updateUserById(user);
		
		return true;
	}

	@Override
	public boolean delUserById(User user) {
		userDao.delUserById(user);
		
		return true;
	}

	@Override
	public boolean updateUserPwdById(User user) {
		if(Utility.isEmpty(user.getPassword())) {
			user.setPassword(Tools.defaultPwd());
		}
		user.setPassword(passwordEncoder.encodePassword(user.getPassword()
				, saltSource.getSystemWideSalt()));
		user.setUpdateDate(new Date());
		userDao.updateUserById(user);
		
		return true;
	}

	@Override
	public User getByPhone(String phone) {
		return userDao.getByPhone(phone);
	}

}
