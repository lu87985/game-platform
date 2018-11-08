package com.luming.config;

import com.luming.dao.RoleDao;
import com.luming.dao.UserDao;
import com.luming.model.VO.UserVO;
import com.luming.model.pojo.RoleDO;
import com.luming.model.pojo.UserDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

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
    UserDao userDao;
    
    @Autowired
    RoleDao roleDao;
    
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDO user = userDao.findUserDOByEmail(username);
        RoleDO role;
        if (user == null) {
            throw new UsernameNotFoundException("用户名：" + username + "不存在！");
        } else {
            role = roleDao.findRoleDOById(user.getId());
            if (role == null) {
                throw new UsernameNotFoundException("roleName " + username + " not found");
            }
        }
        String password = user.getPassword() + "@" + user.getSalt();
        log.info(password);
        user.setPassword(password);
        return new UserVO(user, role);
    }
}
