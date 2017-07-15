package com.tengfei.payment.dao;

import com.tengfei.payment.vo.Page;

public interface CustomerServiceDao {
	
	public String prefix = "com.tengfei.payment.dao.CustomerServiceDao";
	
	public Page pageQueryCustomerService(Page page);

	public com.tengfei.payment.vo.CustomerService getByQQ(String qq);

	public boolean insertCustomerService(com.tengfei.payment.vo.CustomerService entity);
	
	public boolean updateCustomerService(com.tengfei.payment.vo.CustomerService entity);

	public boolean delByQQ(com.tengfei.payment.vo.CustomerService entity);

}
