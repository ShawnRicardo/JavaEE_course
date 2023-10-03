package com.Ricardo;

public class UseMyClass {
    private MyClass myBean;

    public void setMyBean(MyClass myBean) {
        this.myBean = myBean;
    }

    public void doSomething() {
        myBean.sayHello();
    }
}
