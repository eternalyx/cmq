package com.tcm.questionnaire.controller;

import com.tcm.questionnaire.po.UserPO;
import com.tcm.questionnaire.service.TestService;
import com.tcm.questionnaire.service.impl.TestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class LoginController {

    @Resource
    private TestService testService;

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Integer id){

        System.out.println(username + password);

        UserPO userPO = testService.selectById(id);

        return username + password + "  :" + userPO.getAddress() + userPO.getName() + userPO.getId();
    }

}
