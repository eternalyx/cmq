package com.cmq.bo.request;

import java.io.Serializable;

public class DoctorConfigurationRequestBO implements Serializable {
    private static final long serialVersionUID = 4455730348295827130L;

    /**
     * if id is null, the operation is insert, else it is update
     */
    private Integer id;

    private String name;

    private String sex;

    private String mobile;

    private String idCardNumber;

    private String organization;

    private String hospitalName;

    private String memo;

    private Integer isResponsible;

    private Integer[] districtIds;

    private Integer[] functionIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getIsResponsible() {
        return isResponsible;
    }

    public void setIsResponsible(Integer isResponsible) {
        this.isResponsible = isResponsible;
    }

    public Integer[] getDistrictIds() {
        return districtIds;
    }

    public void setDistrictIds(Integer[] districtIds) {
        this.districtIds = districtIds;
    }

    public Integer[] getFunctionIds() {
        return functionIds;
    }

    public void setFunctionIds(Integer[] functionIds) {
        this.functionIds = functionIds;
    }
}
