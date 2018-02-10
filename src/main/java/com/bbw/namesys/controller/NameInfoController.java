package com.bbw.namesys.controller;

import com.bbw.namesys.base.Result;
import com.bbw.namesys.base.Results;
import com.bbw.namesys.service.namelist.NameDetailInfo;
import com.bbw.namesys.service.namelist.NameInfoService;
import com.bbw.namesys.utils.SessionUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/name")
public class NameInfoController {

    @Autowired
    private NameInfoService nameInfoService;

    /**
     * 根据用户名查询特定梯队的名单
     */
    @RequestMapping("select")
    public ModelAndView select(@RequestParam(defaultValue = "0") int echelon,
                               String keyword, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/name/namelist");
        String username = SessionUtil.getUsername(request);
        mv.addObject("data", Results.of(nameInfoService.select(username, echelon, keyword)));
        mv.addObject("keyword", keyword);
        mv.addObject("echelon", echelon);
        return mv;
    }

    @RequestMapping("detail")
    public ModelAndView select(@RequestParam(defaultValue = "0") int id, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("/name/nameinfo");
        if (id <= 0) {
            mv.addObject("data", Results.error("id非法"));
            return mv;
        }
        mv.addObject("data", Results.of(nameInfoService.select(id)));
        return mv;
    }

    @RequestMapping("modify")
    public Result modify(NameDetailInfo detailInfo, HttpServletRequest request) {
        String username = SessionUtil.getUsername(request);
        detailInfo.setUsername(username);
        return nameInfoService.modify(detailInfo);
    }

    @RequestMapping("add.json")
    public Result add(String names, HttpServletRequest request) {
        if (StringUtils.isBlank(names)) {
            return Results.error("名单不能为空!");
        }
        String username = SessionUtil.getUsername(request);
        try {
            return nameInfoService.addNames(username, names);
        } catch (Exception e) {
            e.printStackTrace();
            return Results.exception();
        }
    }

}
