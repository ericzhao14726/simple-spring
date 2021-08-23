package com.zx.simpleSpring.mvc.handler.request;

import com.google.common.base.Converter;
import com.sun.corba.se.impl.orbutil.ObjectUtility;
import com.sun.tools.javac.util.Convert;
import com.zx.simpleSpring.core.factory.BeanFactory;
import com.zx.simpleSpring.core.factory.RouterFactory;
import com.zx.simpleSpring.mvc.domain.RequestMethod;
import com.zx.simpleSpring.mvc.handler.response.ResponseHandler;
import com.zx.simpleSpring.mvc.handler.response.factory.ResponseHandlerFactory;
import com.zx.simpleSpring.util.JsonUtil;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.util.internal.ObjectUtil;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetRequestHandlerImpl implements RequestHandler {

    @Override
    public FullHttpResponse handler(FullHttpRequest msg) {
        // 解析 url
        String url = parseUrl(msg);
        // 获取路由
        Method method = RouterFactory.getRouter(HttpMethod.GET, url);
        if (null == method) {
            return ResponseHandlerFactory.error404();
        }
        // 获取bean
        Object bean = BeanFactory.getBean(method.getDeclaringClass().getSimpleName());
        // 解析参数
        final Object[] params = parseParam(method, msg);

        return ResponseHandler.handler(bean, method, params);
    }

    private Object[] parseParam(Method method, FullHttpRequest msg) {
        List<Object> temp = new ArrayList<>();
        List<Object> list = new ArrayList<>();
        if (!msg.uri().contains("?")) {
            return list.toArray();
        }
        String[] strings = msg.uri().split("\\?")[1].split("&");
        for (String string : strings) {
            if ("".equals(string)) {
                continue;
            }
            String[] split = string.split("=");
            temp.add(split[1]);
        }

        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            Object o = JsonUtil.convertObject(temp.get(i), parameters[i].getType());
            list.add(o);
        }
        return list.toArray();
    }

    private String parseUrl(FullHttpRequest msg) {
        return msg.uri().split("\\?")[0];
    }

}
