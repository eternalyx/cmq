package com.cmq.controller.app;

import com.cmq.common.BaseResult;
import com.cmq.po.DoctorPO;
import com.cmq.service.DoctorService;
import com.cmq.utils.DigestUtil;
import com.cmq.utils.JwtUtil;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
public class LoginController {

    @Resource
    private DoctorService doctorService;

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BaseResult login(String username, String password){
        BaseResult result = new BaseResult();

        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            return result.fail("用户名或密码不能为空");
        }

        DoctorPO doctor = doctorService.selectByMobile(username.trim());
        if(doctor == null || doctor.getIsDeleted() > 0){
            return result.fail("用户不存在");
        }

        if(!DigestUtil.checkPassword(password.trim(), doctor.getPassword())){
            return result.fail("密码不正确");
        }

        String token = JwtUtil.sign(doctor);
        if(StringUtils.isEmpty(token)){
            return result.fail("系统错误");
        }

        return result.success("登录成功").data("token", token);
    }

    @ResponseBody
    @RequestMapping(value = "/contact-info", method = RequestMethod.GET)
    public BaseResult contactInfo(){
        return new BaseResult().success("").data("contactInfo", "请联系管理员xxx，电话xxxx，邮箱xxxx,工作时间24小时");
    }

}
