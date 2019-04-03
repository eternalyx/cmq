package com.cmq.controller.app;

import com.cmq.bo.request.DoctorHandleRequestBO;
import com.cmq.common.BaseResult;
import com.cmq.common.CmqSystem;
import com.cmq.po.DoctorPO;
import com.cmq.service.DoctorService;
import com.cmq.utils.PasswordUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/doctor")
public class DoctorController {

    @Resource
    private DoctorService doctorService;

    @ResponseBody
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public BaseResult selectDetail(){
        DoctorPO doctorPO = doctorService.select(CmqSystem.getCurrentLoggedInUser().getId());
        return new BaseResult().success().data("doctor", doctorPO);
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public BaseResult update(@RequestBody DoctorPO doctorPO){
        //修改当前登录村医的个人信息
        doctorPO.setId(CmqSystem.getCurrentLoggedInUser().getId());

        int update = doctorService.update(doctorPO);

        if(update != 1){
            return new BaseResult().fail("修改个人信息失败");
        }
        return new BaseResult().success("修改个人信息成功");
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

        DoctorPO doctorPO = doctorService.select(CmqSystem.getCurrentLoggedInUser().getId());
        if(!PasswordUtils.checkPassword(oldPassword.trim(), doctorPO.getPassword())){
            return result.fail("旧密码不正确");
        }

        DoctorHandleRequestBO params = new DoctorHandleRequestBO();

        params.setId(doctorPO.getId());
        params.setPassword(PasswordUtils.EncoderByMD5(newPassword));

        int change = doctorService.changePassword(params);
        if(change != 1){
            return result.fail("修改密码失败");
        }
        return result.success("修改密码成功");
    }

}
