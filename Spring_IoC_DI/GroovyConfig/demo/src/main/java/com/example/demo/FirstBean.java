package com.example.demo;

public class FirstBean {

    private SecondBean secondBean;
    private PrototypeBean prototypeBean;

    public FirstBean() {
    }

    public SecondBean getSecondBean() {
        return secondBean;
    }

    public void setSecondBean(SecondBean secondBean) {
        this.secondBean = secondBean;
    }

    public PrototypeBean getPrototypeBean() {
        return prototypeBean;
    }

    public void setPrototypeBean(PrototypeBean prototypeBean) {
        this.prototypeBean = prototypeBean;
    }

    public FirstBean(PrototypeBean prototypeBean) {
        System.out.println("Привет, я самый, самый, самый первый Бин!");
        System.out.println("ПротоБин, первое приглашение " + prototypeBean.toString());
    }

    public void SecondBean(SecondBean secondBean) {
        this.secondBean = secondBean;
    }
}
