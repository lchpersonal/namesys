package com.bbw.namesys.service.paving;

import java.util.Date;

public class PavingRecord {
    private int id;
    private int nameInfoId;
    private String record;
    private Date pavingTime;
    private Date updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNameInfoId() {
        return nameInfoId;
    }

    public void setNameInfoId(int nameInfoId) {
        this.nameInfoId = nameInfoId;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public Date getPavingTime() {
        return pavingTime;
    }

    public void setPavingTime(Date pavingTime) {
        this.pavingTime = pavingTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
