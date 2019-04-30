package cn.itzishu.sum.vo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Result<T>{

    //404为没有发现账户，200为成功，其它为未知错误
    private Integer code;
    //经过加密的密码
    private String enPasswd;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private T result;

    public String getEnPasswd() {
        return enPasswd;
    }

    public void setEnPasswd(String enPasswd) {
        this.enPasswd = enPasswd;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
