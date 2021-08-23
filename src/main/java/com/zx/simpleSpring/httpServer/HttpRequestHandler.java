package com.zx.simpleSpring.httpServer;

import com.zx.simpleSpring.mvc.handler.HttpHandlerFactory;
import com.zx.simpleSpring.mvc.handler.request.RequestHandler;
import com.zx.simpleSpring.mvc.handler.response.factory.ResponseHandlerFactory;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;

@ChannelHandler.Sharable
public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    public static final Logger logger = LoggerFactory.getLogger(HttpRequestHandler.class);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) {
        HttpMethod method = msg.method();
        String uri = msg.uri();
        logger.info(uri);
        logger.info(method.name());
        logger.info(msg.content().toString());
        RequestHandler requestHandle = HttpHandlerFactory.getRequestHandle(msg);
        FullHttpResponse fullHttpResponse;
        try {
            fullHttpResponse = requestHandle.handler(msg);
        } catch (Exception e) {
            fullHttpResponse = ResponseHandlerFactory.error(HttpResponseStatus.INTERNAL_SERVER_ERROR, e.toString());
        }
        boolean keepAlive = HttpUtil.isKeepAlive(msg);
        if (keepAlive) {
            fullHttpResponse.headers().set(CONNECTION, KEEP_ALIVE);
            ctx.write(fullHttpResponse);
        } else {
            ctx.write(fullHttpResponse).addListener(ChannelFutureListener.CLOSE);
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

}
