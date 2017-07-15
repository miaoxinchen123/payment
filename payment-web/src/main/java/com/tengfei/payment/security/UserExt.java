package com.tengfei.payment.security;

import java.util.ArrayList;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.tengfei.payment.system.UserStatu;
import com.tengfei.payment.system.UserType;
import com.tengfei.payment.util.UserUtil;

public class UserExt extends User {

	private static final long serialVersionUID = -6440768879299100186L;
	
	private String id;
	
	private UserStatu statu;
	
	private UserType type;
	
	public UserExt(String id, String username, String password, UserStatu statu, UserType type) {
		super(username, password, UserUtil.isEnabled(statu.code()), true, true,
				true, new ArrayList<SimpleGrantedAuthority>(0));
		this.id = id;
		this.statu = statu;
		this.type = type;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public UserStatu getStatu() {
		return statu;
	}

	public void setStatu(UserStatu statu) {
		this.statu = statu;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}
	
}
