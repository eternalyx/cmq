package com.cmq.mapper;

import com.cmq.po.DistrictPO;

import java.util.List;

public interface DistrictMapper {

    DistrictPO select(int id);

    List<DistrictPO> find(List<Integer> ids);
}
