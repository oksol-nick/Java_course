package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.annotation.RequestScope;

@Configuration
public class DemoApplicationConfig {

    @Bean
    public FirstBean firstBean() {
        return new FirstBean(new PrototypeBean());
    }

    @Bean
    public SecondBean secondBean() {
        return new SecondBean(new PrototypeBean());
    }

    @Bean
    @Scope(value = "prototype")
    public PrototypeBean prototypeBean() {
        return new PrototypeBean();
    }

    @Bean
    @RequestScope
    public RequestScopeBean requestScopeBean() {
        return new RequestScopeBean();
    }
}
