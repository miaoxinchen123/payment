package com.tengfei.payment.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tengfei.payment.dao.CustomerServiceDao;
import com.tengfei.payment.service.CustomerService;
import com.tengfei.payment.tools.Utility;
import com.tengfei.payment.vo.Page;

@Repository
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerServiceDao customerServiceDao;
	
	@Override
	public Page pageQueryCustomerService(Page page) {
		return customerServiceDao.pageQueryCustomerService(page);
	}

	@Override
	public com.tengfei.payment.vo.CustomerService getByQQ(String qq) {
		if(Utility.isEmpty(qq)) return null;
		
		return customerServiceDao.getByQQ(qq);
	}

	@Override
	public boolean saveCustomerService(com.tengfei.payment.vo.CustomerService entity) {
		if(Utility.isEmpty(entity.getId())) {
			customerServiceDao.insertCustomerService(entity);
			
			return true;
		}
		
		customerServiceDao.updateCustomerService(entity);
		
		return true;
	}

	@Override
	public boolean delByQQ(com.tengfei.payment.vo.CustomerService entity) {
		customerServiceDao.delByQQ(entity);
		
		return true;
	}

}
