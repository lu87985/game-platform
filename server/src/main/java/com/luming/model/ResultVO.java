package com.luming.model;

/**
 * 异步请求结果类
 *
 * @author ming.lu@insentek.com
 */
public class ResultVO<T> {

    private Integer status;
    private String info;
    private Object data;

    public ResultVO() {
    }

    public ResultVO(Integer status, String info, Object data) {
        this.status = status;
        this.info = info;
        this.data = data;
    }
    
    public static ResultVO success(String info, Object data) {
        return new ResultVO(10000, info, data);
    }
    
    public static ResultVO error(String info, Object data) {
        return new ResultVO(10001, info, data);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
