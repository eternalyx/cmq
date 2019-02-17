package com.tcm.questionnaire.service.impl;

import com.tcm.questionnaire.mapper.TestMapper;
import com.tcm.questionnaire.po.UserPO;
import com.tcm.questionnaire.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("testService")
public class TestServiceImpl implements TestService {

    @Resource
    private TestMapper testMapper;

    @Override
    public UserPO selectById(Integer id) {
        return testMapper.selectById(id);
    }
}
