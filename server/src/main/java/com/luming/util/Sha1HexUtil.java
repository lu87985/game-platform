package com.luming.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author ming.lu@insentek.com
 * @date 2018/11/6
 * 修正历史：
 * 	2018/11/6：文件创建
 */
public class Sha1HexUtil implements PasswordEncoder{
    @Override
    public String encode(CharSequence rawPassword) {
        return DigestUtils.sha1Hex(rawPassword.toString());
    }
    
    
    /**
     * 判断登录密码是否一致
     * @param rawPassword
     * @param encodedPassword
     * @return
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (DigestUtils.sha1Hex(rawPassword.toString()).equals(encodedPassword)) {
            return true;
        } else {
            return false;
        }
    }
}
