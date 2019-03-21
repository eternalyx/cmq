package com.cmq.bo.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DistrictTreeBO implements Serializable {
    private static final long serialVersionUID = 5203290392867735250L;

    private Integer id;

    private String name;

    private String districtCode;

    private String label;

    private List<DistrictTreeBO> children = new ArrayList<>();

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

    public String getLabel() {
        return districtCode + " : " + name;
    }

    public List<DistrictTreeBO> getChildren() {
        return children;
    }

    public void setChildren(List<DistrictTreeBO> children) {
        this.children = children;
    }
}
