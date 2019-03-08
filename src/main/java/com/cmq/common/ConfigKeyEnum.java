package com.cmq.common;

public enum ConfigKeyEnum {

    CONTACT_US_INFO("CONTACT_US_INFO", "联系我们")
    ;

    private String key;

    private String desc;

    ConfigKeyEnum(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public String getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }
}
