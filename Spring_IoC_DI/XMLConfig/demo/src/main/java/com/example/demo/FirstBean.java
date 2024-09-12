package com.example.demo;

public class FirstBean {

    public FirstBean() {
    }

    private SecondBean secondBean;

    public FirstBean(PrototypeBean prototypeBean) {
        System.out.println("Привет, я самый, самый, самый первый Бин!");
        System.out.println("ПротоБин, первое приглашение " + prototypeBean.toString());
    }

    public void SecondBean(SecondBean secondBean) {
        this.secondBean = secondBean;
    }
}
