package com.cmq.mapper;

import com.cmq.po.DoctorFunctionPO;

import java.util.List;

public interface DoctorFunctionMapper {

    List<DoctorFunctionPO> findByDoctorId(int doctorId);

    int insertBatch(List<DoctorFunctionPO> list);

    int deleteByDoctorId(int doctorId);

}
