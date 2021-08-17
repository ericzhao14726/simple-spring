package com.zx.simpleSpring.mvc.handler;

import com.zx.simpleSpring.mvc.handler.request.GetRequestHandlerImpl;
import com.zx.simpleSpring.mvc.handler.request.PostRequestHandlerImpl;
import com.zx.simpleSpring.mvc.handler.request.RequestHandler;
import com.zx.simpleSpring.mvc.handler.responst.GetResponseHandlerImpl;
import com.zx.simpleSpring.mvc.handler.responst.PostResponseHandlerImpl;
import com.zx.simpleSpring.mvc.handler.responst.ResponseHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;


public class HttpHandlerFactory {
    public static GetRequestHandlerImpl getRequestHandler = new GetRequestHandlerImpl();
    public static PostRequestHandlerImpl postRequestHandler =  new PostRequestHandlerImpl();

    public static GetResponseHandlerImpl getResponseHandler = new GetResponseHandlerImpl();
    public static PostResponseHandlerImpl postResponseHandler = new PostResponseHandlerImpl();

    public static RequestHandler getRequestHandle(FullHttpRequest fullHttpRequest){
        HttpMethod method = fullHttpRequest.method();
        return HttpMethod.GET.equals(method) ? getRequestHandler : postRequestHandler;
    }

    public static ResponseHandler getResponseHandler(FullHttpRequest fullHttpRequest) {
        HttpMethod method = fullHttpRequest.method();
        return HttpMethod.GET.equals(method) ? getResponseHandler : postResponseHandler;
    }
}
