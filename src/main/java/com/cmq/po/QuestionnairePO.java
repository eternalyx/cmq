package com.cmq.po;

import com.cmq.common.BasePO;

public class QuestionnairePO extends BasePO {

    private Integer residentId;

    /**
     * 等价于creator_id,创建医生id
     */
    private Integer doctorId;

    /** 辨识题33题答案**/
    private String options;

    /** 气虚质 **/
    private String qixu;

    /** 阳虚质 **/
    private String yangxu;

    /** 阴虚质 **/
    private String yinxu;

    /** 痰湿质 **/
    private String tanshi;

    /** 湿热质 **/
    private String shire;

    /** 血瘀质 **/
    private String xueyu;

    /** 气郁质 **/
    private String qiyu;

    /** 特禀质 **/
    private String tebing;

    /** 平和质 **/
    private String pinghe;

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
