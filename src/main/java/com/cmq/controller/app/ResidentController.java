package com.cmq.controller.app;

import com.cmq.common.BaseResult;
import com.cmq.po.ResidentPO;
import com.cmq.service.ResidentService;
import com.cmq.utils.HttpUtils;
import com.cmq.utils.IDCardUtils;
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
    @RequestMapping(value = "/list-by-condition", method = RequestMethod.GET)
    public BaseResult findResidentsByCondition(String condition){
        List<ResidentPO> residentPOS = residentService.findByCondition(condition);
        return new BaseResult().data("residents", residentPOS);
    }

    @ResponseBody
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public BaseResult insert(@RequestBody ResidentPO residentPO){
        int result = residentService.insert(residentPO);
        if(result != 1){
            return new BaseResult().fail("添加居民失败");
        }
        return new BaseResult().success("添加居民成功");
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
