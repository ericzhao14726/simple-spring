package com.zx.simpleSpring.util;

import com.zx.simpleSpring.annotation.ioc.Autowired;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * 反射工具类
 */
public class ReflectUtil {

    /**
     * 根据包名获取该包下所有包含此 annotation 的 class
     * @param packages 包名数组
     * @param annotation 注解
     * @return class 集合
     */
    public static Set<Class<?>> scanAnnotationClassWithPackages(String[] packages, Class<? extends Annotation> annotation) {
        Reflections reflections = new Reflections(packages);
        return reflections.getTypesAnnotatedWith(annotation, true);
    }


    public static Set<Field> scanAnnotationWithAutowired(Class<?> c) {
        Reflections reflections = new Reflections(c, new FieldAnnotationsScanner());
        return reflections.getFieldsAnnotatedWith(Autowired.class);
    }

    public static <T> Set<Class<? extends T>> getImplements(String[] packages, Class<T> c) {
        Reflections reflections = new Reflections(packages);
        return reflections.getSubTypesOf(c);
    }

    public static Object executeMethod(Object o, Method m, Object... args) throws InvocationTargetException, IllegalAccessException {
        return m.invoke(o, args);
    }

    public static void executeMethodVoid(Object o, Method m, Object... args) throws InvocationTargetException, IllegalAccessException {
        m.invoke(o, args);
    }
}
