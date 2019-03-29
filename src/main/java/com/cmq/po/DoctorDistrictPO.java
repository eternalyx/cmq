package com.cmq.po;

import com.cmq.common.BasePO;

/**
 * 医生地区关联表
 */
public class DoctorDistrictPO extends BasePO {

    private Integer doctorId;

    private Integer districtId;

    private String districtCode;

    private Integer isResponsible;

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

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public Integer getIsResponsible() {
        return isResponsible;
    }

    public void setIsResponsible(Integer isResponsible) {
        this.isResponsible = isResponsible;
    }
}
