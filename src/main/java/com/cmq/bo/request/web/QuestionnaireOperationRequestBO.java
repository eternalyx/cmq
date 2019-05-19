package com.cmq.bo.request.web;

import java.io.Serializable;
import java.util.List;

public class QuestionnaireOperationRequestBO implements Serializable {
    private static final long serialVersionUID = 1264041167914423606L;

    private List<Integer> questionnaireIds;

    public List<Integer> getQuestionnaireIds() {
        return questionnaireIds;
    }

    public void setQuestionnaireIds(List<Integer> questionnaireIds) {
        this.questionnaireIds = questionnaireIds;
    }
}
