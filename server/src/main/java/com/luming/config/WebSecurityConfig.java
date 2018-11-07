package com.luming.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luming.model.ResultVO;
import com.luming.util.Sha1HexUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author ming.lu@insentek.com
 * @date 2018/11/5
 * 修正历史：
 * 	2018/11/5：文件创建
 */
@EnableWebSecurity
@Configuration
/**
 * 开启security注解
  */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(-1)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    public MyUserDetailService userDetailsService;
    
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Sha1HexUtil();
    }
    
    @Autowired
    protected void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(this.passwordEncoder());
    }
    /**
     * http请求安全处理
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 通过authorizeRequests()方法来开始请求权限配置
        http.authorizeRequests()
                // 我们指定任何用户都可以访问多个URL的模式。
                // 任何用户都可以访问以"/resources/","/signup", 或者 "/about"开头的URL。
                .antMatchers("/api/admin/**").permitAll()
                // 以 "/admin/" 开头的URL只能让拥有 "ROLE_ADMIN"角色的用户访问。
                // 请注意我们使用 hasRole 方法，没有使用 "ROLE_" 前缀。
//                .antMatchers("/api/**").hasRole("USER")
                
                // 任何以"/api/" 开头的URL需要同时具有 "ROLE_ADMIN" 和 "ROLE_DBA"权限的用户才可以访问。
                // 和上面一样我们的 hasRole 方法也没有使用 "ROLE_" 前缀。
//                .antMatchers("/admin/**").access("hasRole('ADMIN') and hasRole('DBA')")
        
                // 尚未匹配的任何URL都要求用户进行身份验证
                .anyRequest().authenticated()
                .and()
                // 指定登录页的路径
                // formLogin().permitAll()方法允许所有用户基于表单登录访问/login登录页
                .formLogin().loginPage("http://localhost:3000/login")
                .loginProcessingUrl("/api/admin/login")
                // 登录成功后跳转到/main页面
//                .defaultSuccessUrl("/admin/main")
                // 登录失败后跳转到/login?error页面
//                .failureUrl("http://localhost:3000/login?status=false")
                // 登录失败的方法
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req,
                                                        HttpServletResponse resp,
                                                        AuthenticationException e) throws IOException {
                        resp.setContentType("application/json;charset=utf-8");
                        ResultVO resultVO;
                        if (e instanceof BadCredentialsException ||
                                e instanceof UsernameNotFoundException) {
                            resultVO = ResultVO.error(null,"账户名或者密码输入错误!");
                        } else if (e instanceof LockedException) {
                            resultVO = ResultVO.error(null,"账户被锁定，请联系管理员!");
                        } else if (e instanceof CredentialsExpiredException) {
                            resultVO = ResultVO.error(null,"密码过期，请联系管理员!");
                        } else if (e instanceof AccountExpiredException) {
                            resultVO = ResultVO.error(null,"账户过期，请联系管理员!");
                        } else if (e instanceof DisabledException) {
                            resultVO = ResultVO.error(null,"账户被禁用，请联系管理员!");
                        } else {
                            resultVO = ResultVO.error(null,"登录失败!");
                        }
                        ObjectMapper om = new ObjectMapper();
                        PrintWriter out = resp.getWriter();
                        out.write(om.writeValueAsString(resultVO));
                        out.flush();
                        out.close();
                    }
                })
                // 登录成功的方法
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req,
                                                        HttpServletResponse resp,
                                                        Authentication auth) throws IOException {
                        resp.setContentType("application/json;charset=utf-8");
                        ResultVO resultVO = ResultVO.success(null, auth.getDetails());
                        ObjectMapper om = new ObjectMapper();
                        PrintWriter out = resp.getWriter();
                        out.write(om.writeValueAsString(resultVO));
                        out.flush();
                        out.close();
                    }
                })
                .permitAll()
                .and()
                //开启cookie储存用户信息，并设置有效期为14天，指定cookie中的密钥
//                .rememberMe().tokenValiditySeconds(1209600).key("Authentication")
//                .and()
                .logout()
                //指定登出的url
                .logoutUrl("/logout")
                //指定登出成功之后跳转的url
                .logoutSuccessUrl("/")
                .permitAll()
                .and()
                .cors()
                .and()
                .csrf().disable();
    
    }
    
    /**
     * 需要配置这个支持password模式 support password grant type
     *
     * @return
     * @throws Exception
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
}
