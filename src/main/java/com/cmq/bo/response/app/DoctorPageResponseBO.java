package com.cmq.bo.response.app;

import java.io.Serializable;
import java.util.List;

public class DoctorPageResponseBO implements Serializable {
    private static final long serialVersionUID = 6808763528741348959L;

    private List<DoctorResponseBO> doctors;

    private Integer pageSize;

    private Integer pageNo;

    private Integer total;

    public List<DoctorResponseBO> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<DoctorResponseBO> doctors) {
        this.doctors = doctors;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
