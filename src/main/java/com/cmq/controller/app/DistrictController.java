package com.cmq.controller.app;

import com.cmq.common.BaseResult;
import com.cmq.common.CmqSystem;
import com.cmq.po.DistrictPO;
import com.cmq.po.DoctorDistrictPO;
import com.cmq.service.DistrictService;
import com.cmq.service.DoctorDistrictService;
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

    @Resource
    private DoctorDistrictService doctorDistrictService;

    @ResponseBody
    @RequestMapping(value = "/list-by-doctor", method = RequestMethod.GET)
    public BaseResult findDistrictsByDoctor(){
        int curLoggedInDoctorId = CmqSystem.getCurrentLoggedInUser().getId();

        List<DoctorDistrictPO> doctorDistrictPOS = doctorDistrictService.findByDoctorId(curLoggedInDoctorId);

        List<Map<String, Object>> districts = new ArrayList<>();

        if(!CollectionUtils.isEmpty(doctorDistrictPOS)){
            List<Integer> districtIds = doctorDistrictPOS.stream().map(DoctorDistrictPO::getDistrictId).collect(Collectors.toList());
            List<DistrictPO> districtPOS = districtService.find(districtIds);

            if(!CollectionUtils.isEmpty(districtPOS)){
                for(DistrictPO districtPO : districtPOS){
                    Map<String, Object> district = new HashMap<>();
                    district.put("id", districtPO.getId());
                    district.put("districtName", districtPO.getName());
                    districts.add(district);
                }
            }
        }
        return new BaseResult().data("districts", districts);
    }
}
