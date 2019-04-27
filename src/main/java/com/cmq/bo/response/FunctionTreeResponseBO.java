package com.cmq.bo.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FunctionTreeResponseBO implements Serializable {
    private static final long serialVersionUID = -4507501315689273960L;

    private Integer id;

    private String name;

    private String label;

    private List<FunctionTreeResponseBO> children = new ArrayList<>();

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

    public String getLabel() {
        return name;
    }

    public List<FunctionTreeResponseBO> getChildren() {
        return children;
    }

    public void setChildren(List<FunctionTreeResponseBO> children) {
        this.children = children;
    }
}
