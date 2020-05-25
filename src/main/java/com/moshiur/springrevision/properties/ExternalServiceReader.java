package com.moshiur.springrevision.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ExternalServiceReader {

    @Value("${com.moshiur.service.externalUrl}")
    private String externalUrl ;
    @Value("${com.moshiur.sercie.userName}")
    private String userName ;

    public String getExternalUrl() {
        return externalUrl;
    }

    public String getUserName() {
        return userName;
    }
}
