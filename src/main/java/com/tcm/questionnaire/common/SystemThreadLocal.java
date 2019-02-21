package com.tcm.questionnaire.common;

public class SystemThreadLocal {

    private SystemThreadLocal() {
    }

    private static final ThreadLocal<SystemUser> SYSTEM_USER = new ThreadLocal<>();

    public static void setSystemUserId(Integer userId){
        SYSTEM_USER.set(new SystemUser(userId));
    }

    public static void setSystemUser(SystemUser user){
        SYSTEM_USER.set(user);
    }

    public static Integer getSystemUserId(){
        return SYSTEM_USER.get().getUserId();
    }
}
