package com.tcm.questionnaire.service.impl;

import com.tcm.questionnaire.common.SystemThreadLocal;
import com.tcm.questionnaire.mapper.DoctorMapper;
import com.tcm.questionnaire.mapper.ResidentMapper;
import com.tcm.questionnaire.po.DoctorPO;
import com.tcm.questionnaire.po.ResidentPO;
import com.tcm.questionnaire.service.ResidentService;
import com.tcm.questionnaire.utils.CommonUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("residentService")
public class ResidentServiceImpl implements ResidentService {

    @Resource
    private DoctorMapper doctorMapper;

    @Resource
    private ResidentMapper residentMapper;

    @Override
    public int insert(ResidentPO resident) {
        try{
            DoctorPO curDoctor = doctorMapper.selectById(SystemThreadLocal.getSystemUser().getId());
            resident.insert(curDoctor.getName());
            resident.setAge(CommonUtil.calculateAgeByBirthday(resident.getBirthday()));

            //todo parse permanentAddress or residenceAddress to distirct id
            resident.setDistrictId(-1);
            return residentMapper.insert(resident);
        }catch (Exception e){
            return 0;
        }
    }

    @Override
    public List<ResidentPO> findByCondition(int districtId, String condition) {
        return residentMapper.findByCondition(districtId, condition);
    }
}
