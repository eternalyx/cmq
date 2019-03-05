package com.cmq.common;

public class CmqSystem {

    private CmqSystem() { }

    private static final ThreadLocal<SystemUser> CURRENT_LOGGED_IN_USER = new ThreadLocal<>();

    public static void setCurrentLoggedInUser(SystemUser user){
        CURRENT_LOGGED_IN_USER.set(user);
    }

    public static SystemUser getCurrentLoggedInUser(){
        return CURRENT_LOGGED_IN_USER.get();
    }
}
