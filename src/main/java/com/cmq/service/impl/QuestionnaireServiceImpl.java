package com.cmq.service.impl;

import com.cmq.common.CmqSystem;
import com.cmq.mapper.QuestionnaireMapper;
import com.cmq.po.DoctorPO;
import com.cmq.po.QuestionnairePO;
import com.cmq.service.DoctorService;
import com.cmq.service.QuestionnaireService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("questionnaireService")
public class QuestionnaireServiceImpl implements QuestionnaireService {

    @Resource
    private QuestionnaireMapper questionnaireMapper;

    @Resource
    private DoctorService doctorService;

    @Override
    public QuestionnairePO select(int id) {
        return questionnaireMapper.select(id);
    }

    @Override
    public List<QuestionnairePO> find(List<Integer> ids) {
        return questionnaireMapper.find(ids);
    }

    @Override
    public List<QuestionnairePO> findByResidentId(int residentId) {
        return questionnaireMapper.findByResidentId(residentId);
    }

    @Override
    public List<QuestionnairePO> findLastEveryResident(List<Integer> residentIds) {
        return questionnaireMapper.findLastEveryResident(residentIds);
    }

    @Override
    public int insert(QuestionnairePO questionnairePO) {
        try{
            DoctorPO currentLoggedInDoctor = doctorService.select(CmqSystem.getCurrentLoggedInUser().getId());
            questionnairePO.insert(currentLoggedInDoctor.getName());

            return questionnaireMapper.insert(questionnairePO);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(QuestionnairePO questionnairePO) {
        try{
            DoctorPO currentLoggedInDoctor = doctorService.select(CmqSystem.getCurrentLoggedInUser().getId());
            questionnairePO.update(currentLoggedInDoctor.getName());

            return questionnaireMapper.update(questionnairePO);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}
