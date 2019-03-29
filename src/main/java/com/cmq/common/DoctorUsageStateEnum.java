package com.cmq.common;

public enum DoctorUsageStateEnum {

    ENABLED("ENABLED", "已启用"),

    NOT_ENABLED("NOT_ENABLED", "未启用")

    ;

    private String databaseCode;

    private String desc;

    DoctorUsageStateEnum(String databaseCode, String desc) {
        this.databaseCode = databaseCode;
        this.desc = desc;
    }

    public String getDatabaseCode() {
        return databaseCode;
    }

    public String getDesc() {
        return desc;
    }
}
