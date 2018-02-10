package com.bbw.namesys.controller;

import com.bbw.namesys.base.Constants;
import com.bbw.namesys.base.Result;
import com.bbw.namesys.base.Results;
import com.bbw.namesys.service.user.User;
import com.bbw.namesys.service.user.UserService;
import com.bbw.namesys.utils.SessionUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/password/reset.json", method = RequestMethod.POST)
    public Result resetPassword(String username, String password, String newPassword, String confirm) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return Results.error("用户名或密码不能为空");
        }
        if (StringUtils.isBlank(newPassword) || StringUtils.isBlank(confirm)) {
            return Results.error("新密码不能为空");
        }
        if (!newPassword.equals(confirm)) {
            return Results.error("两次输入的密码不一致");
        }
        User user = userService.selectUser(username);
        if (user == null) {
            return Results.error("用户名不存在");
        }
        if (!user.getPassword().equals(password)) {
            return Results.error("旧密码不正确");
        }
        userService.modifyPassword(username, newPassword);
        return Results.success();

    }

    @RequestMapping(value = "/add.json", method = RequestMethod.POST)
    public Result addUser(String username, String name, HttpServletRequest request) {
        if (!SessionUtil.getUsername(request).equals(Constants.ADMIN_USERANME)) {
            return Results.accessdenied();
        }
        if (StringUtils.isBlank(username) || StringUtils.isBlank(name)) {
            return Results.error("用户名和姓名都不能为空");
        }
        return userService.addUser(username, name);
    }

    @RequestMapping(value = "/select.json", method = RequestMethod.POST)
    public Result selectUser(HttpServletRequest request) {
        if (!SessionUtil.getUsername(request).equals(Constants.ADMIN_USERANME)) {
            return Results.accessdenied();
        }
        return Results.of(userService.selectAll());
    }

    @RequestMapping(value = "/password/adminReset.json", method = RequestMethod.POST)
    public Result adminRestPassword(HttpServletRequest request, String username) {
        if (!SessionUtil.getUsername(request).equals(Constants.ADMIN_USERANME)) {
            return Results.accessdenied();
        }
        try {
            return userService.modifyPassword(username, Constants.DEFAULT_PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            return Results.exception();
        }
    }
}
