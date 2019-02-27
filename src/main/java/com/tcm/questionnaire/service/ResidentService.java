package com.tcm.questionnaire.service;

import com.tcm.questionnaire.po.ResidentPO;

import java.util.List;

public interface ResidentService {

    int insert(ResidentPO resident);

    List<ResidentPO> findByCondition(int districtId, String condition);
}
