package com.luming.config.session;

import org.apache.catalina.security.SecurityConfig;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * @author lei.jia@insentek.com
 * @date 2018/11/16
 * 修正历史：
 * 	2018/11/16：文件创建
 */


/**
 * 与springsecurity整合配置将Servlet容器初始化
 */
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {
    
    public SecurityInitializer() {
        super(SecurityConfig.class, LettuceConfig.class);
    }
}