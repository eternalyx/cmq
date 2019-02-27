package com.tcm.questionnaire.common;

public class SystemUser {

    private Integer id;

    private String username;

    private String mobile;

    public SystemUser(Integer id, String mobile) {
        this.id = id;
        this.mobile = mobile;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
