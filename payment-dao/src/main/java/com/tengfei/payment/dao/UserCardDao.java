package com.tengfei.payment.dao;

import com.tengfei.payment.vo.Page;
import com.tengfei.payment.vo.UserCard;

public interface UserCardDao {
	public String prefix = "com.tengfei.payment.dao.UserCardDao";
	
	public boolean insertUserCard(UserCard userCard);
	
	public boolean delUserCard(UserCard userCard);
	
	public Page listUserCard(Page page);
	
	public int getUserCardCount(String userId);
	
}
