package com.tcm.questionnaire.mapper;

import com.tcm.questionnaire.po.UserPO;

public interface UserMapper {

    UserPO selectByUsername(String username);
}
