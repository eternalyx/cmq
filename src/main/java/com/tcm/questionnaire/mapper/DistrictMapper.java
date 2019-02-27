package com.tcm.questionnaire.mapper;

import com.tcm.questionnaire.po.DistrictPO;

import java.util.List;

public interface DistrictMapper {

    DistrictPO selectById(Integer id);

    List<DistrictPO> findByIds(List<Integer> ids);
}
