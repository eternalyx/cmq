package com.cmq.bo.response;

import java.io.Serializable;
import java.util.Date;

public class QuestionnaireResponseBO implements Serializable {
    private static final long serialVersionUID = 5376438091220844664L;

    private Integer id;

    private Integer residentId;

    private String residentName;

    private String sex;

    private Integer age;

    private String idCardNumber;

    private String avatar;

    private Date birthday;

    private String permanentAddress;

    private String residenceAddress;

    private Integer doctorId;

    private String doctorName;

    private Date lastDoneQuestionnaireTime;

    private String options;

    private String qixu;

    private String yangxu;

    private String yinxu;

    private String tanshi;

    private String shire;

    private String xueyu;

    private String qiyu;

    private String tebing;

    private String pinghe;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getResidentId() {
        return residentId;
    }

    public void setResidentId(Integer residentId) {
        this.residentId = residentId;
    }

    public String getResidentName() {
        return residentName;
    }

    public void setResidentName(String residentName) {
        this.residentName = residentName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public Date getLastDoneQuestionnaireTime() {
        return lastDoneQuestionnaireTime;
    }

    public void setLastDoneQuestionnaireTime(Date lastDoneQuestionnaireTime) {
        this.lastDoneQuestionnaireTime = lastDoneQuestionnaireTime;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getQixu() {
        return qixu;
    }

    public void setQixu(String qixu) {
        this.qixu = qixu;
    }

    public String getYangxu() {
        return yangxu;
    }

    public void setYangxu(String yangxu) {
        this.yangxu = yangxu;
    }

    public String getYinxu() {
        return yinxu;
    }

    public void setYinxu(String yinxu) {
        this.yinxu = yinxu;
    }

    public String getTanshi() {
        return tanshi;
    }

    public void setTanshi(String tanshi) {
        this.tanshi = tanshi;
    }

    public String getShire() {
        return shire;
    }

    public void setShire(String shire) {
        this.shire = shire;
    }

    public String getXueyu() {
        return xueyu;
    }

    public void setXueyu(String xueyu) {
        this.xueyu = xueyu;
    }

    public String getQiyu() {
        return qiyu;
    }

    public void setQiyu(String qiyu) {
        this.qiyu = qiyu;
    }

    public String getTebing() {
        return tebing;
    }

    public void setTebing(String tebing) {
        this.tebing = tebing;
    }

    public String getPinghe() {
        return pinghe;
    }

    public void setPinghe(String pinghe) {
        this.pinghe = pinghe;
    }
}
