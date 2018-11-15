package com.luming.config.oauth2;

import com.luming.config.message.AuthExceptionEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author ming.lu@insentek.com
 * @date 2018/11/15
 * 修正历史：
 * 	2018/11/15：文件创建
 */
@Configuration
@EnableResourceServer
public class RewirteResourceServerConfigurerAdapter extends ResourceServerConfigurerAdapter {
    
    @Autowired
    protected AuthenticationSuccessHandler loginAuthenticationSuccessHandler;
    
    @Autowired
    protected AuthenticationFailureHandler loginAuthenticationFailureHandler;
    
    //    @Override
    //    public void configure(HttpSecurity http) throws Exception {
    //
    //        http
    //                // 手机验证码登录
    //                .apply(smsCodeAuthenticationSecurityConfig)
    //                .and()
    //                .authorizeRequests()
    //                //手机验证码登录地址
    //                .antMatchers("/mobile/token", "/email/token")
    //                .permitAll()
    //                .and()
    //                .authorizeRequests()
    //                .antMatchers(
    //                        "/register",
    //                        "/social/**",
    //                        "/**/*.js",
    //                        "/**/*.css",
    //                        "/**/*.jpg",
    //                        "/**/*.png",
    //                        "/**/*.woff2",
    //                        "/code/image")
    //                .permitAll()//以上的请求都不需要认证
    //                .anyRequest()
    //                .authenticated()
    //                .and()
    //                .csrf().disable();
    //    }
    
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                //登录页面，app用不到
                //.loginPage("/authentication/login")
                //登录提交action，app会用到
                // 用户名登录地址
//                .loginProcessingUrl("//token")
                //成功处理器 返回Token
                .successHandler(loginAuthenticationSuccessHandler)
                //失败处理器
                .failureHandler(loginAuthenticationFailureHandler).and()
                .exceptionHandling().authenticationEntryPoint(new AuthExceptionEntryPoint());
        http.requestMatchers()
                .antMatchers("/api/**")
                .and()
                .authorizeRequests()
                .antMatchers("/api/**")
                .authenticated();
    }
    
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.authenticationEntryPoint(new AuthExceptionEntryPoint());
    }
}
