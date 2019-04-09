package com.cmq.mapper;

import com.cmq.po.DoctorDistrictPO;

import java.util.List;

public interface DoctorDistrictMapper {

    List<DoctorDistrictPO> findByDoctorId(int doctorId);

    List<DoctorDistrictPO> findByDistrictCodes(List<String> districtCodes);

    List<DoctorDistrictPO> findSubordinateByDistrictCode(String districtCode);

    int insertBatch(List<DoctorDistrictPO> list);

    int deleteByDoctorId(int doctorId);
}
