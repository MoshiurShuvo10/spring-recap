package com.moshiur.springrevision.cdi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CdiApplication {

    private static Logger logger = LoggerFactory.getLogger(CdiApplication.class);
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(CdiApplication.class);
        CDIBusiness cdiBusiness = applicationContext.getBean(CDIBusiness.class);

        logger.info("{} dao-{}",cdiBusiness,cdiBusiness.getCdiDao());
    }
}
