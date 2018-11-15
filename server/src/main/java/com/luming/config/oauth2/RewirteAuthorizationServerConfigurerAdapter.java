package com.luming.config.oauth2;

import com.luming.config.AdminUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * @author ming.lu@insentek.com
 * @date 2018/11/15
 * 修正历史：
 * 	2018/11/15：文件创建
 */
@Configuration
@EnableAuthorizationServer
public class RewirteAuthorizationServerConfigurerAdapter extends AuthorizationServerConfigurerAdapter {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AdminUserDetailService userDetailsService;
    @Autowired
    private TokenStore tokenStore;
    @Autowired
    private WebResponseExceptionTranslator customWebResponseExceptionTranslator;
    
    @Autowired
    public RewirteAuthorizationServerConfigurerAdapter(AdminUserDetailService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .exceptionTranslator(customWebResponseExceptionTranslator);
    }
    
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                //配置内存中，也可以是数据库
                .withClient("test")
                .secret("7c4a8d09ca3762af61e59520943dc26494f8941b")
                // 选择授权模式
                .authorizedGrantTypes("refresh_token", "password")
                //限制允许的权限配置
                .scopes("all")
                .resourceIds("oauth2-resource")
                //token有效时间  秒
                .accessTokenValiditySeconds(1200)
                .refreshTokenValiditySeconds(50000);
    }
    
    @Bean
    public TokenStore tokenStore() {
        //使用内存的tokenStore
        return new InMemoryTokenStore();
    }
}
