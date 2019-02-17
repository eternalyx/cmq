package com.tcm.questionnaire.service.impl;

import com.tcm.questionnaire.mapper.UserMapper;
import com.tcm.questionnaire.po.UserPO;
import com.tcm.questionnaire.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserPO selectByUsername(String username) {
        return userMapper.selectByUsername(username);
    }
}
