package com.luming.filter;

import com.luming.config.RewriteAccessDecisionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author ming.lu@insentek.com
 * @date 2018/11/15
 * 修正历史：
 * 	2018/11/15：文件创建
 */
@Service
public class RewriteFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {
    
    @Autowired
    private FilterInvocationSecurityMetadataSource securityMetadataSource;
    
    @Autowired
    public void setMyAccessDecisionManager(RewriteAccessDecisionManager rewriteAccessDecisionManager) {
        super.setAccessDecisionManager(rewriteAccessDecisionManager);
    }
    
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        FilterInvocation filterInvocation = new FilterInvocation(request, response, chain);
        invoke(filterInvocation);
    }
    
    
    public void invoke(FilterInvocation filterInvocation) throws IOException, ServletException {
        //  filterInvocation里面有一个被拦截的url
        //  里面调用InvocationSecurityMetadataSourceService的getAttributes(Object object)这个方法获取filterInvocation对应的所有权限
        //  再调用RewriteAccessDecisionManager的decide方法来校验用户的权限是否足够
        InterceptorStatusToken token = super.beforeInvocation(filterInvocation);
        try {
            //  执行下一个拦截器
            filterInvocation.getChain().doFilter(filterInvocation.getRequest(), filterInvocation.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }
    }
    
    
    @Override
    public void destroy() {
    
    }
    
    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
        
    }
    
    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.securityMetadataSource;
    }
}
