package config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class ReplacementOfWebXml implements WebApplicationInitializer {
    @Override
    public void onStartup(javax.servlet.ServletContext servletContext) throws ServletException {
        System.out.println("Tomcat picked this class..");

/*        if my spring config file is in xml, then use the following approach
        XmlWebApplicationContext xmlWebApplicationContext = new XmlWebApplicationContext() ;
        xmlWebApplicationContext.setConfigLocation("classpath:spring-config.xml");*/

//        if my spring config file is in java confg,, then use the following approach
        AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext = new AnnotationConfigWebApplicationContext() ;
        annotationConfigWebApplicationContext.register(ReplacementOfSpringConfig.class);


        // create a dispatcher servlet object
        DispatcherServlet dispatcherServlet = new DispatcherServlet(annotationConfigWebApplicationContext) ;

        // register the ds with servlet context
        ServletRegistration.Dynamic myDispatcherServlet = servletContext.addServlet("myDispatcherServlet", dispatcherServlet);

        // url mapping and initialization criteria of dispatcher servlet
        myDispatcherServlet.setLoadOnStartup(1);
        myDispatcherServlet.addMapping("/mrahman/*") ;

    }
}
