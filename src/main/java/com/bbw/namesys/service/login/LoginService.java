package com.bbw.namesys.service.login;

import com.bbw.namesys.base.Result;
import com.bbw.namesys.base.Results;
import com.bbw.namesys.service.user.User;
import com.bbw.namesys.service.user.UserService;
import com.bbw.namesys.utils.SessionUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class LoginService {

    @Autowired
    private UserService userService;

    public Result login(String username, String password, HttpServletRequest request) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            return Results.error("用户名及密码不能为空");
        }
        User user = userService.selectUser(username);
        if (user == null) {
            return Results.error("该用户不存在");
        }
        if (!password.equals(user.getPassword())) {
            return Results.error("用户名或密码错误");
        }
        SessionUtil.putSessionUser(request, user);
        return Results.success();
    }
}
