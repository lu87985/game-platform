package com.luming.config.Provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * @author ming.lu@insentek.com
 * @date 2018/11/20
 * 修正历史：
 * 	2018/11/20：文件创建
 */
@Component
public class MobileCodeAuthenticationProvider implements AuthenticationProvider {
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //TODO ming.lu.insentek, 2018/11/21, [] 手机号验证码登录
        return null;
    }
    
    
    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
