package com.bbw.namesys.controller;

import com.bbw.namesys.base.Result;
import com.bbw.namesys.base.Results;
import com.bbw.namesys.service.namelist.EchelonInfo;
import com.bbw.namesys.service.namelist.NameInfoService;
import com.bbw.namesys.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/echelon")
public class EchelonController {

    @Autowired
    private NameInfoService nameInfoService;

    /**
     * 分梯队
     */
    @RequestMapping("do")
    public ModelAndView select(@RequestParam(defaultValue = "0") int echelon,
                               String keyword) {
        ModelAndView mv = new ModelAndView("");
        return mv;
    }

    @RequestMapping("/select.json")
    public Result getEchelonInfo(HttpServletRequest request) {
        String username = SessionUtil.getUsername(request);
        try {
            List<EchelonInfo> echelonInfos = nameInfoService.selectEchelonInfos(username);
            return Results.of(echelonInfos);
        } catch (Exception e) {
            e.printStackTrace();
            return Results.exception();
        }
    }

    @RequestMapping("/upd.json")
    public Result updEchelonInfo(int[] ids0, int[] ids1, int[] ids2, int[] ids3, HttpServletRequest request) {
        String username = SessionUtil.getUsername(request);
        try {
            nameInfoService.updEchelonInfos(username, ids0, ids1, ids2, ids3);
        } catch (Exception e) {
            e.printStackTrace();
            return Results.exception();
        }
        return Results.success();
    }

}
