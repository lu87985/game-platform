package com.luming.dao;

import com.luming.model.pojo.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ming.lu@insentek.com
 * @date 2018/3/14
 * 修正历史：
 * 	2018/3/14：文件创建
 */
public interface UserDao extends JpaRepository<UserDO, Integer> {

    UserDO findUserDOByEmailAndPassword(String name, String password);
}
