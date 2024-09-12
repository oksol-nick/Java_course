package com.example.demo;

public class SecondBean {

    public SecondBean() {
    }

    private PrototypeBean prototypeBean;

    public PrototypeBean getPrototypeBean() {
        return prototypeBean;
    }

    public void setPrototypeBean(PrototypeBean prototypeBean) {
        this.prototypeBean = prototypeBean;
    }

    public SecondBean(PrototypeBean prototypeBean) {
        System.out.println("Привет, а я всего лишь второй Бин(((");
        System.out.println("ПротоБин, второе приглашение " + prototypeBean.toString());
    }


}
