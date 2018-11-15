package com.luming.config;

import com.luming.dao.PermissionJpa;
import com.luming.dao.RoleJpa;
import com.luming.dao.UserJpa;
import com.luming.model.VO.UserVO;
import com.luming.model.pojo.PermissionDO;
import com.luming.model.pojo.RoleDO;
import com.luming.model.pojo.UserDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ming.lu@insentek.com
 * @date 2018/11/6
 * 修正历史：
 * 	2018/11/6：文件创建
 */
@Component
public class AdminUserDetailService implements UserDetailsService {
    Logger log = LoggerFactory.getLogger(AdminUserDetailService.class);
    
    @Autowired
    UserJpa userJpa;
    
    @Autowired
    RoleJpa roleJpa;
    
    @Autowired
    PermissionJpa permissionJpa;
    
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDO user = userJpa.findUserDOByEmail(username);
        RoleDO role;
        List<PermissionDO> list;
        if (user == null) {
            throw new UsernameNotFoundException("用户名：" + username + "不存在！");
        } else {
            role = roleJpa.findRoleDOById(user.getId());
            if (role == null) {
                throw new UsernameNotFoundException("roleName " + username + " not found");
            } else {
                list = permissionJpa.findPermissionListByRoleId(role.getId());
            }
        }
        String password = user.getPassword() + "@" + user.getSalt();
        log.info(password);
        user.setPassword(password);
        return new UserVO(user, role, list);
    }
}
