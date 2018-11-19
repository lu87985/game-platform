package com.luming.controller.handler;

import com.luming.dao.PermissionJpa;
import com.luming.dao.RoleJpa;
import com.luming.dao.UserJpa;
import com.luming.model.pojo.PermissionDO;
import com.luming.model.pojo.RoleDO;
import com.luming.model.pojo.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author ming.lu@insentek.com
 * @date 2018/11/19
 * 修正历史：
 * 	2018/11/19：文件创建
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    
    @Autowired
    UserJpa userJpa;
    
    @Autowired
    RoleJpa roleJpa;
    
    @Autowired
    PermissionJpa permissionJpa;
    
    @Override
    public Authentication authenticate(Authentication authentication) {
        
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
    
        UserDO user = userJpa.findUserDOByEmail(name);
        RoleDO role;
        List<PermissionDO> list;
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        if (user == null) {
            throw new AuthenticationCredentialsNotFoundException("Account is not found.");
        } else {
            role = roleJpa.findRoleDOById(user.getId());
            if (role == null) {
                throw new UsernameNotFoundException("Account is not found.");
            } else {
                list = permissionJpa.findPermissionListByRoleId(role.getId());
                if (list != null && list.size() > 0) {
                    for (PermissionDO permissionDO: list) {
                        GrantedAuthority authority = new SimpleGrantedAuthority(permissionDO.getUrl());
                        authorities.add(authority);
                    }
                }
            }
        }
        return new UsernamePasswordAuthenticationToken(name, password, authorities);
    }
    
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
