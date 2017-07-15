package com.tengfei.payment.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

import com.tengfei.payment.security.UserExt;
import com.tengfei.payment.system.UserStatu;
import com.tengfei.payment.system.UserType;
import com.tengfei.payment.vo.User;

/**
 * 用户工具类
 * @author jianfei.xu
 *
 */
public class UserUtil {
	/**
	 * 得到当前用户信息
	 * @return 用户信息类名
	 */
	public static User getCurrentUser(){
		Authentication au = SecurityContextHolder.getContext().getAuthentication();
		User user = new User();
		UserExt tmpUser = (com.tengfei.payment.security.UserExt)au.getPrincipal();
		user.setCount(tmpUser.getUsername());
		user.setId(tmpUser.getId());
		user.setType(tmpUser.getType().code());
		user.setStatus(tmpUser.getStatu().code());
		
		return user;
	}
	
	public static String getCurrentUserId() {
		User user = getCurrentUser();
		if(null == user) return "";
		
		return user.getId();
	}
	
	public static UserType getCurrentUserType() {
		User user = getCurrentUser();
		if(null == user) return null;
		
		return UserType.fromCode(user.getType());
	}
	
	public static UserStatu getCurrentUserStatu() {
		User user = getCurrentUser();
		if(null == user) return null;
		
		return UserStatu.fromCode(user.getStatus());
	}
	
	/**
	 * 得到当前用户名（非帐号）
	 * @return 账号名称
	 */
	public static String getCurrentUserName(){
		User user = getCurrentUser();
		if(user == null) return "";
		return user.getCount();
	}
	
	/**
	 * 判断当前用户是否登陆
	 * @return 判断结果
	 */
	public static boolean isLogged(){
		Authentication au = SecurityContextHolder.getContext().getAuthentication();
		if(au == null) return false;
		if(au.getPrincipal() == null) return false;
		return true;
	}
	
	/**
	 * 账户是否可用
	 * @param status
	 * @return
	 */
	public static boolean isEnabled(String status) {
		return !status.equals(UserStatu.ABNORMAL.code());
	}
	
	/**
	 * 获取用户的登陆IP
	 * @return IP地址
	 */
	public static String getLonginIp() { 
		Authentication au = SecurityContextHolder.getContext().getAuthentication();
		if (au == null) return null;
		String ip = ((WebAuthenticationDetails) au.getDetails()).getRemoteAddress();
	    return ip; 
	}
	
}
