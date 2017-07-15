package com.tengfei.payment.dao;

import java.util.List;

import com.tengfei.payment.vo.Page;
import com.tengfei.payment.vo.User;

/**
 * 用户dao
 * @author jianfei.xu
 * @date   2016年8月25日
 *
 */
public interface UserDao {
	
	public String prefix = "com.tengfei.payment.dao.UserDao";
			
	public User getByUserCount(String count);
	
	public Page pageQueryUser(Page page);

	public User getByUserId(String userId);

	public boolean insertUser(User user);

	public boolean updateUserById(User user);

	public boolean delUserById(User user);
	
	public List<User> getNormalProxy(User user);

	public User getByPhone(String phone);

}
