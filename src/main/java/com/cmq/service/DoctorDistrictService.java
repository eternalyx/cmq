package com.cmq.service;

import com.cmq.bo.request.DoctorConfigurationRequestBO;
import com.cmq.po.DoctorDistrictPO;

import java.util.List;

public interface DoctorDistrictService {

    List<DoctorDistrictPO> findByDoctorId(int doctorId);

    int insertOrUpdateByDoctorId(DoctorConfigurationRequestBO params, boolean isUpdate);
}
