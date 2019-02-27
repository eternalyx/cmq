package com.tcm.questionnaire.po;

import com.tcm.questionnaire.common.BasePO;

public class DoctorDistrictPO extends BasePO {

    private Integer doctorId;

    private Integer districtId;

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }
}
