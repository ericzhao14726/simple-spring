package com.zx.simpleSpring.annotation.ioc;

import java.lang.annotation.*;

/**
 * 包路径扫描，用于扫描在当前运行包路径下的bean
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ComponentScan {
    String []values () default {};
}
