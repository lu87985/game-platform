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
        String password = encodedPassword.split("@")[0];
        String salt = encodedPassword.split("@")[1];
        String pwd = DigestUtils.sha1Hex(salt + rawPassword);
        if (pwd.equals(password)) {
            return true;
        } else {
            return false;
        }
    }
}
