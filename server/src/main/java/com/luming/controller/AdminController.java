package com.luming.controller;

import com.luming.model.ResultVO;
import com.luming.model.pojo.UserDO;
import com.luming.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    
    @Autowired
    private UserService userService;
    
    /**
     * 登录
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResultVO login(@RequestParam String email, @RequestParam String password) {
        ResultVO resultVO = new ResultVO();
        UserDO user = userService.login(email, password);
        if (user == null) {
            resultVO.setStatus(false);
            resultVO.setData("邮箱或密码不正确");
        }else {
            
            resultVO.setStatus(true);
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
        ResultVO resultVO = new ResultVO();
        Boolean result = userService.saveUserDo(name, email, password);
        if (result) {
            resultVO.setStatus(true);
        }else {
            resultVO.setStatus(false);
        }
        return resultVO;
    }
}
