package com.zx.simpleSpring.mvc.handler.response;

import com.zx.simpleSpring.mvc.handler.response.factory.ResponseHandlerFactory;
import com.zx.simpleSpring.util.ReflectUtil;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class ResponseHandler {

    public static FullHttpResponse handler(Object bean, Method method, Object[] args) {
        if (method.getReturnType().equals(Void.TYPE)) {
            try {
                ReflectUtil.executeMethodVoid(bean, method, args);
                return ResponseHandlerFactory.successVoid();
            } catch (InvocationTargetException | IllegalAccessException e) {
                return ResponseHandlerFactory.error(HttpResponseStatus.INTERNAL_SERVER_ERROR, e.toString());
            }
        } else {
            // 执行方法
            try {
                Object result = ReflectUtil.executeMethod(bean, method, args);
                return ResponseHandlerFactory.success(result);
            } catch (InvocationTargetException | IllegalAccessException e) {
                return ResponseHandlerFactory.error(HttpResponseStatus.INTERNAL_SERVER_ERROR, e.toString());
            }
        }
    }
}
