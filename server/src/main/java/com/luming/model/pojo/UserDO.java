package com.luming.model.pojo;

import javax.persistence.*;

/**
 * 用户表
 *
 * @author ming.lu@insentek.com
 * @date 2018/3/14
 * 修正历史：
 * 	2018/3/14：文件创建
 */
@Entity
@Table(name = "tb_user")
public class UserDO {
    @Id
    @GeneratedValue
    Integer id;
    String name;
    String email;
    String password;
    String salt;
    String mobile;
    Integer age;
    Integer roleId;
    
    public UserDO() {
    }
    
    public UserDO(Integer id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getSalt() {
        return salt;
    }
    
    public void setSalt(String salt) {
        this.salt = salt;
    }
    
    public String getMobile() {
        return mobile;
    }
    
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    
    public Integer getAge() {
        return age;
    }
    
    
    public void setAge(Integer age) {
        this.age = age;
    }
    
    
    public Integer getRoleId() {
        return roleId;
    }
    
    
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
