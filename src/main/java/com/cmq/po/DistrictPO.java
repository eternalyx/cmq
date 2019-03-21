package com.cmq.po;

import com.cmq.common.BasePO;

/**
 * 地区字典
 */
public class DistrictPO extends BasePO {

    private String name;

    private String districtCode;

//    private String shortName;
//
//    private Integer level;
//
//    private Integer sort;
//
//    private Integer parentId;

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
