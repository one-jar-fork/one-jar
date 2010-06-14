package com.simontuffs.onejar.spring.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.simontuffs.onejar.test.Testable;

public class Main extends Testable {

    public Main() {
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Transformer transformer = (Transformer) context.getBean("transformer");
        transformer.run();
    }

    public void transform() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Transformer transformer = (Transformer) context.getBean("transformer");
        transformer.run();
    }

}
