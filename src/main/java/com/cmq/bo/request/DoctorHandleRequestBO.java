package com.cmq.bo.request;

import java.io.Serializable;

public class DoctorHandleRequestBO implements Serializable {
    private static final long serialVersionUID = 5195193012370683458L;

    //change usage_state
    //transform this to doctor ids, and then handle in a unified framework
    private Integer doctorId;

    private Integer[] doctorIds;

    private String usageState;

    //change password
    private Integer id;

    private String password;

    //normal fields
    private Integer lastUpdateId;

    private String lastUpdateName;

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public Integer[] getDoctorIds() {
        return doctorIds;
    }

    public void setDoctorIds(Integer[] doctorIds) {
        this.doctorIds = doctorIds;
    }

    public String getUsageState() {
        return usageState;
    }

    public void setUsageState(String usageState) {
        this.usageState = usageState;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getLastUpdateId() {
        return lastUpdateId;
    }

    public void setLastUpdateId(Integer lastUpdateId) {
        this.lastUpdateId = lastUpdateId;
    }

    public String getLastUpdateName() {
        return lastUpdateName;
    }

    public void setLastUpdateName(String lastUpdateName) {
        this.lastUpdateName = lastUpdateName;
    }
}
