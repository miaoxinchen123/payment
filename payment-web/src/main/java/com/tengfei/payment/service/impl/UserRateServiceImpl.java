package com.tengfei.payment.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tengfei.payment.dao.RateDao;
import com.tengfei.payment.dao.UserRateDao;
import com.tengfei.payment.service.UserRateService;
import com.tengfei.payment.util.Tools;
import com.tengfei.payment.vo.Rate;
import com.tengfei.payment.vo.UserRate;

/**
 * 用户服务
 * @author jianfei.xu
 * @date   2016年8月25日
 *
 */
@Service
public class UserRateServiceImpl implements UserRateService {
	
	@Autowired
	UserRateDao UserRateDao;
	
	@Autowired
	RateDao rateDao;

	@Override
	public UserRate getUserRateById(String id) {
		return UserRateDao.getUserRateById(id);
	}

	@Override
	public void updateUserRate(UserRate userRate) {
		userRate.setUpdateDate(new Date());
		UserRateDao.updateUserRate(userRate);
	}

	@Override
	public UserRate saveUserRate(String userId) {
		Rate rate = rateDao.getRate();
		UserRate userRate = new UserRate();
		userRate.setId(Tools.generatorId());
		userRate.setUserId(userId);
		userRate.setCreateDate(new Date());
		userRate.setUpdateDate(new Date());
		userRate.setAlipayPc(rate.getAlipayPcSt());
		userRate.setAlipayWap(rate.getAlipayWapSt());
		userRate.setCardPc(rate.getCardPcSt());
		userRate.setCardWap(rate.getCardWapSt());
		userRate.setWechatPc(rate.getWechatPcSt());
		userRate.setWechatWap(rate.getWechatWapSt());
		UserRateDao.addUserRate(userRate);
		return userRate;
	}


	
}
