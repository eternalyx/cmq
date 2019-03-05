package com.cmq.service;

import com.cmq.po.DoctorPO;

import java.util.List;

public interface DoctorService {

    DoctorPO select(int id);

    DoctorPO selectByName(String name);

    DoctorPO selectByMobile(String mobile);

    List<DoctorPO> find(List<Integer> ids);

    int update(DoctorPO doctorPO);

    int changePassword(int doctorId, String newPassword);
}
