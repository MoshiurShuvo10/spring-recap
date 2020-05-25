package com.moshiur.springrevision.properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class SpringPropertiesApplication {
    private static Logger logger = LoggerFactory.getLogger(SpringPropertiesApplication.class);
    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(SpringPropertiesApplication.class);
        ExternalServiceReader externalServiceReader = applicationContext.getBean(ExternalServiceReader.class);
        logger.info("External Url: "+externalServiceReader.getExternalUrl());
        logger.info("User Name: "+externalServiceReader.getUserName());
    }
}
