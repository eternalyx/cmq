package com.cmq.mapper;

import com.cmq.po.DoctorPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DoctorMapper {

    DoctorPO select(int id);

    DoctorPO selectByName(String name);

    DoctorPO selectByMobile(String mobile);

    List<DoctorPO> find(List<Integer> ids);

    int update(DoctorPO doctorPO);

    int changePassword(@Param("id") int id, @Param("password") String password);
}
