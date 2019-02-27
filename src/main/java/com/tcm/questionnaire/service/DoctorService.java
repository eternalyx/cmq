package com.tcm.questionnaire.service;

import com.tcm.questionnaire.po.DoctorPO;

public interface DoctorService {

    DoctorPO selectById(Integer id);

    DoctorPO selectByName(String name);

    DoctorPO selectByMobile(String mobile);

    int changePassword(Integer doctorId, String newPassword);
}
