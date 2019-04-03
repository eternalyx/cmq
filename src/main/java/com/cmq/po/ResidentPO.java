package com.cmq.po;

import com.cmq.common.BasePO;

import java.util.Date;

public class ResidentPO extends BasePO {

    /** 头像 **/
    private String avatar;

    private String name;

    private String sex;

    //@Deprecated
    //private Integer age;

    private Date birthday;

    private String idCardNumber;

    /** 户籍地址 **/
    private String permanentAddress;

    /** 常住地址 **/
    private String residenceAddress;

    //private Integer districtId;

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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getResidenceAddress() {
        return residenceAddress;
    }

    public void setResidenceAddress(String residenceAddress) {
        this.residenceAddress = residenceAddress;
    }

}
