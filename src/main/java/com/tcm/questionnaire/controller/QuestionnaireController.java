package com.tcm.questionnaire.controller;

import com.tcm.questionnaire.bo.request.QuestionnaireRequestBO;
import com.tcm.questionnaire.common.BaseResult;
import com.tcm.questionnaire.po.QuestionnairePO;
import com.tcm.questionnaire.service.QuestionnaireService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/questionnaire")
public class QuestionnaireController {

    @Resource
    private QuestionnaireService questionnaireService;

    @ResponseBody
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public BaseResult insert(@RequestBody QuestionnaireRequestBO questionnaireRequestBO){
        BaseResult result = new BaseResult();

        QuestionnairePO questionnaire = new QuestionnairePO();
        BeanUtils.copyProperties(questionnaireRequestBO, questionnaire);

        int insert = questionnaireService.insert(questionnaire);

        if(insert != 1){
            return result.fail("新增辨识结果失败");
        }
        return result.success("新增辨识结果成功");
    }
}
