package com.cmq.service;

import com.cmq.po.DistrictPO;

import java.util.List;

public interface DistrictService {

    List<DistrictPO> find(List<Integer> ids);

}
