package config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// another approach to create dispatcher servlet
public class ReplacementOfWebXmlApproach2 extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        Class[] configClasses = {ReplacementOfSpringConfig.class} ;
        return configClasses ;
    }

    @Override
    protected String[] getServletMappings() {
        String[] servletMappings = {"/mrahman/*"} ;
        return servletMappings ;
    }
}
