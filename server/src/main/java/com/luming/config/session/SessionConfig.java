package com.luming.config.session;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lei.jia@insentek.com
 * @date 2018/11/16
 * 修正历史：
 * 	2018/11/16：文件创建
 */

//自己封装的对session的操作
public class SessionConfig {

    
    //获取完整的session内容
    public static JSONObject getSession(HttpServletRequest request) {
        String token = getSessionToken(request);
        if (checkSessionExist(request, token)) {
            return getRealSession(request, token);
        }  else {
            return null;
        }
    }
    
    //将Session的Json格式作为内容存入Redis,cover选择是覆盖还是追加
    public static void setSession(HttpServletRequest request, JSONObject content, boolean cover) {
        String token = getSessionToken(request);
        if (cover) {
            request.getSession().setAttribute(token, content);
        } else {
            JSONObject jsonObject = getRealSession(request, token);
            for(String eachKey : content.keySet()) {
                jsonObject.put(eachKey, content.getString(eachKey));
            }
            request.getSession().setAttribute(token, jsonObject);
        }
        
    }
    
    
    
    //给session存储key，value
    public static void setSessionValue(HttpServletRequest request, String key, String value) {
        String token = getSessionToken(request);
        JSONObject content = getSessionCreate(request, true);
        content.put(key, value);
        request.getSession().setAttribute(token, content);
    }
    
    //获取s0ession的某个key的值
    public static String getSessionValue(HttpServletRequest request, String key) {
        JSONObject content = getSessionCreate(request, true);
        if (content.keySet().size() != 0) {
            return content.getString(key);
        }
        return null;
    }
    
    
    //删除session的某个key
    public static void removeSessionValue(HttpServletRequest request, String key) {
        String token = getSessionToken(request);
        JSONObject content = getSessionCreate(request, true);
        content.remove(key);
        request.getSession().setAttribute(token, content);
    }
    
    
    //删除session
    public static void removeSession (HttpServletRequest request) {
        String token = getSessionToken(request);
        if (checkSessionExist(request, token)) {
            request.getSession().removeAttribute(token);
        }
    }
    
    //当调用的时候如果Session不存在，自动创建
    private static JSONObject getSessionCreate(HttpServletRequest request, boolean create) {
        String token = getSessionToken(request);
        if (checkSessionExist(request, token)) {
            return getRealSession(request, token);
        }  else {
            if (create) {
                request.getSession().setAttribute(token, new JSONObject());
                return new JSONObject();
            }
        }
        return null;
    }
    
    //获取session的token（作为此session的key）
    private static String getSessionToken (HttpServletRequest request) {
       return request.getHeader("token");
    }
    
    //直接获取session（无逻辑处理，已经经过判断此session存在）
    private static JSONObject getRealSession (HttpServletRequest request, String token) {
        return JSONObject.parseObject(request.getSession().getAttribute(token).toString());
    }
    
    //判断session是否已经存在
    private static boolean checkSessionExist (HttpServletRequest request, String token) {
        Object content = request.getSession().getAttribute(token);
        if (StringUtils.isEmpty(content)) {
           return false;
        }
        return true;
    }
    
}
