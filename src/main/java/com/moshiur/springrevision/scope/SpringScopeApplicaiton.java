package com.moshiur.springrevision.scope;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
@SpringBootApplication
public class SpringScopeApplicaiton {
    private static Logger logger = LoggerFactory.getLogger(SpringScopeApplicaiton.class);
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(SpringScopeApplicaiton.class);
        /*
        *  Spring is considering the scope of PersonDAO as default which is singleton.
        *  So, everytime it'll return the same object for PersonDAO. But, as we used
        *  ProxyMode for JdbcConnection, we'll get different different jdbcConnection for
        *  every PersonDAO
        *
        * */

        PersonDAO personDAO1 = applicationContext.getBean(PersonDAO.class);
        PersonDAO personDAO2 = applicationContext.getBean(PersonDAO.class);
        logger.info("{}",personDAO1);
        logger.info("{}",personDAO1.getJdbcConccention());
        logger.info("{}",personDAO2);
        logger.info("{}",personDAO2.getJdbcConccention());
    }
}
