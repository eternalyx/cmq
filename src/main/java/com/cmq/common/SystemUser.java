package com.cmq.common;

public class SystemUser {

    /**
     * 用户id
     */
    private Integer id;

    /**
     * 登录名：用户手机号
     */
    private String username;

    /**
     * 用户真实姓名
     */
    private String realName;

    public SystemUser(Integer id, String username) {
        this.id = id;
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
