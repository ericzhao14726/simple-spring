package com.zx.simpleSpring.core;

import com.zx.simpleSpring.core.factory.BeanFactory;
import com.zx.simpleSpring.core.factory.ClassFactory;
import com.zx.simpleSpring.core.factory.RouterFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.zx.demo.Test;
import com.zx.simpleSpring.annotation.ioc.ComponentScan;
import com.zx.simpleSpring.httpServer.Server;
import com.zx.simpleSpring.util.BannerUtil;

import java.lang.annotation.Annotation;
import java.util.*;


import static sun.invoke.util.VerifyAccess.getPackageName;

public class Application {
    public static Logger logger = LoggerFactory.getLogger(Application.class);

    public static Application APPLICATION = new Application();

    public static Map<Class<? extends Annotation>, Set<Class<?>>> CLASS_CONTAINER = new HashMap<>();

    public static Map<String, Object> BEAN_CONTAINER = new HashMap<>();


    public static void run(Class<Test> c, String[] args) {
        APPLICATION.run(c);
    }


    public void run(Class<Test> c) {
        long startTime = new Date().getTime();
        start(c);
        long endTime = new Date().getTime();
        logger.info("SimpleSpringApplication started for {} Second.", (endTime - startTime) / 1000);
    }

    private void start(Class<Test> c) {
        // banner 打印
        BannerUtil.print();
        // 扫描包路径
        String[] packageNames = scanPackageNames(c);
        // 加载类
        ClassFactory.load(packageNames);
        // 加载 bean
        BeanFactory.load(packageNames);
        // bean 注入属性
        BeanFactory.autowired();
        // 加载路由
        RouterFactory.load();
        // 启动服务
        Server.start();
    }

    private String[] scanPackageNames(Class<Test> c) {
        // 当前路径
        String baseName = getPackageName(c);
        // 扫描 @ComponentScan 注解标识的包路径
        ComponentScan annotation = c.getAnnotation(ComponentScan.class);
        // 如果为空返回当前路径
        return null == annotation ? new String[]{baseName}
                : annotation.values();
    }
}
