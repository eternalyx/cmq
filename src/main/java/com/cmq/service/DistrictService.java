package com.cmq.service;

import com.cmq.bo.response.DistrictTreeBO;
import com.cmq.po.DistrictPO;

import java.util.List;

public interface DistrictService {

    DistrictPO select(int id);

    DistrictPO selectByCode(String districtCode);

    List<DistrictPO> find(List<Integer> ids);

    List<DistrictTreeBO> findAllDistrictAsTree();

    List<DistrictPO> findProvinces();

    List<DistrictPO> findChildrenByParentId(int districtId);

    List<DistrictPO> findChildrenByParentCode(String districtCode);

    int insert(DistrictPO districtPO);

    int update(DistrictPO districtPO);

    int deleteByCodeWithChildren(String districtCode);

}
