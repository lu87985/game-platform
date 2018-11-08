package com.luming.service;


import com.luming.model.pojo.UserDO;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * @author ming.lu@insentek.com
 * @date 2018/3/14
 * 修正历史：
 * 	2018/3/14：文件创建
 */
@Service
public interface UserService {
    /**
     * 用户注册
     * @param name
     * @param email
     * @param password
     * @return
     */
    Boolean saveUserDo(String name, String email, String password);
    
    
    /**
     * 用户登录
     * @param email
     * @param password
     * @return
     */
    UserDO login(String email, String password);
    
    
    /**
     * 修改用户
     * @param name
     * @param mobile
     * @param age
     * @return
     */
    Boolean updataUser(String name, String mobile, Integer age, Authentication authenticator);
}
