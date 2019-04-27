package com.cmq.service;

import com.cmq.po.ResidentPO;

import java.util.List;

public interface ResidentService {

    ResidentPO select(int id);

    List<ResidentPO> findByCondition(String condition);

    int insert(ResidentPO residentPO);

    int update(ResidentPO residentPO);
}
