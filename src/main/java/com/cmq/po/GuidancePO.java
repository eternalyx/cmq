package com.cmq.po;

import com.cmq.common.BasePO;

public class GuidancePO extends BasePO {

    private String constitutionalType;

    private Integer serialNumber;

    private String healthGuidance;

    private String guidanceDetails;

    public String getConstitutionalType() {
        return constitutionalType;
    }

    public void setConstitutionalType(String constitutionalType) {
        this.constitutionalType = constitutionalType;
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getHealthGuidance() {
        return healthGuidance;
    }

    public void setHealthGuidance(String healthGuidance) {
        this.healthGuidance = healthGuidance;
    }

    public String getGuidanceDetails() {
        return guidanceDetails;
    }

    public void setGuidanceDetails(String guidanceDetails) {
        this.guidanceDetails = guidanceDetails;
    }
}
