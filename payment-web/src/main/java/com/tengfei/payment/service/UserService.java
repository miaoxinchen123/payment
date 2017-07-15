package com.tengfei.payment.service;

import com.tengfei.payment.vo.Page;
import com.tengfei.payment.vo.User;

/**
 * 用户服务
 * @author jianfei.xu
 * @date   2016年8月24日
 *
 */
public interface UserService {

	public User getByUserCount(String count);
	
	public User getByUserId(String userId);
	
	public Page pageQueryUser(Page page);

	public boolean saveUser(User user);

	public boolean delUserById(User user);

	public boolean updateUserPwdById(User user);
	
	public User getByPhone(String phone);

}
