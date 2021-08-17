package com.zx.simpleSpring.annotation.ioc;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Value {
    String name() default "";
}
