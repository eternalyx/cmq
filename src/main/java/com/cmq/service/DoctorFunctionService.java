package com.cmq.service;

import com.cmq.bo.request.app.DoctorConfigurationRequestBO;
import com.cmq.po.DoctorFunctionPO;

import java.util.List;

public interface DoctorFunctionService {

    List<DoctorFunctionPO> findByDoctorId(int doctorId);

    int insertOrUpdateByDoctorId(DoctorConfigurationRequestBO params, boolean isUpdate);
}
