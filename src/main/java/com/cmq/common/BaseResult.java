package com.cmq.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class BaseResult implements Serializable {
    private static final long serialVersionUID = 902279251229677186L;

    private String code;

    private String message;

    private Map<String, Object> data;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public BaseResult() {
        success();
    }

    public BaseResult success(){
        this.code = "200";
        return this;
    }

    public BaseResult success(String message){
        success();
        this.message = message;
        return this;
    }

    public BaseResult fail(){
        this.code = "500";
        return this;
    }

    public BaseResult fail(String message){
        fail();
        this.message = message;
        return this;
    }

    public BaseResult data(String key, Object value){
        if(null == data){
            this.data = new HashMap<>();
        }

        this.data.put(key, value);
        return this;
    }
}
