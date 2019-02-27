package com.tcm.questionnaire.common;

public class SystemThreadLocal {

    private SystemThreadLocal() {
    }

    private static final ThreadLocal<SystemUser> SYSTEM_USER = new ThreadLocal<>();

    public static void setSystemUser(SystemUser user){
        SYSTEM_USER.set(user);
    }

    public static SystemUser getSystemUser(){
        return SYSTEM_USER.get();
    }
}
