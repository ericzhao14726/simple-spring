package com.zx.simpleSpring.annotation.boot;

import com.zx.simpleSpring.annotation.ioc.ComponentScan;

import java.lang.annotation.*;

/**
 * 标识项目为 SimpleSpring 项目
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@ComponentScan
public @interface SimpleSpringApplication {
}
