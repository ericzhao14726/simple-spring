package com.zx.simpleSpring.mvc.handler.request;

import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;

public interface RequestHandler {
    FullHttpResponse handler(FullHttpRequest msg);
}
