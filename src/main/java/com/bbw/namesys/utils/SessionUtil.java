package com.bbw.namesys.utils;

import com.bbw.namesys.service.user.User;

import javax.servlet.http.HttpServletRequest;

public class SessionUtil {


    private static final String SESSION_USER_KEY = "LOGIN_USER";

    public static String getUsername(HttpServletRequest request) {
        User user = getSessionUser(request);
        if (user == null) {
            return null;
        }
        return user.getUsername();
    }

    public static User getSessionUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute(SESSION_USER_KEY);
    }

    public static void putSessionUser(HttpServletRequest request, User user) {
        request.getSession().setAttribute(SESSION_USER_KEY, user);
    }

    public static void removeSessionUser(HttpServletRequest request) {
        request.getSession().removeAttribute(SESSION_USER_KEY);
    }

}
