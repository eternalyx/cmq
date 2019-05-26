package com.cmq.bo.response.web;

import java.io.Serializable;

public class QuestionnaireScoreVO implements Serializable {
    private static final long serialVersionUID = 5797878960783990571L;

    private Integer score;

    private String result;

    private String guidance;

    private String guidanceOther;

    private String type;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getGuidance() {
        return guidance;
    }

    public void setGuidance(String guidance) {
        this.guidance = guidance;
    }

    public String getGuidanceOther() {
        return guidanceOther;
    }

    public void setGuidanceOther(String guidanceOther) {
        this.guidanceOther = guidanceOther;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
