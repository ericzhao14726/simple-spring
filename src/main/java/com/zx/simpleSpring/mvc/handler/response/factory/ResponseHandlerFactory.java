package com.zx.simpleSpring.mvc.handler.response.factory;

import com.zx.simpleSpring.util.JsonUtil;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ResponseHandlerFactory {
    private static final Logger logger = LoggerFactory.getLogger(ResponseHandlerFactory.class);

    public static FullHttpResponse success(Object res) {
        byte[] bytes = JsonUtil.toByte(res);
        FullHttpResponse fullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK, Unpooled.wrappedBuffer(bytes));
        fullHttpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "application/json");
        fullHttpResponse.headers().setInt(HttpHeaderNames.CONTENT_LENGTH, fullHttpResponse.content().readableBytes());
        return fullHttpResponse;
    }

    public static FullHttpResponse successVoid() {
        FullHttpResponse fullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        fullHttpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "application/json");
        fullHttpResponse.headers().setInt(HttpHeaderNames.CONTENT_LENGTH, fullHttpResponse.content().readableBytes());
        return fullHttpResponse;
    }

    public static FullHttpResponse error(HttpResponseStatus status, String msg) {
        byte[] bytes = JsonUtil.toByte(msg);
        FullHttpResponse fullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                status, Unpooled.wrappedBuffer(bytes));
        fullHttpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "application/json");
        fullHttpResponse.headers().setInt(HttpHeaderNames.CONTENT_LENGTH, fullHttpResponse.content().readableBytes());
        return fullHttpResponse;
    }

    public static FullHttpResponse error404() {
        FullHttpResponse fullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NOT_FOUND);
        fullHttpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "application/json");
        fullHttpResponse.headers().setInt(HttpHeaderNames.CONTENT_LENGTH, fullHttpResponse.content().readableBytes());
        return fullHttpResponse;
    }

}
