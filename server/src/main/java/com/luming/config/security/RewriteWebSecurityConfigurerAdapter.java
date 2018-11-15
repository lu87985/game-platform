package com.luming.config.security;

import com.luming.config.AdminUserDetailService;
import com.luming.dao.UserJpa;
import com.luming.filter.RewritrFilterSecurityInterceptor;
import com.luming.util.Sha1HexUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

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
    private UserJpa userDao;
    @Autowired
    public AdminUserDetailService adminUserDetailService;
    
    @Autowired
    private RewritrFilterSecurityInterceptor filterSecurityInterceptor;
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Sha1HexUtil();
    }
    
    @Autowired
    protected void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(adminUserDetailService).passwordEncoder(this.passwordEncoder());
    }
    /**
     * http请求安全处理
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 通过authorizeRequests()方法来开始请求权限配置
        http
                .authorizeRequests()
                .antMatchers("/oauth/**").permitAll()
                .and()
                .addFilterBefore(filterSecurityInterceptor, FilterSecurityInterceptor.class)
                .csrf().disable();
//                        .and()
//                        // 指定登录页的路径
//                        .formLogin().loginPage("http://localhost:3000/login")
//                        .loginProcessingUrl("/api/admin/login")
//                        .failureHandler(new AuthenticationFailureHandler() {
//                            @Override
//                            public void onAuthenticationFailure(HttpServletRequest req,
//                                                                HttpServletResponse resp,
//                                                                AuthenticationException e) throws IOException {
//                                resp.setContentType("application/json;charset=utf-8");
//                                ResultVO resultVO;
//                                if (e instanceof BadCredentialsException ||
//                                        e instanceof UsernameNotFoundException) {
//                                    resultVO = ResultVO.error(null,"账户名或者密码输入错误!");
//                                } else if (e instanceof LockedException) {
//                                    resultVO = ResultVO.error(null,"账户被锁定，请联系管理员!");
//                                } else if (e instanceof CredentialsExpiredException) {
//                                    resultVO = ResultVO.error(null,"密码过期，请联系管理员!");
//                                } else if (e instanceof AccountExpiredException) {
//                                    resultVO = ResultVO.error(null,"账户过期，请联系管理员!");
//                                } else if (e instanceof DisabledException) {
//                                    resultVO = ResultVO.error(null,"账户被禁用，请联系管理员!");
//                                } else {
//                                    resultVO = ResultVO.error(null,"登录失败!");
//                                }
//                                ObjectMapper om = new ObjectMapper();
//                                PrintWriter out = resp.getWriter();
//                                out.write(om.writeValueAsString(resultVO));
//                                out.flush();
//                                out.close();
//                            }
//                        });
        //                // 登录成功的方法
        //                .successHandler(new AuthenticationSuccessHandler() {
        //                    @Override
        //                    public void onAuthenticationSuccess(HttpServletRequest req,
        //                                                        HttpServletResponse resp,
        //                                                        Authentication auth) throws IOException {
        //                        resp.setContentType("application/json;charset=utf-8");
        //                        UserVO user = (UserVO) auth.getPrincipal();
        //                        ResultVO resultVO = ResultVO.success(null, userDao.findUserDOByEmail(user.getUsername()));
        //                        ObjectMapper om = new ObjectMapper();
        //                        PrintWriter out = resp.getWriter();
        //                        out.write(om.writeValueAsString(resultVO));
        //                        out.flush();
        //                        out.close();
        //                    }
        //                })
        //                // 允许所有用户基于表单登录访问/login登录页
        //                .permitAll()
        //                .and()
        //                //开启cookie储存用户信息，并设置有效期为14天，指定cookie中的密钥
        //                .rememberMe().tokenValiditySeconds(1209600).key("Authentication")
        //                .and()
        //                .logout().permitAll()
        //                .and()
        //                .cors()
        //                .and().csrf().disable();
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
