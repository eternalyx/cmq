package com.cmq.service;

import com.cmq.po.DoctorDistrictPO;

import java.util.List;

public interface DoctorDistrictService {

    List<DoctorDistrictPO> findByDoctorId(int doctorId);
}
