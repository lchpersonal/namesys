package com.bbw.namesys.env;

import com.bbw.namesys.base.AccessFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import java.util.EnumSet;
import java.util.concurrent.TimeUnit;
import javax.servlet.*;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * 配置ContextLoaderListener
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RootConfig.class};
    }

    /**
     * 配置DispatcherServlet
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{MvcConfig.class};
    }

    /**
     * 配置ServletMappings
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }


    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.addFilter("AccessFilter", new AccessFilter()).
                addMappingForServletNames(EnumSet.of(DispatcherType.REQUEST), true, this.getServletName());
        servletContext.addListener(new HttpSessionListener() {
            @Override
            public void sessionCreated(HttpSessionEvent se) {
                se.getSession().setMaxInactiveInterval((int) TimeUnit.HOURS.toSeconds(6));
            }
            @Override
            public void sessionDestroyed(HttpSessionEvent se) {

            }
        });
        super.onStartup(servletContext);
    }
}
