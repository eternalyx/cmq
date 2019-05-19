package com.cmq.service;

import com.cmq.bo.request.app.DoctorConfigurationRequestBO;
import com.cmq.po.DoctorDistrictPO;

import java.util.List;

/**
 * 关于数据权限：若用户为区域负责人，则可以查看本级地域及下级地域的医生录入的Data
 *            若否。只可以查看自己录入的Data
 *
 * resident 不再有地域属性，取消"医生可以查看本级地域的村民"的逻辑
 */

public interface DoctorDistrictService {

    List<DoctorDistrictPO> findByDoctorId(int doctorId);

    List<DoctorDistrictPO> findByDistrictCodes(List<String> districtCodes);

    List<DoctorDistrictPO> findSubordinateByDistrictCode(String districtCode);

    List<Integer> findSubordinateDoctorIdsByLoginUser();

    int insertOrUpdateByDoctorId(DoctorConfigurationRequestBO params, boolean isUpdate);
}
