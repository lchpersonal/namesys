package com.bbw.namesys.service.paving;

import com.bbw.namesys.base.Result;
import com.bbw.namesys.base.Results;
import com.bbw.namesys.mapper.PavingMapper;
import com.bbw.namesys.service.namelist.NameInfo;
import com.bbw.namesys.service.namelist.NameInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PavingService {


    @Autowired
    private PavingMapper pavingMapper;
    @Autowired
    private NameInfoService nameInfoService;

    public Result addPavingRecord(int nameInfoId, String record, String username) {
        NameInfo info = nameInfoService.selectNameInfo(nameInfoId, username);
        if (info == null) {
            return Results.accessdenied();
        }
        String newRecord =  record.replaceAll("<","&lt;").replaceAll(">","&gt;");
        pavingMapper.addPavingRecord(nameInfoId, newRecord);
        return Results.success();
    }

    public List<PavingRecord> select(int nameInfoId) {
        List<PavingRecord> records = pavingMapper.select(nameInfoId);
        for (PavingRecord record : records) {
            if(StringUtils.isNotBlank(record.getRecord())){
                record.setRecord(record.getRecord().replaceAll("\r|\n","<br/>"));
            }
        }
        return records;
    }

    public Result editPavingRecord(int recordId, String record, String username) {
        PavingRecord pavingRecord = pavingMapper.selectPavingRecord(recordId);
        if(pavingRecord == null){
            return Results.error("该记录不存在");
        }
        NameInfo info = nameInfoService.selectNameInfo(pavingRecord.getNameInfoId(), username);
        if (info == null) {
            return Results.accessdenied();
        }
        pavingMapper.editPavingRecord(recordId, record);
        return Results.success();
    }

    public Result selectPavingRecord(int recordId, String username) {
        PavingRecord record = pavingMapper.selectPavingRecord(recordId);
        if (record == null) {
            Results.error("铺垫记录不存在");
        }
        NameInfo nameInfo = nameInfoService.selectNameInfo(record.getNameInfoId(), username);
        if (nameInfo == null) {
            return Results.accessdenied();
        }
        return Results.of(record);
    }
}
