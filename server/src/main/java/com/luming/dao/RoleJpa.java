package com.luming.dao;

import com.luming.model.pojo.RoleDO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author ming.lu@insentek.com
 * @date 2018/11/6
 * 修正历史：
 * 	2018/11/6：文件创建
 */
public interface RoleJpa extends JpaRepository<RoleDO, Integer> {

    RoleDO findRoleDOById(Integer userId);
}
