package com.example.demo;
public class HwController {

    private RequestScopeBean requestScopeBean;

    public HwController(RequestScopeBean requestScopeBean) {
        this.requestScopeBean = requestScopeBean;
    }


    public String HW() {
        StringBuilder b = new StringBuilder();
        b.append("HW! \n\n");
        b.append("Первое приглашение РеквеСкоупБина ");
        b.append(requestScopeBean.toString());
        b.append("\nВторое приглашение РеквеСкоупБина ");
        b.append(requestScopeBean.toString());
        b.append("\n");
        return b.toString();
    }
}
