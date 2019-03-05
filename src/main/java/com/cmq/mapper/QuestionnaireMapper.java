package com.cmq.mapper;

import com.cmq.po.QuestionnairePO;

import java.util.List;

public interface QuestionnaireMapper {

    QuestionnairePO select(int id);

    List<QuestionnairePO> find(List<Integer> ids);

    List<QuestionnairePO> findByResidentId(int residentId);

    List<QuestionnairePO> findLastEveryResident(List<Integer> residentIds);

    int insert(QuestionnairePO questionnairePO);

    int update(QuestionnairePO questionnairePO);
}
