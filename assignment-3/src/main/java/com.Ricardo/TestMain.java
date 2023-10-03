package com.Ricardo;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {
    public static void main(String[] args) {
        MiniApplicationContext context = new MiniApplicationContext("miniApplicationContext.xml");
        // 使用容器获取Bean示例
        MyClass myClass = (MyClass) context.getBean("myBean");
        myClass.sayHello();
    }
}
