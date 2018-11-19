package com.luming.controller.handler;

import com.luming.model.VO.UserVO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author ming.lu@insentek.com
 * @date 2018/11/15
 * 修正历史：
 * 	2018/11/15：文件创建
 */
public class SecurityUserInfoHandler {
    /**
     * 获取当前用户
     * @return
     */
    public static Authentication getCurrentUserAuthentication(){
        System.out.println("用户信息 : " + SecurityContextHolder.getContext().getAuthentication());
        return SecurityContextHolder.getContext().getAuthentication();
    }
    
    /**
     * 获取当前用户
     * @return
     */
    public static UserVO getCurrentPrincipal(){
        UserVO userVO = (UserVO) getCurrentUserAuthentication().getPrincipal();
        userVO.setPassword(null);
        return userVO;
    }
}
