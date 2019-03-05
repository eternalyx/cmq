package com.cmq.bo.response;

import java.io.Serializable;
import java.util.Date;

public class DoneQuestionnaireResidentsResponseBO implements Serializable {
    private static final long serialVersionUID = 5203290392867736250L;

    private Integer residentId;

    private String residentName;

    private String sex;

    private Integer age;

    private String idCardNumber;

    private Integer doctorId;

    private String doctorName;

    private Date lastDoneQuestionnaireTime;

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
}
