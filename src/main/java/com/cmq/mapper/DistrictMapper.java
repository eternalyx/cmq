package com.cmq.mapper;

import com.cmq.po.DistrictPO;

import java.util.List;

public interface DistrictMapper {

    DistrictPO select(int id);

    DistrictPO selectByCode(String districtCode);

    List<DistrictPO> find(List<Integer> ids);

    List<DistrictPO> findAll();

    int insert(DistrictPO districtPO);

    int update(DistrictPO districtPO);
}
