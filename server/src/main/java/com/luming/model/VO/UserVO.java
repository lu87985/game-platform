package com.luming.model.VO;

import com.luming.model.pojo.PermissionDO;
import com.luming.model.pojo.RoleDO;
import com.luming.model.pojo.UserDO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author ming.lu@insentek.com
 * @date 2018/11/7
 * 修正历史：
 * 	2018/11/7：文件创建
 */
public class UserVO implements UserDetails {
    
    Integer id;
    String name;
    String email;
    String password;
    String mobile;
    Integer age;
    RoleDO roleDO;
    List<PermissionDO> permissionList;
    
    public UserVO(UserDO user) {
        if (user != null) {
            this.setId(user.getId());
            this.setName(user.getName());
            this.setEmail(user.getEmail());
            this.setPassword(user.getPassword());
            this.setMobile(user.getMobile());
            this.setAge(user.getAge());
        }
    }
    
    public UserVO(UserDO user, RoleDO role, List<PermissionDO> permissionList) {
        if (user != null) {
            this.setId(user.getId());
            this.setName(user.getName());
            this.setEmail(user.getEmail());
            this.setPassword(user.getPassword());
            this.setMobile(user.getMobile());
            this.setAge(user.getAge());
        }
        if (role != null) {
            this.roleDO = role;
        }
        if (permissionList != null && permissionList.size() > 0) {
            this.permissionList = permissionList;
        }
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
    
    
    public void setPassword(String password) {
        this.password = password;
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
    
    
    public RoleDO getRoleDO() {
        return roleDO;
    }
    
    
    public void setRoleDO(RoleDO roleDO) {
        this.roleDO = roleDO;
    }
    
    
    public List<PermissionDO> getPermissionList() {
        return permissionList;
    }
    
    
    public void setPermissionList(List<PermissionDO> permissionList) {
        this.permissionList = permissionList;
    }
    
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        if (permissionList != null && permissionList.size() > 0) {
            for (PermissionDO permissionDO: permissionList) {
                GrantedAuthority authority = new SimpleGrantedAuthority(permissionDO.getUrl());
                authorities.add(authority);
            }
        }
        return authorities;
    }
    
    @Override
    public String getPassword() {
        return this.password;
    }
    
    
    @Override
    public String getUsername() {
        return this.email;
    }
    
    @Override
    /**
     * 判断账号是否已经过期，默认没有过期
     */
    public boolean isAccountNonExpired() {
        return true;
    }
    
    @Override
    /**
     * 判断账号是否被锁定，默认没有锁定
     */
    public boolean isAccountNonLocked() {
        return true;
    }
    
    @Override
    /**
     * 判断信用凭证是否过期，默认没有过期
     */
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    @Override
    /**
     * 判断账号是否可用，默认可用
     */
    public boolean isEnabled() {
        return true;
    }
}
