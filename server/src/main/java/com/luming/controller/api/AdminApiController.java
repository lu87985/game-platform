package com.luming.controller.api;

import com.luming.controller.handler.SecurityUserInfoHandler;
import com.luming.model.ResultVO;
import com.luming.model.VO.UserVO;
import com.luming.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ming.lu@insentek.com
 * @date 2018/11/6
 * 修正历史：
 * 	2018/11/6：文件创建
 */
@RequestMapping("/user")
@RestController
public class AdminApiController {
    
    @Autowired
    private UserService userService;
    
    
    /**
     * 当前登录人信息
     * @return
     */
    @RequestMapping(value = "info", method = RequestMethod.GET)
    public ResultVO userInfo() {
        ResultVO resultVO;
        UserVO user = SecurityUserInfoHandler.getCurrentPrincipal();
        if (user == null) {
            resultVO = ResultVO.error(null, "失败");
        }else {
            resultVO = ResultVO.success("成功", user);
        }
        return resultVO;
    }
    
    
    /**
     * 注册用户
     * @param name
     * @param password
     * @param email
     * @return
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ResultVO registerUser(@RequestParam String name, @RequestParam String password, @RequestParam String email) {
        ResultVO resultVO;
        Boolean result = userService.saveUserDo(name, email, password);
        if (result) {
            resultVO = ResultVO.success(null, "成功");
        }else {
            resultVO = ResultVO.error(null, "失败");
        }
        return resultVO;
    }
    
}
