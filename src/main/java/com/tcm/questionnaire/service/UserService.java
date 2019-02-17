package com.tcm.questionnaire.service;

import com.tcm.questionnaire.po.UserPO;

public interface UserService {

    UserPO selectByUsername(String username);
}
