package com.cmq.controller.backstage;

import com.cmq.bo.response.DistrictTreeBO;
import com.cmq.common.BaseResult;
import com.cmq.service.DistrictService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/district")
public class BackDistrictController {

    @Resource
    private DistrictService districtService;

    @ResponseBody
    @RequestMapping(value = "/list-as-tree", method = RequestMethod.GET)
    public BaseResult findAllDistrictAsTree(){
        List<DistrictTreeBO> tree = districtService.findAllDistrictAsTree();
        return new BaseResult().success().data("tree", tree);
    }
}
