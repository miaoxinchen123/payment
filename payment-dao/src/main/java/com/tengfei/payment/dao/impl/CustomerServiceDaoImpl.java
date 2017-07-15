package com.tengfei.payment.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tengfei.payment.dao.CustomerServiceDao;
import com.tengfei.payment.tools.Utility;
import com.tengfei.payment.vo.CustomerService;
import com.tengfei.payment.vo.Page;

@Repository
public class CustomerServiceDaoImpl implements CustomerServiceDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public Page pageQueryCustomerService(Page page) {
		List<CustomerService> customerServiceList = sqlSessionTemplate.selectList(CustomerServiceDao.prefix + ".pageQueryCustomerService", page);
		page.setRes(customerServiceList);
		return page;
	}

	@Override
	public CustomerService getByQQ(String qq) {
		List<CustomerService> customerServiceList = sqlSessionTemplate.selectList(CustomerServiceDao.prefix + ".getByQQ", qq);
		if(Utility.isEmpty(customerServiceList)) return null;
		
		return customerServiceList.get(0);
	}

	@Override
	public boolean delByQQ(CustomerService entity) {
		sqlSessionTemplate.delete(CustomerServiceDao.prefix + ".delByQQ", entity);
		
		return true;
	}

	@Override
	public boolean insertCustomerService(CustomerService entity) {
		sqlSessionTemplate.insert(CustomerServiceDao.prefix + ".insertCustomerService", entity);
		
		return true;
	}

	@Override
	public boolean updateCustomerService(CustomerService entity) {
		sqlSessionTemplate.update(CustomerServiceDao.prefix + ".updateCustomerService", entity);
		
		return true;
	}

}
