package com.zx.simpleSpring.annotation.mvc;

import com.zx.simpleSpring.annotation.ioc.Component;

import java.lang.annotation.*;


@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Component
public @interface GetMapping {
    String value() default "";
}
