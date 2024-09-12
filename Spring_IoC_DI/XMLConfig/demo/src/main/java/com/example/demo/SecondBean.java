package com.example.demo;

public class SecondBean {

    public SecondBean() {
    }

    public SecondBean(PrototypeBean prototypeBean) {
        System.out.println("Привет, а я всего лишь второй Бин(((");
        System.out.println("ПротоБин, второе приглашение " + prototypeBean.toString());
    }
}
