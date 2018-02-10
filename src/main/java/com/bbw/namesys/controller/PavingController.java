package com.bbw.namesys.controller;

import com.bbw.namesys.base.Result;
import com.bbw.namesys.base.Results;
import com.bbw.namesys.service.namelist.NameInfoService;
import com.bbw.namesys.service.paving.PavingService;
import com.bbw.namesys.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

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

    @RequestMapping("namelist")
    public ModelAndView toPavingNameList(@RequestParam(defaultValue = "0") int echelon,
                                         String keyword, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/paving/pavingNamelist");
        String username = SessionUtil.getUsername(request);
        mv.addObject("data", Results.of(nameInfoService.select(username, echelon, keyword)));
        mv.addObject("keyword", keyword);
        mv.addObject("echelon", echelon);
        return mv;
    }

    @RequestMapping("/record")
    public ModelAndView pavingRecord(int id) {
        ModelAndView mv = new ModelAndView("/paving/pavingRecord");
        mv.addObject("data", Results.of(pavingService.select(id)));
        mv.addObject("id", id);
        return mv;
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
           return  pavingService.addPavingRecord(id, record, username);
        } catch (Exception e) {
            e.printStackTrace();
            return Results.exception();
        }
    }

    @RequestMapping("/editRecordPage")
    public ModelAndView editPavingRecordPage(int recordId, int nameInfoId, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/paving/editPavingRecord");
        String username = SessionUtil.getUsername(request);
        Result result = pavingService.selectPavingRecord(recordId, username);
        mv.addObject("recordId", recordId);
        mv.addObject("nameInfoId", nameInfoId);
        mv.addObject("data", result);
        return mv;
    }

    @RequestMapping("/editRecord.json")
    public Result editPavingRecord(int id, String record, HttpServletRequest request) {
        try {
            String username = SessionUtil.getUsername(request);
           return  pavingService.editPavingRecord(id, record, username);
        } catch (Exception e) {
            e.printStackTrace();
            return Results.exception();
        }
    }

}
