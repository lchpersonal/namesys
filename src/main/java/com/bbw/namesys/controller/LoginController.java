package com.bbw.namesys.controller;

import com.bbw.namesys.base.Result;
import com.bbw.namesys.base.Results;
import com.bbw.namesys.service.login.LoginService;
import com.bbw.namesys.utils.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("login")
    public ModelAndView login() {
        return new ModelAndView("/sys/login");
    }

    @RequestMapping("doLogin.json")
    public Result doLogin(String username, String password, HttpServletRequest request) {
        return loginService.login(username, password, request);
    }

    @RequestMapping("logout.json")
    public Result doLogin(HttpServletRequest request) {
        SessionUtil.removeSessionUser(request);
        return Results.success();
    }



}
