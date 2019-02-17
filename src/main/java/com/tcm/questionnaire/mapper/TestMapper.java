package com.tcm.questionnaire.mapper;

import com.tcm.questionnaire.po.UserPO;
import org.springframework.stereotype.Repository;

public interface TestMapper {

    UserPO selectById(Integer id);
}
