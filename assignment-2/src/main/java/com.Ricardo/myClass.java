package com.Ricardo;

public class myClass {

    @InitMethod
    public void init() {
        System.out.println("调用初始化方法成功");
    }

    public int Add(int a, int b){
        return a + b;
    }
    public int Sub(int a, int b) {
        return a - b;
    }
    public int Mult(int a, int b) {
        return a * b;
    }
    public int Div(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("除数不能为零");
        }
        return a / b;

    }

}
