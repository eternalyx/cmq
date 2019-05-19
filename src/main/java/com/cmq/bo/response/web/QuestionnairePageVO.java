package com.cmq.bo.response.web;

import java.io.Serializable;
import java.util.List;

public class QuestionnairePageVO implements Serializable {
    private static final long serialVersionUID = 1964941630829090944L;

    private List<QuestionnaireVO> questionnaires;

    private Integer pageSize;

    private Integer pageNo;

    private Integer total;

    public List<QuestionnaireVO> getQuestionnaires() {
        return questionnaires;
    }

    public void setQuestionnaires(List<QuestionnaireVO> questionnaires) {
        this.questionnaires = questionnaires;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
