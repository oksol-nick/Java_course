package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class SecondBean {

    public SecondBean(PrototypeBean prototypeBean) {
        System.out.println("Привет, а я всего лишь второй Бин(((");
        System.out.println("ПротоБин, второе приглашение " + prototypeBean.toString());
    }
}
