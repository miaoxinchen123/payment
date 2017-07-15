package com.tengfei.payment.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * 无权限页面跳转
 * @author jianfei.xu
 * @date   2016年8月24日
 *
 */
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
	  /**
     * 页面错误处理类
     */
    private String errorPage;
    
    /**
     * 登录页面
     */
    private String loginFormUrl;
    
    /**
     * Handles an access denied failure.
     *
     * @param request that resulted in an <code>AccessDeniedException</code>
     * @param response so that the user agent can be advised of the failure
     * @param accessDeniedException that caused the invocation
     *
     * @throws IOException in the event of an IOException
     * @throws ServletException in the event of a ServletException
     */
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        if (!response.isCommitted()) {
            if(null != SecurityContextHolder.getContext().getAuthentication()) {
       		 if (null != errorPage) {
                	response.sendRedirect(request.getContextPath() + errorPage);
                } else {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage());
                }
	       	} else { //未登录跳回首页
	       		response.sendRedirect(request.getContextPath() + loginFormUrl);
	       	}
        }
    }

    /**
     * The error page to use. Must begin with a "/" and is interpreted relative to the current context root.
     *
     * @param errorPage the dispatcher path to display
     *
     * @throws IllegalArgumentException if the argument doesn't comply with the above limitations
     */
    public void setErrorPage(String errorPage) {
        if ((errorPage != null) && !errorPage.startsWith("/")) {
            throw new IllegalArgumentException("errorPage must begin with '/'");
        }

        this.errorPage = errorPage;
    }

	public String getLoginFormUrl() {
		return loginFormUrl;
	}

	public void setLoginFormUrl(String loginFormUrl) {
		this.loginFormUrl = loginFormUrl;
	}

	public String getErrorPage() {
		return errorPage;
	}
	
}
