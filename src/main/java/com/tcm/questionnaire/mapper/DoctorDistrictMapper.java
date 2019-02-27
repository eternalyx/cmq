package com.tcm.questionnaire.mapper;

import com.tcm.questionnaire.po.DoctorDistrictPO;

import java.util.List;

public interface DoctorDistrictMapper {

    List<DoctorDistrictPO> findByDoctorId(Integer doctorId);
}
