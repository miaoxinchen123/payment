package com.tengfei.payment.service;

import com.tengfei.payment.vo.Page;

public interface CustomerService {

	public Page pageQueryCustomerService(Page page);

	public com.tengfei.payment.vo.CustomerService getByQQ(String qq);

	public boolean saveCustomerService(com.tengfei.payment.vo.CustomerService entity);

	public boolean delByQQ(com.tengfei.payment.vo.CustomerService entity);

}
