package com.bbw.namesys.env;

import com.bbw.namesys.base.AccessFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import java.util.EnumSet;
import javax.servlet.*;

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
        super.onStartup(servletContext);
    }
}
