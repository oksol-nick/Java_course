package com.example.demo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class RequestScopeBean {
    public RequestScopeBean() {
        System.out.println("Привет, я РеквеСкоупБин!");
    }
}
