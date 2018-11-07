package com.luming.config;

import com.luming.dao.RoleDao;
import com.luming.dao.UserDao;
import com.luming.model.pojo.RoleDO;
import com.luming.model.pojo.UserDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;

/**
 * @author ming.lu@insentek.com
 * @date 2018/11/6
 * 修正历史：
 * 	2018/11/6：文件创建
 */
@Component
public class MyUserDetailService implements UserDetailsService {
    Logger log = LoggerFactory.getLogger(MyUserDetailService.class);
    
    @Autowired
    UserDao userDao;
    
    @Autowired
    RoleDao roleDao;
    
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDO userEntity = userDao.findUserDOByEmail(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("用户名：" + username + "不存在！");
        }
        String password = userEntity.getPassword();
//        String password = DigestUtils.sha1Hex(userEntity.getPassword());
        log.info(password);
        
        
        Collection<SimpleGrantedAuthority> collection = new HashSet<SimpleGrantedAuthority>();
        RoleDO roleDO = roleDao.findRoleDOById(userEntity.getId());
        collection.add(new SimpleGrantedAuthority(roleDO.getRoleName()));
        /*return new User(username, password, AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN"));*/
        return new User(username, password, collection);
    }
    
}
