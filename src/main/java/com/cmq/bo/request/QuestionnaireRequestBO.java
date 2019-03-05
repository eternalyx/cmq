package com.cmq.bo.request;

public class QuestionnaireRequestBO {

    /**
     * id==null:insert
     * id!=null:update
     */
    private Integer id;

    private Integer residentId;

    private Integer doctorId;

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

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
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
