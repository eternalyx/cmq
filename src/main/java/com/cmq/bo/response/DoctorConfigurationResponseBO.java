package com.cmq.bo.response;

import java.io.Serializable;
import java.util.List;

public class DoctorConfigurationResponseBO implements Serializable {
    private static final long serialVersionUID = -6137108058248102787L;

    private Integer id;

    private String avatar;

    private String name;

    private String sex;

    private String idCardNumber;

    private String mobile;

    private String hospitalName;

    private String memo;

    private List<Integer> districtIds;

    private Integer isResponsible;

    private List<Integer> functionIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public List<Integer> getDistrictIds() {
        return districtIds;
    }

    public void setDistrictIds(List<Integer> districtIds) {
        this.districtIds = districtIds;
    }

    public Integer getIsResponsible() {
        return isResponsible;
    }

    public void setIsResponsible(Integer isResponsible) {
        this.isResponsible = isResponsible;
    }

    public List<Integer> getFunctionIds() {
        return functionIds;
    }

    public void setFunctionIds(List<Integer> functionIds) {
        this.functionIds = functionIds;
    }
}
