package com.cmq.service.impl;

import com.cmq.bo.request.app.DoctorConfigurationRequestBO;
import com.cmq.common.CmqSystem;
import com.cmq.mapper.DoctorFunctionMapper;
import com.cmq.po.DoctorFunctionPO;
import com.cmq.po.DoctorPO;
import com.cmq.service.DoctorFunctionService;
import com.cmq.service.DoctorService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("doctorFunctionService")
public class DoctorFunctionServiceImpl implements DoctorFunctionService {

    @Resource
    private DoctorFunctionMapper doctorFunctionMapper;

    @Resource
    private DoctorService doctorService;

    @Override
    public List<DoctorFunctionPO> findByDoctorId(int doctorId) {
        return doctorFunctionMapper.findByDoctorId(doctorId);
    }

    @Override
    public int insertOrUpdateByDoctorId(DoctorConfigurationRequestBO params, boolean isUpdate) {
        if(isUpdate){
            doctorFunctionMapper.deleteByDoctorId(params.getId());
        }

        if(ArrayUtils.isEmpty(params.getFunctionIds())){
            return 0;
        }

        DoctorPO loginUser = doctorService.select(CmqSystem.getCurrentLoggedInUser().getId());

        List<DoctorFunctionPO> doctorFunctionPOs = new ArrayList<>();

        for(Integer functionId : params.getFunctionIds()){
            DoctorFunctionPO doctorFunctionPO = new DoctorFunctionPO();
            doctorFunctionPO.setDoctorId(params.getId());
            doctorFunctionPO.setFunctionId(functionId);
            doctorFunctionPO.insert(loginUser.getId(), loginUser.getName());

            doctorFunctionPOs.add(doctorFunctionPO);
        }

        return doctorFunctionMapper.insertBatch(doctorFunctionPOs);
    }
}
