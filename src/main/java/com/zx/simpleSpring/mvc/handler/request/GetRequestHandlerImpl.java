package com.zx.simpleSpring.mvc.handler.request;

import com.zx.simpleSpring.mvc.domain.RequestMethod;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;

import java.util.HashMap;
import java.util.Map;

public class GetRequestHandlerImpl implements RequestHandler {

    @Override
    public RequestMethod handler(FullHttpRequest msg) {
        RequestMethod requestMethod = parseParam(msg);


        return null;
    }

    private RequestMethod parseParam(FullHttpRequest msg) {
        Map<String, String> res = new HashMap<>();
        String[] strings = msg.uri().split("\\?")[1].split("&");
        for (String string : strings) {
            if ("".equals(string)) {
                continue;
            }
            String[] split = string.split("=");
            res.put(split[0], split[1]);
        }
        return new RequestMethod(msg.uri(), res);
    }

}
