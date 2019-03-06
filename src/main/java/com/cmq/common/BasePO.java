package com.cmq.common;

import java.util.Date;

public class BasePO {

    private Integer id;

    private Integer creatorId;

    private String creatorName;

    private Date createTime;

    private Integer lastUpdateId;

    private String lastUpdateName;

    private Date lastUpdateTime;

    private Integer isDeleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public void insert(Integer creatorId, String creatorName){
        this.creatorId = creatorId;
        this.creatorName = creatorName;
        this.lastUpdateId = creatorId;
        this.lastUpdateName = creatorName;
    }

    public void update(Integer lastUpdateId, String lastUpdateName){
        this.lastUpdateId = lastUpdateId;
        this.lastUpdateName = lastUpdateName;
    }
}
