package com.tengfei.payment.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tengfei.payment.dao.WithDrawDao;
import com.tengfei.payment.service.WithDrawService;
import com.tengfei.payment.vo.Page;
import com.tengfei.payment.vo.WithDraw;

/**
 * 订单服务
 * @author  miaoxin.chen
 * @date   2016年8月25日
 *
 */
@Service
public class WithDrawServiceImpl implements WithDrawService {
	
	@Autowired
	private WithDrawDao withDrawDao;

	@Override
	public Page pageQueryWithDraw(Page page) {
		return withDrawDao.pageQueryWithDraw(page);
	}

	@Override
	public void saveWithDraw(WithDraw withDraw) {
		withDrawDao.addWithDraw(withDraw);
	}

	@Override
	public Page adminPageQueryWithDraw(Page page) {
		return withDrawDao.adminPageQueryWithDraw(page);
	}

	@Override
	public WithDraw getWithDraw(String id) {
		return withDrawDao.getWithDraw(id);
	}

	@Override
	public void updateByStatus(WithDraw withDraw) {
		withDraw.setUpdateDate(new Date());
		withDrawDao.updateByStatus(withDraw);
	}
	
}
