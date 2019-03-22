package com.cmq.bo.request;

import java.io.Serializable;

public class DistrictRequestBO implements Serializable {
    private static final long serialVersionUID = 7377677000472623574L;

    /**
     * id==null:insert
     * id!=null:update
     */
    private Integer id;

    private String name;

    private String districtCode;

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

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }
}
