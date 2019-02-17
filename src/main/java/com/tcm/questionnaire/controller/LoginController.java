package com.tcm.questionnaire.controller;

import com.tcm.questionnaire.common.BaseResult;
import com.tcm.questionnaire.po.UserPO;
import com.tcm.questionnaire.service.UserService;
import com.tcm.questionnaire.utils.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class LoginController {

    @Resource
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BaseResult login(String username, String password){
        BaseResult result = new BaseResult();

        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            return result.fail("用户名或密码不能为空");
        }

        UserPO user = userService.selectByUsername(username.trim());
        if(user == null || user.getIsDeleted() > 0){
            return result.fail("用户不存在");
        }

        if(!DigestUtils.checkPassword(password.trim(), user.getPassword())){
            return result.fail("密码不正确");
        }

        return result.data("user", user.getUsername() + user.getCreateBy())
                .data("token", "xxxx");
    }

}
