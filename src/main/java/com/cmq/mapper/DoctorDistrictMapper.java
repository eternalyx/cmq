package com.cmq.mapper;

import com.cmq.po.DoctorDistrictPO;

import java.util.List;

public interface DoctorDistrictMapper {

    List<DoctorDistrictPO> findByDoctorId(int doctorId);

    int insertBatch(List<DoctorDistrictPO> list);

    int deleteByDoctorId(int doctorId);
}
