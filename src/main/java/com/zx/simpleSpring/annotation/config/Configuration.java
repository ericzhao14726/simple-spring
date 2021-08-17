package com.zx.simpleSpring.annotation.config;

import com.zx.simpleSpring.annotation.ioc.Component;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Component
public @interface Configuration {
}
