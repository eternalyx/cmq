package com.cmq.mapper;

import com.cmq.bo.request.app.DoctorHandleRequestBO;
import com.cmq.bo.request.app.DoctorPageRequestBO;
import com.cmq.po.DoctorPO;

import java.util.List;

public interface DoctorMapper {

    DoctorPO select(int id);

    DoctorPO selectByName(String name);

    DoctorPO selectByMobile(String mobile);

    List<DoctorPO> find(List<Integer> ids);

    List<DoctorPO> findByPaging(DoctorPageRequestBO params);

    int count(DoctorPageRequestBO params);

    int insertBatch(List<DoctorPO> list);

    int update(DoctorPO doctorPO);

    //int changePassword(@Param("id") int id, @Param("password") String password);
    int changePassword(DoctorHandleRequestBO params);

    int changeUsageState(DoctorHandleRequestBO params);
}
