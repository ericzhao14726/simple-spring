package com.zx.simpleSpring.mvc.handler.request;

import com.zx.simpleSpring.mvc.domain.RequestMethod;
import io.netty.handler.codec.http.FullHttpRequest;

public class PostRequestHandlerImpl implements RequestHandler {
    @Override
    public RequestMethod handler(FullHttpRequest msg) {
        return null;
    }
}
