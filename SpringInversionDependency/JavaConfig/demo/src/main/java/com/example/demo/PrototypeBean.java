package com.example.demo;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class PrototypeBean {

    public PrototypeBean() {
                System.out.println("Привет, я ПротоБин, я родился!");
    }
}
