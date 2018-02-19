package com.bbw.namesys.service.paving;

import com.bbw.namesys.service.namelist.NameInfo;

public class VPavingRecord {
    private PavingRecord pavingRecord;
    private NameInfo nameInfo;

    public PavingRecord getPavingRecord() {
        return pavingRecord;
    }

    public void setPavingRecord(PavingRecord pavingRecord) {
        this.pavingRecord = pavingRecord;
    }

    public NameInfo getNameInfo() {
        return nameInfo;
    }

    public void setNameInfo(NameInfo nameInfo) {
        this.nameInfo = nameInfo;
    }
}
