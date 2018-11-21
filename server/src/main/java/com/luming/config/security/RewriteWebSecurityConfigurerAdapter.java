package com.luming.config.security;

import com.luming.config.AdminUserDetailService;
import com.luming.util.Sha1HexUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

/**
 * @author ming.lu@insentek.com
 * @date 2018/11/15
 * 修正历史：
 * 	2018/11/15：文件创建
 */
@Configuration
@EnableWebSecurity
public class RewriteWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
    
    @Autowired
    public AdminUserDetailService adminUserDetailService;
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Sha1HexUtil();
    }
    
    /**
     * 数据库验证
     * 创建DaoAuthenticationProvider认证的bean
     * @return
     */
    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        //加密用的
        daoAuthenticationProvider.setPasswordEncoder(this.passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(adminUserDetailService);
        return daoAuthenticationProvider;
    }
    
    /**
     * 认证
     *
     * @return
     * @throws Exception
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        //会进行多种方式认证，当第一种不成功时会进行第二种认证
        //多个认证方式
        ProviderManager authenticationManager = new ProviderManager(Arrays.asList(daoAuthenticationProvider()));
        //不擦除认证密码，擦除会导致TokenBasedRememberMeServices因为找不到Credentials再调用UserDetailsService而抛出UsernameNotFoundException
        //验证后设置擦除凭证
        authenticationManager.setEraseCredentialsAfterAuthentication(false);
        return authenticationManager;
    }
    
    /**
     * http请求安全处理
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 通过authorizeRequests()方法来开始请求权限配置
        http.requestMatchers().antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers("/**").authenticated();
    }
}
