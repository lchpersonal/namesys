package com.bbw.namesys.controller;

import com.bbw.namesys.base.Result;
import com.bbw.namesys.base.Results;
import com.bbw.namesys.service.namelist.NameInfo;
import com.bbw.namesys.service.namelist.NameInfoService;
import com.bbw.namesys.service.paving.PavingRecord;
import com.bbw.namesys.service.paving.PavingService;
import com.bbw.namesys.service.paving.VPavingRecord;
import com.bbw.namesys.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 铺垫相关
 */
@Controller
@RequestMapping("/paving")
public class PavingController {

    @Autowired
    private NameInfoService nameInfoService;
    @Autowired
    private PavingService pavingService;

    @RequestMapping("/record")
    public ModelAndView pavingRecord(int id) {
        ModelAndView mv = new ModelAndView("/paving/pavingRecord");
        mv.addObject("data", Results.of(pavingService.select(id)));
        mv.addObject("id", id);
        return mv;
    }

    /**
     * 查询所有铺垫记录，每次查詢十條
     */
    @RequestMapping("/records.json")
    public Result pavingRecords(@RequestParam(defaultValue = "0") int curId,
                                HttpServletRequest request) {
        String username = SessionUtil.getUsername(request);
        if (curId == 0) {
            curId = Integer.MAX_VALUE;
        }
        List<PavingRecord> pavingRecords = pavingService.selectByUsername(username, curId);
        if (pavingRecords.isEmpty()) {
            return Results.success();
        }
        List<VPavingRecord> vPavingRecords = new ArrayList<>();
        List<Integer> nameInfoIds = pavingRecords.stream().map(PavingRecord::getNameInfoId).collect(Collectors.toList());
        Map<Integer, NameInfo> map = nameInfoService.selectNameInfosMap(nameInfoIds);
        for (PavingRecord pavingRecord : pavingRecords) {
            VPavingRecord vPavingRecord = new VPavingRecord();
            vPavingRecord.setPavingRecord(pavingRecord);
            vPavingRecord.setNameInfo(map.get(pavingRecord.getNameInfoId()));
            vPavingRecords.add(vPavingRecord);
        }
        return Results.of(vPavingRecords);
    }

    @RequestMapping("/addRecordPage")
    public ModelAndView addPavingRecordPage(int id) {
        ModelAndView mv = new ModelAndView("/paving/addPavingRecord");
        mv.addObject("id", id);
        return mv;
    }

    @RequestMapping("/addRecord.json")
    public Result addPavingRecord(int id, String record, HttpServletRequest request) {
        try {
            String username = SessionUtil.getUsername(request);
            return pavingService.addPavingRecord(id, record, username);
        } catch (Exception e) {
            e.printStackTrace();
            return Results.exception();
        }
    }

    @RequestMapping("/editRecordPage")
    public ModelAndView editPavingRecordPage(@RequestParam(defaultValue = "0") int flag,
                                             int recordId, int nameInfoId, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/paving/editPavingRecord");
        String username = SessionUtil.getUsername(request);
        Result result = pavingService.selectPavingRecord(recordId, username);
        mv.addObject("recordId", recordId);
        mv.addObject("nameInfoId", nameInfoId);
        mv.addObject("data", result);
        mv.addObject("flag", flag);
        return mv;
    }

    @RequestMapping("/editRecord.json")
    public Result editPavingRecord(int id, String record, HttpServletRequest request) {
        try {
            String username = SessionUtil.getUsername(request);
            return pavingService.editPavingRecord(id, record, username);
        } catch (Exception e) {
            e.printStackTrace();
            return Results.exception();
        }
    }

}
