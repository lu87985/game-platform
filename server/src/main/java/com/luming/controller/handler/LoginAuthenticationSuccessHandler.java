package com.luming.controller.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;

/**
 * 登录成功执行的方法
 * @author ming.lu@insentek.com
 * @date 2018/11/15
 * 修正历史：
 * 	2018/11/15：文件创建
 */
@Component("loginAuthenticationSuccessHandler")
public class LoginAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    
    @Autowired
    private ClientDetailsService clientDetailsService;
    
    @Autowired
    private AuthorizationServerTokenServices authorizationServerTokenServices;
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String header = request.getHeader("Authorization");
    
        if (header == null || !header.startsWith("Basic ")) {
            throw new UnapprovedClientAuthenticationException("请求头中无client信息");
        }
    
        String[] tokens = extractAndDecodeHeader(header, request);
        assert tokens.length == 2;
    
        String clientId = tokens[0];
        String clientSecret = tokens[1];
    
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
    
        if (clientDetails == null) {
            throw new UnapprovedClientAuthenticationException("clientId对应的配置信息不存在：" + clientId);
        } else if (clientSecret != null && clientSecret.equals(clientDetails.getClientSecret())) {
            throw new UnapprovedClientAuthenticationException("clientSecret不匹配：" + clientId);
        }
    
        TokenRequest tokenRequest = new TokenRequest(new HashMap<String, String>(), clientId, clientDetails.getScope(), "custom");
    
        OAuth2Request auth2Request = tokenRequest.createOAuth2Request(clientDetails);
    
    
        OAuth2Authentication auth2Authentication = new OAuth2Authentication(auth2Request, authentication);
    
        OAuth2AccessToken token = authorizationServerTokenServices.createAccessToken(auth2Authentication);
    
        response.setContentType("application/json;charset=UTF-8");
        ObjectMapper om = new ObjectMapper();
        response.getWriter().write(om.writeValueAsString(token));
    }
    
    private String[] extractAndDecodeHeader(String header, HttpServletRequest request) throws IOException {
        
        byte[] base64Token = header.substring(6).getBytes("UTF-8");
        byte[] decoded;
        try {
            decoded = Base64.getDecoder().decode(base64Token);
        } catch (IllegalArgumentException e) {
            throw new BadCredentialsException("Failed to decode basic authentication token");
        }
        String token = new String(decoded, "UTF-8");
        int delim = token.indexOf(":");
        if (delim == -1) {
            throw new BadCredentialsException("Invalid basic authentication token");
        }
        return new String[] { token.substring(0, delim), token.substring(delim + 1) };
    }

}
