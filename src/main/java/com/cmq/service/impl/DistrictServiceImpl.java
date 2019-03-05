package com.cmq.service.impl;

import com.cmq.mapper.DistrictMapper;
import com.cmq.mapper.DoctorDistrictMapper;
import com.cmq.po.DistrictPO;
import com.cmq.po.DoctorDistrictPO;
import com.cmq.service.DistrictService;
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
    public List<DistrictPO> find(List<Integer> ids) {
        return districtMapper.find(ids);
    }

}
