package com.cmq.service.impl;

import com.cmq.common.CmqSystem;
import com.cmq.mapper.DoctorMapper;
import com.cmq.po.DoctorPO;
import com.cmq.service.DoctorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("doctorService")
public class DoctorServiceImpl implements DoctorService {

    @Resource
    private DoctorMapper doctorMapper;

    @Override
    public DoctorPO select(int id) {
        return doctorMapper.select(id);
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
    public List<DoctorPO> find(List<Integer> ids) {
        return doctorMapper.find(ids);
    }

    @Override
    public int update(DoctorPO doctorPO) {
        try{
            DoctorPO currentLoggedInDoctor = doctorMapper.select(CmqSystem.getCurrentLoggedInUser().getId());
            doctorPO.update(currentLoggedInDoctor.getId(), currentLoggedInDoctor.getName());

            return doctorMapper.update(doctorPO);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int changePassword(int doctorId, String newPassword) {
        try{
            return doctorMapper.changePassword(doctorId, newPassword);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}
