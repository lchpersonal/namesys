package com.bbw.namesys.controller;

import com.bbw.namesys.utils.SessionUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @RequestMapping(value = "/")
    public ModelAndView home(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("user", SessionUtil.getSessionUser(request));
        return mv;
    }
} 