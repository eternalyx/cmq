package com.tcm.questionnaire.controller;

import com.sun.tracing.dtrace.Attributes;
import com.tcm.questionnaire.common.BaseResult;
import com.tcm.questionnaire.common.SystemThreadLocal;
import com.tcm.questionnaire.po.UserPO;
import com.tcm.questionnaire.service.UserService;
import com.tcm.questionnaire.utils.DigestUtil;
import com.tcm.questionnaire.utils.JwtUtil;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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

        if(!DigestUtil.checkPassword(password.trim(), user.getPassword())){
            return result.fail("密码不正确");
        }

        String token = JwtUtil.sign(user.getId());
        if(StringUtils.isEmpty(token)){
            return result.fail("认证失败");
        }

        return result.success("登录成功").data("token", token);
    }

    @ResponseBody
    @RequestMapping(value = "eternal-token", method = RequestMethod.GET)
    public BaseResult eternalToken(String currentUserId){

        return new BaseResult().success("认证成功").data("userId", SystemThreadLocal.getSystemUserId());
    }

}
