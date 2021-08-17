package com.zx.simpleSpring.httpServer;

import com.zx.simpleSpring.mvc.handler.HttpHandlerFactory;
import com.zx.simpleSpring.mvc.handler.request.RequestHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

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
        try {
            requestHandle.handler(msg);
        } catch (Exception e) {
            logger.info("http request fail -> {}", e.getMessage());
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
