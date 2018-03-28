package com.luming.filter;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import redis.clients.jedis.Jedis;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author ming.lu@insentek.com
 * @date 2018/3/15
 * 修正历史：
 * 	2018/3/15：文件创建
 */
@Component
@WebFilter(filterName = "adminFilter", urlPatterns = "/")
public class AdminFilter implements Filter {
    
    @Value("${session_key}")
    private String SESSION_KEY;
    @Value("${session_time}")
    private String SESSION_TIME;
    @Value("#{'${checkUrls}'.split(',')}")
    private List<String> checkUrls;
    
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter init");
    }
    
    
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();
        String url = request.getRequestURL().toString();
        AntPathMatcher pathMatcher = new AntPathMatcher();
        for (String pattern : checkUrls) {
            if (pathMatcher.match(pattern, uri)) {
                Jedis jedis = new Jedis();
                String sessionKey = jedis.get(SESSION_KEY);
                if (sessionKey == null) {
                
                }
            }
            System.out.println("filter doFilter");
        }
        
    }
    
    
    @Override
    public void destroy() {
        
    }
}

