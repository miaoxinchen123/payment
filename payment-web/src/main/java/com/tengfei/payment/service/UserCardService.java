package com.tengfei.payment.service;

import com.tengfei.payment.vo.Page;
import com.tengfei.payment.vo.UserCard;

public interface UserCardService {

	public int getUserCardCount(String userId);
	
	public boolean insertUserCard(UserCard userCard);
	
	public boolean delUserCard(UserCard userCard);
	
	public Page listUserCard(Page page);
	
}
