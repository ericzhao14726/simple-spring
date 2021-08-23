package com.zx.simpleSpring.core.factory;

import com.zx.simpleSpring.annotation.mvc.PostMapping;
import com.zx.simpleSpring.annotation.mvc.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.zx.simpleSpring.annotation.mvc.GetMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import io.netty.handler.codec.http.HttpMethod;

import static com.zx.simpleSpring.core.factory.ClassFactory.CLASS_CONTAINER;

/**
 * 路由工厂
 */
public class RouterFactory {
    private static final Logger logger = LoggerFactory.getLogger(RouterFactory.class);

    public static Map<HttpMethod, Map<String, Method>> ROUTER_CONTAINER = new HashMap<>();


    /**
     * 加载路由
     */
    public static void load() {
        CLASS_CONTAINER.values().forEach(cs -> cs.forEach(c -> {
            if (c.isAnnotationPresent(RestController.class)) {
                RestController annotation = c.getAnnotation(RestController.class);
                String baseUrl = annotation.value();
                Method[] methods = c.getMethods();
                for (Method method : methods) {
                    loadUrlForMethod(baseUrl, method);
                }
            }
        }));
    }

    private static void loadUrlForMethod(String baseUrl, Method m) {
            if (m.isAnnotationPresent(GetMapping.class)) {
                GetMapping annotation = (GetMapping)m.getAnnotation(GetMapping.class);
                String url = annotation.value();
                addRouter(baseUrl + url, m, HttpMethod.GET);

            }

            if (m.isAnnotationPresent(PostMapping.class)) {
                PostMapping annotation = (PostMapping)m.getAnnotation(PostMapping.class);
                String url = annotation.value();
                addRouter(baseUrl + url, m, HttpMethod.POST);
        }
    }

    private static void addRouter(String url, Method m, HttpMethod hm) {
        ROUTER_CONTAINER.values().forEach(r -> {
            if (r.containsKey(url)) {
                throw new RuntimeException("router container has contains a router name:" + url + "");
            }
        });
        Map<String, Method> map = ROUTER_CONTAINER.get(hm);
        if (null == map) {
            map = new HashMap<>();
        }
        map.put(url, m);
        ROUTER_CONTAINER.put(hm, map);
    }

    public static Method getRouter(HttpMethod m, String url) {
         return ROUTER_CONTAINER.get(m).get(url);
    }
}
