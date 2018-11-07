package com.luming.controller.api;

import com.luming.model.ResultVO;
import com.luming.model.pojo.UserDO;
import com.luming.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ming.lu@insentek.com
 * @date 2018/11/6
 * 修正历史：
 * 	2018/11/6：文件创建
 */
@RequestMapping("/api/admin/")
public class AdminApiController {
    
    
    @Autowired
    private UserService userService;
    
    /**
     * 登录
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResultVO login(@RequestParam String username, @RequestParam String password) {
        ResultVO resultVO;
        UserDO user = userService.login(username, password);
        if (user == null) {
            resultVO = ResultVO.error(null, "失败");
        }else {
            resultVO = ResultVO.success(null, "成功");
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
