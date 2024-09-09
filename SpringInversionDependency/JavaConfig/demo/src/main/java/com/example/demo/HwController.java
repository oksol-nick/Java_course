package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HwController {

    private RequestScopeBean requestScopeBean;

    public HwController(RequestScopeBean requestScopeBean) {
        this.requestScopeBean = requestScopeBean;
    }

    @GetMapping("/")
    private String HW() {
        StringBuilder b = new StringBuilder();
        b.append("HW! \n\n");
        b.append("Первое приглашение РеквеСкоупБина ");
        b.append(requestScopeBean.toString());
        b.append("Второе приглашение РеквеСкоупБина ");
        b.append(requestScopeBean.toString());
        b.append("\n");
        return b.toString();
    }
}
