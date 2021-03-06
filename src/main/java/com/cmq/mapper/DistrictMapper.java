package com.cmq.mapper;

import com.cmq.po.DistrictPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DistrictMapper {

    DistrictPO select(int id);

    DistrictPO selectByCode(String districtCode);

    List<DistrictPO> find(List<Integer> ids);

    List<DistrictPO> findAll();

    List<DistrictPO> findProvinces();

    List<DistrictPO> findChildrenByParentDistrictCode(String districtCode);

    List<DistrictPO> findChildrenByParentCodes(List<String> districtCodes);

    int insert(DistrictPO districtPO);

    int update(DistrictPO districtPO);

    int deleteByCodeWithChildren(String districtCode);

}
