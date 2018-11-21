package com.luming.controller.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luming.model.ResultVO;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录失败执行的方法
 * @author ming.lu@insentek.com
 * @date 2018/11/15
 * 修正历史：
 * 	2018/11/15：文件创建
 */
@Component("loginAuthenticationFailureHandler")
public class LoginAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    
    @Override
    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException {
        resp.setContentType("application/json;charset=utf-8");
        ResultVO resultVO;
        if (e instanceof BadCredentialsException || e instanceof UsernameNotFoundException) {
            resultVO = ResultVO.error(null, "账户名或者密码输入错误!");
        } else if (e instanceof LockedException) {
            resultVO = ResultVO.error(null, "账户被锁定，请联系管理员!");
        } else if (e instanceof CredentialsExpiredException) {
            resultVO = ResultVO.error(null, "密码过期，请联系管理员!");
        } else if (e instanceof AccountExpiredException) {
            resultVO = ResultVO.error(null, "账户过期，请联系管理员!");
        } else if (e instanceof DisabledException) {
            resultVO = ResultVO.error(null, "账户被禁用，请联系管理员!");
        } else {
            resultVO = ResultVO.error(null, "登录失败!");
        }
        ObjectMapper om = new ObjectMapper();
        PrintWriter out = resp.getWriter();
        out.write(om.writeValueAsString(resultVO));
        out.flush();
        out.close();
    }
}
