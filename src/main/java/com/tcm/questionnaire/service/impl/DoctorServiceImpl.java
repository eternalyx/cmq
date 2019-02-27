package com.tcm.questionnaire.service.impl;

import com.tcm.questionnaire.mapper.DoctorMapper;
import com.tcm.questionnaire.po.DoctorPO;
import com.tcm.questionnaire.service.DoctorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("doctorService")
public class DoctorServiceImpl implements DoctorService {

    @Resource
    private DoctorMapper doctorMapper;

    @Override
    public DoctorPO selectById(Integer id) {
        return doctorMapper.selectById(id);
    }

    @Override
    public DoctorPO selectByName(String name) {
        return doctorMapper.selectByName(name);
    }

    @Override
    public DoctorPO selectByMobile(String mobile) {
        return doctorMapper.selectByMobile(mobile);
    }

    @Override
    public int changePassword(Integer doctorId, String newPassword) {
        try{
            return doctorMapper.changePassword(doctorId, newPassword);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}
