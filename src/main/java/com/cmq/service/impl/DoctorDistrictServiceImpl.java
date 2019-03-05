package com.cmq.service.impl;

import com.cmq.mapper.DoctorDistrictMapper;
import com.cmq.po.DoctorDistrictPO;
import com.cmq.service.DoctorDistrictService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("doctorDistrictService")
public class DoctorDistrictServiceImpl implements DoctorDistrictService {

    @Resource
    private DoctorDistrictMapper doctorDistrictMapper;

    @Override
    public List<DoctorDistrictPO> findByDoctorId(int doctorId) {
        return doctorDistrictMapper.findByDoctorId(doctorId);
    }
}
