package com.tengfei.payment.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tengfei.payment.service.UserService;
import com.tengfei.payment.system.UserStatu;
import com.tengfei.payment.system.UserType;
import com.tengfei.payment.tools.Utility;


/**
 * 用户信息提取
 * @author jianfei.xu
 * @date   2016年8月24日
 *
 */
@Service
public class DefaultUserDetailsService implements UserDetailsService {
	@Autowired
	private UserService userService;
	
	/**
	 * 按用户名找查当前用户的信息
	 * @param username 用户名
	 * @throws UsernameNotFoundException
	 * 		   LoginAuthenticationException
	 * @return 用户信息实体类
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, LoginAuthenticationException {
		com.tengfei.payment.vo.User user = this.userService.getByUserCount(username); //从用户表查找
		if (null == user || Utility.isEmpty(user.getCount())) {
			throw new UsernameNotFoundException("用户名或密码不正确!");
		}
		
		UserExt retUser = new UserExt(user.getId(), user.getCount(), user.getPassword(), UserStatu.fromCode(user.getStatus())
				, UserType.fromCode(user.getType()));
		
		return retUser;
    }

}
