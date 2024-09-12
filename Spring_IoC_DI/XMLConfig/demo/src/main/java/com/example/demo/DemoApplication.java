package com.example.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DemoApplication {
	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		FirstBean firstBean = context.getBean(FirstBean.class);
		SecondBean secondBean = context.getBean(SecondBean.class);
		PrototypeBean prototypeBean = context.getBean(PrototypeBean.class);
		RequestScopeBean requestScopeBean = context.getBean((RequestScopeBean.class));
	}
}
