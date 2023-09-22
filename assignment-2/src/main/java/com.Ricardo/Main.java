package com.Ricardo;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws Exception{

        // 读取属性文件
        Properties  properties = new Properties();
        try(InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("myapp.properties")) {
            properties.load(inputStream);
        }
        catch (IOException e) {
            e.printStackTrace();
            return;
        }


        // 获取配置的类名
        String className = properties.getProperty("bootstrapClass");

        // 加载类
        Class<?> clazz = Class.forName(className);

        // 创建对象
        Object obj = clazz.getDeclaredConstructor().newInstance();

        // 查找并调用带有@InitMethod注解的初始化方法
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(InitMethod.class)) {
                method.invoke(obj);
                break; // 只调用第一个带有@InitMethod注解的方法
            }
        }


    }
}