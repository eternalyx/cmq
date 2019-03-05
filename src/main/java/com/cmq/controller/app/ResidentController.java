package com.cmq.controller.app;

import com.cmq.common.BaseResult;
import com.cmq.po.ResidentPO;
import com.cmq.service.ResidentService;
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
    @RequestMapping(value = "/list-by-condition", method = RequestMethod.GET)
    public BaseResult findResidentsByCondition(int districtId, String condition){
        List<ResidentPO> residentPOS = residentService.findByCondition(districtId, condition);
        return new BaseResult().data("residents", residentPOS);
    }

    @ResponseBody
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public BaseResult insert(@RequestBody ResidentPO residentPO){
        int result = residentService.insert(residentPO);
        if(result != 1){
            return new BaseResult().fail("添加居民失败");
        }
        return new BaseResult().success("添加居民成功");
    }
}
