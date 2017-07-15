package com.tengfei.payment.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.util.Assert;

/**
 * 验证登录账号信息
 * @author jianfei.xu
 */
@SuppressWarnings("all")
public class LoginAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    private static final String USER_NOT_FOUND_PASSWORD = "userNotFoundPassword";

    private PasswordEncoder passwordEncoder;

    private String userNotFoundEncodedPassword;

    private SaltSource saltSource;

    private UserDetailsService userDetailsService;

    public LoginAuthenticationProvider() {
        setPasswordEncoder(new PlaintextPasswordEncoder());
    }
    
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.isInstanceOf(UsernamePasswordAuthenticationToken.class, authentication,
            messages.getMessage("AbstractUserDetailsAuthenticationProvider.onlySupports",
                "只支持UsernamePasswordAuthenticationToken类型"));

        String username = (authentication.getPrincipal() == null) ? "NONE_PROVIDED" : authentication.getName();
        boolean cacheWasUsed = true;
        UserDetails user = getUserCache().getUserFromCache(username);

        if (user == null) {
            cacheWasUsed = false;
            try {
                user = retrieveUser(username, (UsernamePasswordAuthenticationToken) authentication);
            } catch (UsernameNotFoundException | LoginAuthenticationException notFound) {
            	if(logger.isDebugEnabled()) {
            		logger.debug("用户提取出现异常");
            	}

                if (hideUserNotFoundExceptions) {
                    throw new BadCredentialsException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials"
                    		, "无效的用户名"));
                } else {
                    throw notFound;
                }
            }

            Assert.notNull(user, "提取用户信息为空");
        }
        
    	try {
            additionalAuthenticationChecks(user, (UsernamePasswordAuthenticationToken) authentication);
        } catch (AuthenticationException exception) {
        	if (cacheWasUsed) {
                cacheWasUsed = false;
                user = retrieveUser(username, (UsernamePasswordAuthenticationToken) authentication);
                additionalAuthenticationChecks(user, (UsernamePasswordAuthenticationToken) authentication);
            } else {
                throw exception;
            }
        }

        WebAuthenticationDetails authenticationDetails = (WebAuthenticationDetails) authentication.getDetails();

        if (!cacheWasUsed) {
            getUserCache().putUserInCache(user);
        }

        Object principalToReturn = user;
        if (isForcePrincipalAsString()) {
            principalToReturn = user.getUsername();
        }
        
        return createSuccessAuthentication(principalToReturn, authentication, user);
    }
	
    /**
     * 验证用户密码
     * @param userDetails		:用户详细信息
     * @param authentication	:凭证令牌
     */
	@Override
	public void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) 
			throws AuthenticationException {
		
		if(userDetails instanceof UserExt) {
			/*if(!((UserExt)userDetails).isEnabled()) {
				throw new LoginAuthenticationException(messages.getMessage("CustomValidate.Abnormal", "账户异常,请联系管理员"));
			}*/
		}
		
		Object salt = null;
        if (this.saltSource != null) {
            salt = this.saltSource.getSalt(userDetails);
        }

        if (null == authentication.getCredentials()) {
        	if(logger.isDebugEnabled()) {
        		logger.debug("验证信息中用户名为空");
        	}

            throw new LoginAuthenticationException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "无效的用户"));
        }

        String presentedPassword = authentication.getCredentials().toString();

        if (!passwordEncoder.isPasswordValid(userDetails.getPassword(), presentedPassword, salt)) {
        	if(logger.isDebugEnabled()) {
        		logger.debug("密码验证错误");
        	}
            throw new LoginAuthenticationException(messages.getMessage("CustomValidate.PwdErr", "密码输入不正确"));
        }
        
	}

	/**
	 * 提取用户
	 * @param username			:用户名
	 * @param authentication	:凭证令牌
	 * @return	返回用户详细信息
	 */
	@Override
	public UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		UserDetails loadedUser = null;
        try {
            loadedUser = getUserDetailsService().loadUserByUsername(username);
        } catch (AuthenticationException authenticationException) {
            throw authenticationException;
        } catch (Exception repositoryProblem) {
            throw new InternalAuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
        }

        if (loadedUser == null) {
        	 throw new UsernameNotFoundException(messages.getMessage("CustomValidate.UserNameNotFound", "用户不存在"));
        }
        
        return loadedUser;
	}
	
    protected void doAfterPropertiesSet() throws Exception {
        Assert.notNull(this.userDetailsService, "userDetailsService不能为空");
    }

	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        Assert.notNull(passwordEncoder, "密码加密不能为空");
        this.userNotFoundEncodedPassword = passwordEncoder.encodePassword(USER_NOT_FOUND_PASSWORD, null);
        this.passwordEncoder = passwordEncoder;
	}

	public String getUserNotFoundEncodedPassword() {
		return userNotFoundEncodedPassword;
	}

	public void setUserNotFoundEncodedPassword(String userNotFoundEncodedPassword) {
		this.userNotFoundEncodedPassword = userNotFoundEncodedPassword;
	}

	public SaltSource getSaltSource() {
		return saltSource;
	}

	public void setSaltSource(SaltSource saltSource) {
		this.saltSource = saltSource;
	}

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
}
