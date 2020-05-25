package com.moshiur.springrevision.scope;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/*
* JdbcConnection is a dependency of PersonDAO class. As scope of PersonDAO is singleton,
* for the first time, it'll create a bean with a JdbcConnection bean. And for the furtehr
* subsequent request for PersonDAO object, it'll return the old object with the same jdbcConnection
* bean dependency.
*
* So, We want a new jdbcConnection bean every time i.e we want to scope JdbcConnection as Prototype
* To scope the dependencies, We use proxyMode.
*
* @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE,
        proxyMode = ScopedProxyMode.TARGET_CLASS)
* */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE,
        proxyMode = ScopedProxyMode.TARGET_CLASS)
public class JdbcConccention {
    public JdbcConccention(){
        System.out.println("JdbcConnection created..");
    }
}
