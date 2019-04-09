package com.cmq.bo.request;

import java.io.Serializable;
import java.util.List;

/**
 * accounts management request param accept
 */
public class DoctorPageRequestBO implements Serializable {
    private static final long serialVersionUID = 4125280140822573068L;

    private String name;

    private String idCardNumber;

    private Integer[] districtIds;

    private int pageSize;

    private int pageNo;

    private int limit;

    private List<Integer> searchDoctorIds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public Integer[] getDistrictIds() {
        return districtIds;
    }

    public void setDistrictIds(Integer[] districtIds) {
        this.districtIds = districtIds;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getLimit() {
        return (pageNo - 1) * pageSize;
    }

    public List<Integer> getSearchDoctorIds() {
        return searchDoctorIds;
    }

    public void setSearchDoctorIds(List<Integer> searchDoctorIds) {
        this.searchDoctorIds = searchDoctorIds;
    }
}
