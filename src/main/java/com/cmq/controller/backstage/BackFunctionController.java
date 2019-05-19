package com.cmq.controller.backstage;

import com.cmq.bo.response.app.FunctionTreeResponseBO;
import com.cmq.common.BaseResult;
import com.cmq.service.FunctionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/function")
public class BackFunctionController {

    @Resource
    private FunctionService functionService;

    @ResponseBody
    @RequestMapping(value = "/list-as-tree", method = RequestMethod.GET)
    public BaseResult findAllFunctionsAsTree(){
        FunctionTreeResponseBO tree = functionService.findAllFunctionsAsTree();
        return new BaseResult().data("functionTree", tree);
    }
}
