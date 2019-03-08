package com.cmq.service.impl;

import com.cmq.mapper.ResidentMapper;
import com.cmq.common.CmqSystem;
import com.cmq.po.DoctorPO;
import com.cmq.po.ResidentPO;
import com.cmq.service.DoctorService;
import com.cmq.service.ResidentService;
import com.cmq.utils.CommonUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("residentService")
public class ResidentServiceImpl implements ResidentService {

    @Resource
    private ResidentMapper residentMapper;

    @Resource
    private DoctorService doctorService;

    @Override
    public ResidentPO select(int id) {
        return residentMapper.select(id);
    }

    @Override
    public List<ResidentPO> findByCondition(int districtId, String condition) {
        //仅查询当前登录村医创建的村民
        Integer doctorId = CmqSystem.getCurrentLoggedInUser().getId();

        return residentMapper.findByCondition(districtId, doctorId, condition);
    }

    @Override
    public int insert(ResidentPO residentPO) {
        try{
            DoctorPO currentLoggedInDoctor = doctorService.select(CmqSystem.getCurrentLoggedInUser().getId());
            residentPO.insert(currentLoggedInDoctor.getId(), currentLoggedInDoctor.getName());
            residentPO.setAge(CommonUtils.calculateAgeByBirthday(residentPO.getBirthday()));
//            residentPO.setDistrictId(-1);

            return residentMapper.insert(residentPO);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}
