package com.bbw.namesys.service.sts;

public class SelfStsInfo {

    private int nameCount;  //名单总人数
    private int eleclon1Count; //第一梯队人数
    private int eleclon2Count;  //第二梯队人数
    private int eleclon3Count;  //第三梯队人数
    private int uneleclonCount;  //未分梯队数
    private int pavingCount;    //铺垫过的人数
    private int unpavingCount;    //未铺垫过的人数
    private int pavingMonthCount; //近一个月铺垫人数
    private int pavingWeekCount; //近一周铺垫人数

    public int getUneleclonCount() {
        return uneleclonCount;
    }

    public void setUneleclonCount(int uneleclonCount) {
        this.uneleclonCount = uneleclonCount;
    }

    public int getNameCount() {
        return eleclon1Count+eleclon2Count+eleclon3Count+uneleclonCount;
    }

    public void setNameCount(int nameCount) {
        this.nameCount = nameCount;
    }

    public int getEleclon1Count() {
        return eleclon1Count;
    }

    public void setEleclon1Count(int eleclon1Count) {
        this.eleclon1Count = eleclon1Count;
    }

    public int getEleclon2Count() {
        return eleclon2Count;
    }

    public void setEleclon2Count(int eleclon2Count) {
        this.eleclon2Count = eleclon2Count;
    }

    public int getEleclon3Count() {
        return eleclon3Count;
    }

    public void setEleclon3Count(int eleclon3Count) {
        this.eleclon3Count = eleclon3Count;
    }

    public int getPavingCount() {
        return pavingCount;
    }

    public void setPavingCount(int pavingCount) {
        this.pavingCount = pavingCount;
    }

    public int getUnpavingCount() {
        return getNameCount() - getPavingCount();
    }

    public void setUnpavingCount(int unpavingCount) {
        this.unpavingCount = unpavingCount;
    }

    public int getPavingMonthCount() {
        return pavingMonthCount;
    }

    public void setPavingMonthCount(int pavingMonthCount) {
        this.pavingMonthCount = pavingMonthCount;
    }

    public int getPavingWeekCount() {
        return pavingWeekCount;
    }

    public void setPavingWeekCount(int pavingWeekCount) {
        this.pavingWeekCount = pavingWeekCount;
    }
}
