package com.cmq.service;

import com.cmq.bo.request.app.DoctorConfigurationRequestBO;
import com.cmq.bo.request.app.DoctorHandleRequestBO;
import com.cmq.bo.request.app.DoctorPageRequestBO;
import com.cmq.po.DoctorPO;

import java.util.List;

public interface DoctorService {

    DoctorPO select(int id);

    DoctorPO selectByName(String name);

    DoctorPO selectByMobile(String mobile);

    List<DoctorPO> find(List<Integer> ids);

    List<DoctorPO> findByPaging(DoctorPageRequestBO params);

    int count(DoctorPageRequestBO params);

    int insert(DoctorConfigurationRequestBO params);

    int update(DoctorPO doctorPO);

    int changePassword(DoctorHandleRequestBO params);

    int resetPassword(DoctorHandleRequestBO params);

    int changeUsageState(DoctorHandleRequestBO params);
}
