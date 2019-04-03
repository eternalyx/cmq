package com.cmq.controller.app;

import com.cmq.common.BaseResult;
import com.cmq.common.ConfigKeyEnum;
import com.cmq.common.DoctorUsageStateEnum;
import com.cmq.po.ConfigPO;
import com.cmq.po.DoctorPO;
import com.cmq.service.ConfigService;
import com.cmq.service.DoctorService;
import com.cmq.utils.PasswordUtils;
import com.cmq.utils.TokenUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
public class LoginController {

    @Resource
    private DoctorService doctorService;

    @Resource
    private ConfigService configService;

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BaseResult login(String username, String password){
        BaseResult result = new BaseResult();

        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            return result.fail("用户名或密码不能为空");
        }

        DoctorPO doctor = doctorService.selectByMobile(username.trim());
        if(doctor == null || doctor.getIsDeleted() > 0
                || doctor.getUsageState() == DoctorUsageStateEnum.NOT_ENABLED.getDatabaseCode()){
            return result.fail("用户不存在");
        }

        if(!PasswordUtils.checkPassword(password.trim(), doctor.getPassword())){
            return result.fail("密码不正确");
        }

        String token = TokenUtils.sign(doctor);
        if(StringUtils.isEmpty(token)){
            return result.fail("系统错误");
        }

        return result.success("登录成功").data("token", token);
    }

    @ResponseBody
    @RequestMapping(value = "/contact-us", method = RequestMethod.GET)
    public BaseResult contactInfo(){
        ConfigPO configPO = configService.selectByKey(ConfigKeyEnum.CONTACT_US_INFO.getKey());
        if(ObjectUtils.isEmpty(configPO)){
            return new BaseResult().success("请联系客服");
        }
        return new BaseResult().success("").data("contactUs", configPO.getValue());
    }

}
