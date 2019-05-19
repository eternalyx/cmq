package com.cmq.bo.request.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QuestionnairePageRequestBO implements Serializable {
    private static final long serialVersionUID = 2701935168928209009L;

    private String residentName;

    private String idCardNumber;

    //to transfer
    private List<Integer> residentIds = new ArrayList<>();

    private int pageSize;

    private int pageNo;

    private int limit;

    public String getResidentName() {
        return residentName;
    }

    public void setResidentName(String residentName) {
        this.residentName = residentName;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public List<Integer> getResidentIds() {
        return residentIds;
    }

    public void setResidentIds(List<Integer> residentIds) {
        this.residentIds = residentIds;
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
}
