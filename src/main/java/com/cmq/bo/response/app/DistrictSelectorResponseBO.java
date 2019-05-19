package com.cmq.bo.response.app;

import java.io.Serializable;

public class DistrictSelectorResponseBO implements Serializable {
    private static final long serialVersionUID = -4496263923422871212L;

    private Integer id;

    private String districtCode;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
