package com.bbw.namesys.service.sts;

import com.bbw.namesys.base.Result;
import com.bbw.namesys.base.Results;
import com.bbw.namesys.mapper.StsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StsService {

    @Autowired
    private StsMapper stsMapper;

    public Result selectSelfStsInfo(String username){
        List<EchelonCountPair> echelonCountPairs = stsMapper.selectEchelonCountPairs(username);
        SelfStsInfo selfStsInfo = new SelfStsInfo();
        echelonCountPairs.forEach(echelonCountPair->{
            if (echelonCountPair.getEchelon() == 0) {
                selfStsInfo.setUneleclonCount(echelonCountPair.getCount());
            } else if (echelonCountPair.getEchelon() == 1) {
                selfStsInfo.setEleclon1Count(echelonCountPair.getCount());
            } else if (echelonCountPair.getEchelon() == 2) {
                selfStsInfo.setEleclon2Count(echelonCountPair.getCount());
            } else if (echelonCountPair.getEchelon() == 3) {
                selfStsInfo.setEleclon3Count(echelonCountPair.getCount());
            }
        });
        selfStsInfo.setPavingWeekCount(stsMapper.selectPavingStsInfo(username, 7));
        selfStsInfo.setPavingWeekCount(stsMapper.selectPavingStsInfo(username, 30));
        selfStsInfo.setPavingCount(stsMapper.selectPavingCount(username));
        return Results.of(selfStsInfo);
    }
}
