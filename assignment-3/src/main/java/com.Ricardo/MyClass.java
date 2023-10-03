package com.Ricardo;

public class MyClass {
    private String name;

    public MyClass(){

    }
    public MyClass(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void sayHello() {
        System.out.println("Hello, my name is " + name);
    }
}

