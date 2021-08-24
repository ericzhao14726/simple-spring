package com.zx.simpleSpring.mvc.handler.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.zx.simpleSpring.core.factory.BeanFactory;
import com.zx.simpleSpring.core.factory.RouterFactory;
import com.zx.simpleSpring.mvc.domain.RequestMethod;
import com.zx.simpleSpring.mvc.handler.response.ResponseHandler;
import com.zx.simpleSpring.mvc.handler.response.factory.ResponseHandlerFactory;
import com.zx.simpleSpring.util.JsonUtil;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;

import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class PostRequestHandlerImpl implements RequestHandler {

    @Override
    public FullHttpResponse handler(FullHttpRequest msg) {
        // 判断请求体格式
        checkContentType(msg);
        // 获取路由
        Method method = RouterFactory.getRouter(HttpMethod.POST, msg.uri());
        if (null == method) {
            return ResponseHandlerFactory.error404();
        }
        // 获取bean
        Object bean = BeanFactory.getBean(method.getDeclaringClass().getSimpleName());
        // 解析请求参数
        Object[] params;
        try {
            params = parseParam(method, msg);
        } catch (JsonProcessingException e) {
            return ResponseHandlerFactory.error(HttpResponseStatus.INTERNAL_SERVER_ERROR, e.toString());
        }

        return ResponseHandler.handler(bean, method, params);
    }

    private Object[] parseParam(Method method, FullHttpRequest msg) throws JsonProcessingException {
        List<Object> list = new ArrayList<>();
        String jsonStr = msg.content().toString(StandardCharsets.UTF_8);
        Class<?> parameterType = method.getParameterTypes()[0];
        Object o = JsonUtil.parseToObject(jsonStr, parameterType);
        list.add(o);
        return list.toArray();
    }

    private void checkContentType(FullHttpRequest msg) {
        String s = msg.headers().get("Content-Type").split(";")[0];
        if (!"application/json".equals(s)) {
            throw new RuntimeException("only allow requests whose content type is json");
        }
    }
}
