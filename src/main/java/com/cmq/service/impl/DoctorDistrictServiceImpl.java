package com.cmq.service.impl;

import com.cmq.bo.request.DoctorConfigurationRequestBO;
import com.cmq.common.CmqSystem;
import com.cmq.mapper.DoctorDistrictMapper;
import com.cmq.po.DistrictPO;
import com.cmq.po.DoctorDistrictPO;
import com.cmq.po.DoctorPO;
import com.cmq.service.DistrictService;
import com.cmq.service.DoctorDistrictService;
import com.cmq.service.DoctorService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service("doctorDistrictService")
public class DoctorDistrictServiceImpl implements DoctorDistrictService {

    @Resource
    private DoctorDistrictMapper doctorDistrictMapper;

    @Resource
    private DistrictService districtService;

    @Resource
    private DoctorService doctorService;

    @Override
    public List<DoctorDistrictPO> findByDoctorId(int doctorId) {
        return doctorDistrictMapper.findByDoctorId(doctorId);
    }

    @Override
    public int insertOrUpdateByDoctorId(DoctorConfigurationRequestBO params, boolean isUpdate) {
        if(isUpdate){
            doctorDistrictMapper.deleteByDoctorId(params.getId());
        }

        if(ArrayUtils.isEmpty(params.getDistrictIds())){
            return 0;
        }

        DoctorPO loginUser = doctorService.select(CmqSystem.getCurrentLoggedInUser().getId());

        List<DistrictPO> districtPOs = districtService.find(Arrays.asList(params.getDistrictIds()));

        List<DoctorDistrictPO> doctorDistrictPOs = new ArrayList<>();

        for(DistrictPO districtPO : districtPOs){
            DoctorDistrictPO doctorDistrictPO = new DoctorDistrictPO();
            doctorDistrictPO.setDoctorId(params.getId());
            doctorDistrictPO.setDistrictId(districtPO.getId());
            doctorDistrictPO.setDistrictCode(districtPO.getDistrictCode());
            doctorDistrictPO.setIsResponsible(params.getIsResponsible());
            doctorDistrictPO.insert(loginUser.getId(), loginUser.getName());

            doctorDistrictPOs.add(doctorDistrictPO);
        }

        return doctorDistrictMapper.insertBatch(doctorDistrictPOs);
    }
}
