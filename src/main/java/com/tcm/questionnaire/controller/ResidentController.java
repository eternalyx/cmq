package com.tcm.questionnaire.controller;

import com.tcm.questionnaire.common.BaseResult;
import com.tcm.questionnaire.common.SystemThreadLocal;
import com.tcm.questionnaire.common.SystemUser;
import com.tcm.questionnaire.po.ResidentPO;
import com.tcm.questionnaire.service.ResidentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/resident")
public class ResidentController {

    @Resource
    private ResidentService residentService;

    @ResponseBody
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public BaseResult insert(@RequestBody ResidentPO resident){
        int result = residentService.insert(resident);

        if(result != 1){
            return new BaseResult().fail("添加居民失败");
        }
        return new BaseResult().success("添加居民成功");
    }

    @ResponseBody
    @RequestMapping(value = "/find/condition", method = RequestMethod.GET)
    public BaseResult findResidentsByCondition(int districtId, String condition){
        List<ResidentPO> residents = residentService.findByCondition(districtId, condition);
        return new BaseResult().data("residents", residents);
    }
}
