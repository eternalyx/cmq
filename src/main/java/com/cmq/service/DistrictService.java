package com.cmq.service;

import com.cmq.bo.response.DistrictTreeBO;
import com.cmq.po.DistrictPO;

import java.util.List;

public interface DistrictService {

    DistrictPO select(int id);

    DistrictPO selectByCode(String districtCode);

    List<DistrictPO> find(List<Integer> ids);

    @Deprecated
    List<DistrictTreeBO> findAllDistrictAsTree();

    //new version for findAllDistrictAsTree
    List<DistrictTreeBO> findAllDistrictsAsTree();

    List<DistrictPO> findProvinces();

    List<DistrictPO> findChildrenByParentId(int districtId);

    List<DistrictPO> findChildrenByParentCode(String districtCode);

    List<DistrictPO> findChildrenByParentCodes(List<String> districtCodes);

    int insert(DistrictPO districtPO);

    int update(DistrictPO districtPO);

    int deleteByCodeWithChildren(String districtCode);

}
