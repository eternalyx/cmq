package com.cmq.po;

import com.cmq.common.BasePO;

public class DoctorFunctionPO extends BasePO {

    private Integer doctorId;

    private Integer functionId;

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public Integer getFunctionId() {
        return functionId;
    }

    public void setFunctionId(Integer functionId) {
        this.functionId = functionId;
    }
}
