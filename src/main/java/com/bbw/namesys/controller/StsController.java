package com.bbw.namesys.controller;

import com.bbw.namesys.service.sts.StsService;
import com.bbw.namesys.service.user.User;
import com.bbw.namesys.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/sts")
public class StsController {

    @Autowired
    private StsService stsService;

    @RequestMapping("selfinfo")
    public ModelAndView selfInfo(HttpServletRequest request){
        ModelAndView mv = new ModelAndView("/sts/selfinfo");
        User user = SessionUtil.getSessionUser(request);
        mv.addObject("data", stsService.selectSelfStsInfo(user.getUsername()));
        mv.addObject("user", user);
        return mv;
    }
}
