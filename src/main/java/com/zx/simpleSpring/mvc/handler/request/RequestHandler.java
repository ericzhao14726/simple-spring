package com.zx.simpleSpring.mvc.handler.request;

import com.zx.simpleSpring.mvc.domain.RequestMethod;
import io.netty.handler.codec.http.FullHttpRequest;

public interface RequestHandler {
    RequestMethod handler(FullHttpRequest msg);
}
