package com.tengfei.payment.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.MessageSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.util.TextEscapeUtils;
import org.springframework.util.Assert;

import com.tengfei.payment.tools.Utility;

/**
 * 登录验证过滤器
 * @author jianfei.xu
 */
@SuppressWarnings("all")
public class LoginAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    public static final String SPRING_SECURITY_LAST_USERNAME_KEY = "SPRING_SECURITY_LAST_USERNAME";
    public static final String AUTHENTICATION_EXCEPTION = "SPRING_SECURITY_LAST_CUSTOME_EXCEPTION";
    public static final String AUTHENTICATION_FAILED_COUNT = "SPRING_SECURITY_LAST_FAILED_COUNT";
    public static final String SHOW_VALIDATE_CODE_FLAG = "SHOW_VALIDATE_CODE_FLAG";
    private boolean postOnly = true;
    private MessageSource messageSource = null;
    private String usernameParameter = "username";
    private String passwordParameter = "password";
    private SessionAuthenticationStrategy sessionStrategy = null;
    private AuthenticationSuccessHandler successHandler = null;
    private AuthenticationFailureHandler failureHandler = null;
    
    public LoginAuthenticationFilter() {
        super("/login");
    }
    
    /**
     * 处理filter
     * @param req					:请求
     * @param res					:返回
     * @param chain					:过滤器链
     * @throws IOException			:io异常
     * @throws ServletException		:servlet异常
     */
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        
        if (!requiresAuthentication(request, response)) {//不需要身份验证
            chain.doFilter(request, response);
            return;
        }
        
        Authentication authResult = null;
        try {
            authResult = attemptAuthentication(request, response);
        } catch (AuthenticationException failed) {
        	if(logger.isErrorEnabled()) {
        		logger.error("验证身份时发生错误：", failed);
        	}
            unsuccessfulAuthentication(request, response, failed);
            
            return;
        }
        
        if (null == authResult) {//验证失败返回
            return;
        }
        
        sessionStrategy.onAuthentication(authResult, request, response); //验证成功后对session做定制处理
        successfulAuthentication(request, response, chain, authResult);
    }
    
    /**
     * 尝试登录验证(后期单点登录在此扩展)
     * @param request				:请求
     * @param response				:返回
     * @return 验证成功返回验证对象
     */
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    	HttpSession session = request.getSession(true);
    	String username = null;
    	String password = null;
    	UsernamePasswordAuthenticationToken authRequest = null;
        username = obtainUsername(request);
        if (Utility.isEmpty(username)) {
            throw new LoginAuthenticationException("用户名不能为空!");
        }
        username = username.trim(); //TextEscapeUtils.escapeEntities(username.trim()); //防止js攻击
        if (null != session) {
        	session.setAttribute(SPRING_SECURITY_LAST_USERNAME_KEY, username);
		}
        password = obtainPassword(request);
        if (Utility.isEmpty(password)) {
        	throw new LoginAuthenticationException("密码不能为空!");
        }
    	
    	authRequest = new UsernamePasswordAuthenticationToken(username, password);
    	setDetails(request, authRequest);
        authRequest = (UsernamePasswordAuthenticationToken) this.getAuthenticationManager().authenticate(authRequest);
        
    	return authRequest;
    }
    
    /**
     * 验证成功后操作
     * @param request
     * @param response
     * @param chain
     * @param authResult the object returned from the <tt>attemptAuthentication</tt> method.
     * @throws IOException
     * @throws ServletException
     */
    public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException{
        if (logger.isDebugEnabled()) {
            logger.debug("登录验证成功,更新当前SecurityContextHolder: " + authResult);
        }

        SecurityContextHolder.getContext().setAuthentication(authResult);

        if (this.eventPublisher != null) {//发布事件
            eventPublisher.publishEvent(new InteractiveAuthenticationSuccessEvent(authResult, this.getClass()));
        }
        
        HttpSession session = request.getSession(false);
        successHandler.onAuthenticationSuccess(request, response, authResult);
    }

    /**
     * 验证失败后操作
     * @param  request
     * @param  response
     * @param  failed
     * @throws IOException
     * @throws ServletException
     */
    public void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();

        if (logger.isDebugEnabled()) {
            logger.debug("登录验证失败: " + failed.toString());
            logger.debug("失败处理类： " + failureHandler);
        }
        
        if (failed instanceof UsernameNotFoundException
    			|| failed instanceof LoginAuthenticationException) {//登录验证出错
        	HttpSession session = request.getSession(false);
        	if(null != session) {
                session.setAttribute(AUTHENTICATION_EXCEPTION, failed.getMessage());
                int errNum = 1;
                if(null != session.getAttribute(AUTHENTICATION_FAILED_COUNT)) {
                	errNum += Integer.parseInt((String) session.getAttribute(AUTHENTICATION_FAILED_COUNT));
                }
                session.setAttribute(AUTHENTICATION_FAILED_COUNT, "" + errNum);
        	}
        	
    	} else {
    		logger.error("账号: " + obtainUsername(request) + ", 发生未知错误: ", failed);
    		HttpSession session = request.getSession(false);
        	if(null != session) {
                session.setAttribute(AUTHENTICATION_EXCEPTION, "登录出错, 请联系管理员!");
                int errNum = 1;
                if(null != session.getAttribute(AUTHENTICATION_FAILED_COUNT)) {
                	errNum += Integer.parseInt((String) session.getAttribute(AUTHENTICATION_FAILED_COUNT));
                }
                session.setAttribute(AUTHENTICATION_FAILED_COUNT, "" + errNum);
        	}
    	}

        failureHandler.onAuthenticationFailure(request, response, failed);
    }

    protected String obtainPassword(HttpServletRequest request) {
        return request.getParameter(passwordParameter);
    }

    protected String obtainUsername(HttpServletRequest request) {
        return request.getParameter(usernameParameter);
    }

    protected void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    public void setUsernameParameter(String usernameParameter) {
        Assert.hasText(usernameParameter, "用户名参数不能为空");
        this.usernameParameter = usernameParameter;
    }

    public void setPasswordParameter(String passwordParameter) {
        Assert.hasText(passwordParameter, "密码参数名不能为空");
        this.passwordParameter = passwordParameter;
    }

	public boolean isPostOnly() {
		return postOnly;
	}
	
    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public final String getUsernameParameter() {
        return usernameParameter;
    }

    public final String getPasswordParameter() {
        return passwordParameter;
    }

	public SessionAuthenticationStrategy getSessionStrategy() {
		return sessionStrategy;
	}

	public void setSessionStrategy(SessionAuthenticationStrategy sessionStrategy) {
		this.sessionStrategy = sessionStrategy;
	}

	public AuthenticationSuccessHandler getSuccessHandler() {
		return successHandler;
	}

	public void setSuccessHandler(AuthenticationSuccessHandler successHandler) {
		this.successHandler = successHandler;
	}

	public AuthenticationFailureHandler getFailureHandler() {
		return failureHandler;
	}

	public void setFailureHandler(AuthenticationFailureHandler failureHandler) {
		this.failureHandler = failureHandler;
	}

}
