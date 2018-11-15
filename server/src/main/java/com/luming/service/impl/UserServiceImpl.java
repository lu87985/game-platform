package com.luming.service.impl;

import com.luming.dao.UserJpa;
import com.luming.model.pojo.UserDO;
import com.luming.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ming.lu@insentek.com
 * @date 2018/3/14
 * 修正历史：
 * 	2018/3/14：文件创建
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserJpa userDao;
    
    @Override
    public Boolean saveUserDo(String name, String email, String password) {
        try {
            UserDO userDO = new UserDO();
            userDO.setName(name);
            userDO.setEmail(email);
            userDO.setPassword(password);
            userDao.save(userDO);
            return true;
        }catch (Exception e) {
            return false;
        }
    }
    
    
    @Override
    public UserDO login(String email, String password) {
        UserDO userDO = userDao.findUserDOByEmailAndPassword(email, password);
        return userDO;
    }
    
}
