package com.tcm.questionnaire.service;

import com.tcm.questionnaire.po.DistrictPO;
import com.tcm.questionnaire.po.DoctorDistrictPO;

import java.util.List;

public interface DistrictService {

    public List<DistrictPO> findDistrictsByIds(List<Integer> ids);

    public List<DoctorDistrictPO> findVillagesByDoctor(Integer doctorId);
}
