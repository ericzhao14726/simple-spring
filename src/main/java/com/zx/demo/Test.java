package com.zx.demo;

import com.zx.simpleSpring.annotation.boot.SimpleSpringApplication;
import com.zx.simpleSpring.core.Application;

@SimpleSpringApplication
public class Test {

    public static void main(String[] args) {
        Application.run(Test.class, args);
    }

}
