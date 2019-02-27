package com.tcm.questionnaire.service.impl;

import com.tcm.questionnaire.common.SystemThreadLocal;
import com.tcm.questionnaire.mapper.DoctorMapper;
import com.tcm.questionnaire.mapper.QuestionnaireMapper;
import com.tcm.questionnaire.po.DoctorPO;
import com.tcm.questionnaire.po.QuestionnairePO;
import com.tcm.questionnaire.service.QuestionnaireService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("questionnaireService")
public class QuestionnaireServiceImpl implements QuestionnaireService {

    @Resource
    private DoctorMapper doctorMapper;

    @Resource
    private QuestionnaireMapper questionnaireMapper;

    @Override
    public int insert(QuestionnairePO questionnaire) {
        DoctorPO doctor = doctorMapper.selectById(SystemThreadLocal.getSystemUser().getId());
        questionnaire.insert(doctor.getName());

        return questionnaireMapper.insert(questionnaire);
    }
}
