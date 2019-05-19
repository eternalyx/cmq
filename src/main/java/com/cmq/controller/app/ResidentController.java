package com.cmq.controller.app;

import com.cmq.bo.request.app.ResidentRequestBO;
import com.cmq.bo.response.app.ResidentResponseBO;
import com.cmq.common.BaseResult;
import com.cmq.po.ResidentPO;
import com.cmq.service.ResidentService;
import com.cmq.utils.HttpUtils;
import com.cmq.utils.IDCardUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/resident")
public class ResidentController {

    @Resource
    private ResidentService residentService;

    @ResponseBody
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public BaseResult selectDetail(int id){
        ResidentResponseBO residentResponseBO = new ResidentResponseBO();

        ResidentPO residentPO = residentService.select(id);
        BeanUtils.copyProperties(residentPO, residentResponseBO);

        return new BaseResult().data("resident", residentResponseBO);
    }

    @ResponseBody
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public BaseResult insert(@RequestBody ResidentPO residentPO){
        int result = residentService.insert(residentPO);
        if(result != 1){
            return new BaseResult().fail("添加居民失败");
        }
        return new BaseResult().success("添加居民成功").data("id", residentPO.getId());
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public BaseResult update(@RequestBody ResidentRequestBO residentRequestBO){
        BaseResult result = new BaseResult();

        ResidentPO residentPO = new ResidentPO();
        BeanUtils.copyProperties(residentRequestBO, residentPO);

        int update = residentService.update(residentPO);

        if(update != 1){
            return result.fail("修改失败");
        }
        return result.success("修改成功").data("id", residentRequestBO.getId());

    }

    @ResponseBody
    @RequestMapping(value = "/list-by-condition", method = RequestMethod.GET)
    public BaseResult findResidentsByCondition(String condition){
        List<ResidentPO> residentPOS = residentService.findByCondition(condition);
        return new BaseResult().data("residents", residentPOS);
    }

    @ResponseBody
    @RequestMapping(value = "/recognize-id-card", method = RequestMethod.POST)
    public String recognizeIDCard(String imageBase64){
        Map<String, String> header = IDCardUtils.buildHttpHeader();

        String result = null;
        try {
            result = HttpUtils.doPost1(IDCardUtils.WEBOCR_URL, header, "image=" + URLEncoder.encode(imageBase64, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
