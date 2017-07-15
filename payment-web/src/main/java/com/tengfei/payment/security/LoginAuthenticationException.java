package com.tengfei.payment.security;

import org.springframework.security.core.AuthenticationException;

/**
 * 登录异常
 * @author jianfei.xu
 * @date   2016年8月24日
 *
 */
public class LoginAuthenticationException extends AuthenticationException {
	private static final long serialVersionUID = -8838550088696918744L;

	public LoginAuthenticationException(String msg) {
		super(msg);
	}
}
