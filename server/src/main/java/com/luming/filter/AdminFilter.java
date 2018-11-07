package com.luming.filter;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
    @Value("USER_LOGIN")
    private String SESSION_KEY;
    @Value("7200000")
    private String SESSION_TIME;
    @Value("#{'/'.split(',')}")
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
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, Content-Type");
        response.setHeader("Access-Control-Allow-Credentials", "true");
//        AntPathMatcher pathMatcher = new AntPathMatcher();
//        for (String pattern : checkUrls) {
//            if (pathMatcher.match(pattern, uri)) {
//                Jedis jedis = new Jedis();
//                String sessionKey = jedis.get(SESSION_KEY);
//                if (sessionKey == null) {
//
//                }
//            }
//            System.out.println("filter doFilter");
//        }
        chain.doFilter(servletRequest,servletResponse);
    
    
    }
    
    
    @Override
    public void destroy() {
        
    }
}

