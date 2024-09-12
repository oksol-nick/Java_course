package com.example.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericGroovyApplicationContext;


public class DemoApplication {
	public static void main(String[] args) {
		ApplicationContext context = new GenericGroovyApplicationContext("file:demo/groovy/application.groovy");
		PrototypeBean prototypeBean = context.getBean(PrototypeBean.class);
		FirstBean firstBean = context.getBean(FirstBean.class);
		SecondBean secondBean = context.getBean(SecondBean.class);
		RequestScopeBean requestScopeBean = context.getBean(RequestScopeBean.class);
	}
}
