package com.cmq.service;

import com.cmq.bo.request.web.QuestionnaireOperationRequestBO;
import com.cmq.bo.request.web.QuestionnairePageRequestBO;
import com.cmq.po.QuestionnairePO;

import java.util.List;

public interface QuestionnaireService {

    QuestionnairePO select(int id);

    List<QuestionnairePO> find(List<Integer> ids);

    List<QuestionnairePO> findByResidentId(int residentId);

    List<QuestionnairePO> findLastEveryResident(List<Integer> residentIds);

    List<QuestionnairePO> page(QuestionnairePageRequestBO params);

    int count(QuestionnairePageRequestBO params);

    int insert(QuestionnairePO questionnairePO);

    int update(QuestionnairePO questionnairePO);

    void deleteSome(QuestionnaireOperationRequestBO params);
}
