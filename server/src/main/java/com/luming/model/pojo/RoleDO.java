package com.luming.model.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户表
 *
 * @author ming.lu@insentek.com
 * @date 2018/3/14
 * 修正历史：
 * 	2018/3/14：文件创建
 */
@Entity
@Table(name = "tb_role")
public class RoleDO {
    @Id
    @GeneratedValue
    Integer id;
    String roleName;
    
    
    public RoleDO() {
    }
    
    
    public RoleDO(Integer id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }
    
    
    public Integer getId() {
        return id;
    }
    
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    
    public String getRoleName() {
        return roleName;
    }
    
    
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
