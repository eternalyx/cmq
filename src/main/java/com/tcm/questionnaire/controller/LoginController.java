package com.tcm.questionnaire.controller;

import com.tcm.questionnaire.common.BaseResult;
import com.tcm.questionnaire.common.SystemThreadLocal;
import com.tcm.questionnaire.common.SystemUser;
import com.tcm.questionnaire.po.DoctorPO;
import com.tcm.questionnaire.service.DoctorService;
import com.tcm.questionnaire.utils.DigestUtil;
import com.tcm.questionnaire.utils.JwtUtil;
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
            return result.fail("认证失败");
        }

        return result.success("登录成功").data("token", token);
    }

    @ResponseBody
    @RequestMapping(value = "/change-password", method = RequestMethod.POST)
    public BaseResult changePassword(String oldPassword, String newPassword){
        BaseResult result = new BaseResult();

        if(StringUtils.isEmpty(oldPassword)){
            return result.fail("旧密码不能为空");
        }

        if(StringUtils.isEmpty(newPassword)){
            return result.fail("新密码不能为空");
        }

        DoctorPO doctor = doctorService.selectById(SystemThreadLocal.getSystemUser().getId());
        if(!DigestUtil.checkPassword(oldPassword.trim(), doctor.getPassword())){
            return result.fail("旧密码不正确");
        }

        int changeResult = doctorService.changePassword(doctor.getId(), DigestUtil.EncoderByMD5(newPassword));
        if(changeResult != 1){
            return result.fail("修改密码失败");
        }
        return result.success("修改密码成功");
    }

    @ResponseBody
    @RequestMapping(value = "/doctor-info", method = RequestMethod.POST)
    public BaseResult loginDoctorInfo(){
        DoctorPO doctor = doctorService.selectById(SystemThreadLocal.getSystemUser().getId());
        return new BaseResult().success().data("doctor", doctor);
    }

    @ResponseBody
    @RequestMapping(value = "contact-info", method = RequestMethod.GET)
    public BaseResult contactInfo(){
        return new BaseResult().success("").data("contactInfo", "请联系管理员xxx，电话xxxx，邮箱xxxx,工作时间24小时");
    }

}
