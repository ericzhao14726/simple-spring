package com.zx.simpleSpring.core.factory;


import com.zx.simpleSpring.annotation.ioc.Service;
import com.zx.simpleSpring.annotation.mvc.RestController;
import com.zx.simpleSpring.core.Application;
import com.zx.simpleSpring.util.ReflectUtil;
import com.zx.simpleSpring.annotation.ioc.Component;

import java.util.Set;

/**
 * 类工厂
 */
public class ClassFactory {
    /**
     * 加载所有 ioc 注解标识的类到类容器中
     * @param packages
     */
    public static void load(String[] packages) {
        // 获取每个 package 下的注解 class
        Set<Class<?>> controllers = ReflectUtil.scanAnnotationClassWithPackages(packages, RestController.class);
        Set<Class<?>> components = ReflectUtil.scanAnnotationClassWithPackages(packages, Component.class);
        Set<Class<?>> services = ReflectUtil.scanAnnotationClassWithPackages(packages, Service.class);
        Application.CLASS_CONTAINER.put(RestController.class, controllers);
        Application.CLASS_CONTAINER.put(Component.class, components);
        Application.CLASS_CONTAINER.put(Service.class, services);
    }
}
