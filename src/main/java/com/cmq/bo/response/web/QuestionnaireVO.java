package com.cmq.bo.response.web;

import java.io.Serializable;

public class QuestionnaireVO implements Serializable {
    private static final long serialVersionUID = 4201695592125390692L;

    private Integer id;

    private Integer residentId;

    private String residentName;

    private String sex;

    private String idCardNumber;

    private Integer doctorId;

    private String doctorName;

    private String guidanceTime;

    private String result;

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

    public String getGuidanceTime() {
        return guidanceTime;
    }

    public void setGuidanceTime(String guidanceTime) {
        this.guidanceTime = guidanceTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
