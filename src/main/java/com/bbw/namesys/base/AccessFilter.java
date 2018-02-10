package com.bbw.namesys.base;

import com.bbw.namesys.service.user.User;
import com.bbw.namesys.utils.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

public class AccessFilter implements Filter {
    private final static Logger Log = LoggerFactory.getLogger(AccessFilter.class);


    private static final Pattern LoginCheckWhiteList = Pattern.compile("(/login|/doLogin.json|/password|/logout|/error|/noprivilege|/static|/debug)");

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse rep = (HttpServletResponse) response;
        req.setCharacterEncoding("UTF-8");
        rep.setCharacterEncoding("UTF-8");
        String uri = req.getRequestURI();// 不带参数的
        if (LoginCheckWhiteList.matcher(uri).find()) {
            filterChain.doFilter(request, response);
            return;
        }
        try {
            User user = SessionUtil.getSessionUser(req);
            if (user == null) {
                rep.sendRedirect("/jst/sys/login");
                return;
            }
            filterChain.doFilter(request, response);
            return;
        } catch (Exception e) {
            Log.error(e.getMessage(), e);
            rep.sendRedirect("/jst/sys/error");
            return;
        }
    }

    @Override
    public void destroy() {
    }

}
