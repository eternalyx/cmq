package com.tcm.questionnaire.controller;

import com.tcm.questionnaire.common.BaseResult;
import com.tcm.questionnaire.common.SystemThreadLocal;
import com.tcm.questionnaire.mapper.DistrictMapper;
import com.tcm.questionnaire.po.DistrictPO;
import com.tcm.questionnaire.po.DoctorDistrictPO;
import com.tcm.questionnaire.service.DistrictService;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/district")
public class DistrictController {

    @Resource
    private DistrictService districtService;

    @ResponseBody
    @RequestMapping(value = "/doctor-district", method = RequestMethod.GET)
    public BaseResult findVillagesByDoctor(){
        Integer doctorId = SystemThreadLocal.getSystemUser().getId();

        List<DoctorDistrictPO> doctorDistricts = districtService.findVillagesByDoctor(doctorId);

        List<Map<String, Object>> villages = new ArrayList<>();

        if(!CollectionUtils.isEmpty(doctorDistricts)){
            List<Integer> villageIds = doctorDistricts.stream().map(DoctorDistrictPO::getDistrictId).collect(Collectors.toList());
            List<DistrictPO> villagePOs = districtService.findDistrictsByIds(villageIds);

            if(!CollectionUtils.isEmpty(villagePOs)){
                for(DistrictPO villagePO : villagePOs){
                    Map<String, Object> village = new HashMap<>();
                    village.put("id", villagePO.getId());
                    village.put("districtName", villagePO.getName());
                    villages.add(village);
                }
            }

        }
        return new BaseResult().data("districts", villages);
    }
}
