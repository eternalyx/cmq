package com.tcm.questionnaire.service.impl;

import com.tcm.questionnaire.mapper.DistrictMapper;
import com.tcm.questionnaire.mapper.DoctorDistrictMapper;
import com.tcm.questionnaire.po.DistrictPO;
import com.tcm.questionnaire.po.DoctorDistrictPO;
import com.tcm.questionnaire.service.DistrictService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("districtService")
public class DistrictServiceImpl implements DistrictService {

    @Resource
    private DistrictMapper districtMapper;

    @Resource
    private DoctorDistrictMapper doctorDistrictMapper;

    @Override
    public List<DistrictPO> findDistrictsByIds(List<Integer> ids) {
        return districtMapper.findByIds(ids);
    }

    @Override
    public List<DoctorDistrictPO> findVillagesByDoctor(Integer doctorId) {
        return doctorDistrictMapper.findByDoctorId(doctorId);
    }
}
