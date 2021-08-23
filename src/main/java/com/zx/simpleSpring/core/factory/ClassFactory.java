package com.zx.simpleSpring.core.factory;


import com.zx.simpleSpring.annotation.ioc.Service;
import com.zx.simpleSpring.annotation.mvc.RestController;
import com.zx.simpleSpring.util.ReflectUtil;
import com.zx.simpleSpring.annotation.ioc.Component;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 类工厂
 */
public class ClassFactory {

    public static Map<Class<? extends Annotation>, Set<Class<?>>> CLASS_CONTAINER = new HashMap<>();

    /**
     * 加载所有 ioc 注解标识的类到类容器中
     * @param packages
     */
    public static void load(String[] packages) {
        // 获取每个 package 下的注解 class
        Set<Class<?>> controllers = ReflectUtil.scanAnnotationClassWithPackages(packages, RestController.class);
        Set<Class<?>> components = ReflectUtil.scanAnnotationClassWithPackages(packages, Component.class);
        Set<Class<?>> services = ReflectUtil.scanAnnotationClassWithPackages(packages, Service.class);
        CLASS_CONTAINER.put(RestController.class, controllers);
        CLASS_CONTAINER.put(Component.class, components);
        CLASS_CONTAINER.put(Service.class, services);
    }
}
