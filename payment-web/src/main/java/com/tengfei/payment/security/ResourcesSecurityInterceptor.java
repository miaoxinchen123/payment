package com.tengfei.payment.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

/**
 * 资源权限验证(暂未实现)
 * @author jianfei.xu
 * @date   2016年8月24日
 *
 */
public class ResourcesSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {
	
    public void doFilter(ServletRequest request, ServletResponse response,  
            FilterChain chain) throws IOException, ServletException {  
        FilterInvocation fi = new FilterInvocation(request, response, chain);  
        invoke(fi);  
    }  
    
    /**
     * 过滤器链，
     * @param fi  HTTP过滤器集合
     * @throws IOException
     * @throws ServletException
     */
    private void invoke(FilterInvocation fi) throws IOException, ServletException {  
    	InterceptorStatusToken token = null;
    	
    	if(null == SecurityContextHolder.getContext().getAuthentication()) {
    		throw new AccessDeniedException("未登录!");
    	}
    	
        try {  
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());  
        } finally {  
            super.afterInvocation(token, null);  
        }  
    }  
  
    public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {  
        return null;  
    }  
  
    public void init(FilterConfig arg0) throws ServletException {  

    }  
      
    public void destroy() {  
    }
    
    public void afterPropertiesSet() throws Exception {
        logger.debug("Validated configuration attributes");
    }
  
    @Override  
    public Class<? extends Object> getSecureObjectClass() {  
        return FilterInvocation.class;  
    }

	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return null;
	}

}
