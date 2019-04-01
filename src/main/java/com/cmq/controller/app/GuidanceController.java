package com.cmq.controller.app;

import com.cmq.common.BaseResult;
import com.cmq.po.GuidancePO;
import com.cmq.service.GuidanceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/guidance")
public class GuidanceController {

    @Resource
    private GuidanceService guidanceService;

    @ResponseBody
    @RequestMapping(value = "list-all", method = RequestMethod.GET)
    public BaseResult findAll(){
        BaseResult result = new BaseResult();

        List<GuidancePO> guidance = guidanceService.findAll();

        return result.data("guidance", guidance);
    }
}
