package com.simontuffs.spring.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"beans.xml");
		Transformer transformer = (Transformer) context
				.getBean("transformer");
		transformer.run();
	}

}
