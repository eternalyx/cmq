package com.tcm.questionnaire.mapper;

import com.tcm.questionnaire.po.DoctorPO;
import org.apache.ibatis.annotations.Param;

public interface DoctorMapper {

    DoctorPO selectById(Integer id);

    DoctorPO selectByName(String name);

    DoctorPO selectByMobile(String mobile);

    int changePassword(@Param("id") Integer id, @Param("password") String password);
}
