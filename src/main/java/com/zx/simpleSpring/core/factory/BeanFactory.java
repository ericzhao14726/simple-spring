package com.zx.simpleSpring.core.factory;

import com.zx.demo.controller.UserController;
import com.zx.simpleSpring.annotation.ioc.Autowired;
import com.zx.simpleSpring.util.ReflectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.zx.simpleSpring.core.factory.ClassFactory.CLASS_CONTAINER;

/**
 * 实例化 bean，并加载 bean 到 bean 容器中统一管理
 */
public class BeanFactory {
    private static final Logger logger = LoggerFactory.getLogger(BeanFactory.class);

    public static Map<String, Object> BEAN_CONTAINER = new HashMap<>();

    public static void load(String[] packages) {
        CLASS_CONTAINER.values().forEach(cs -> cs.forEach(c -> {
            try {
                // 判断是否是接口
                if (c.isInterface()) {
                    // 获取接口所有实现类
                    Set<Class<?>> anImplements = ReflectUtil.getImplements(packages, (Class<Object>) c);
                    // 没有实现 抛出异常
                    if (anImplements.size() == 0) {
                        throw new RuntimeException("class {" + c.getName() + "} is not have a implement");
                    }
                    // 只有一个实现
                    if (anImplements.size() == 1) {
                        Class<?> next = anImplements.iterator().next();
                        // 实例化 bean
                        Object o = next.newInstance();
                        // 加入 bean 容器
                        BEAN_CONTAINER.put(c.getSimpleName(), o);
                    }
                } else {

                    // 实例化 bean
                    Object o = c.newInstance();
                    // 加入 bean 容器
                    BEAN_CONTAINER.put(c.getSimpleName(), o);
                }
            } catch (InstantiationException | IllegalAccessException e) {
                logger.info("Bean instance fail -> {}", e.toString());
            }
        }));
    }

    /**
     * 给所有 @autowired 注解标识的属性注入值
     */
    public static void autowired() {
        BEAN_CONTAINER.values().forEach(o -> {
            // 获取所有属性
            Field[] fields = o.getClass().getDeclaredFields();
            for (Field field : fields) {
                try {
                    // 处理 autowired 注解
                    if (field.isAnnotationPresent(Autowired.class)) {
                        // 获取属性名称
                        String fieldName = field.getType().getSimpleName();
                        // 标识属性可见
                        field.setAccessible(true);
                        // 属性赋值
                        field.set(o, BEAN_CONTAINER.get(fieldName));
                    }
                } catch (IllegalAccessException e) {
                    logger.info("Set field value for bean fail -> {}", e.toString());
                }
            }
        });
    }

    public static Object getBean(String beanName) {
        return BEAN_CONTAINER.get(beanName);
    }
}
