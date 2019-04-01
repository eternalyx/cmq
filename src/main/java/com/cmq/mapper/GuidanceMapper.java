package com.cmq.mapper;

import com.cmq.po.GuidancePO;

import java.util.List;

public interface GuidanceMapper {

    GuidancePO select(int id);

    List<GuidancePO> findAll();
}
