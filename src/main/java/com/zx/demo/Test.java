package com.zx.demo;

import com.zx.simpleSpring.annotation.boot.SimpleSpringApplication;
import com.zx.simpleSpring.core.ApplicationContext;

@SimpleSpringApplication
public class Test {

    public static void main(String[] args) {
        ApplicationContext.run(Test.class, args);
    }

}
