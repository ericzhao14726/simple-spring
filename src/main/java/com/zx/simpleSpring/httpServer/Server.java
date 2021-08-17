package com.zx.simpleSpring.httpServer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * netty http server
 */
public class Server {
    public static final Logger logger = LoggerFactory.getLogger(Server.class);

    public static void start() {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup work = new NioEventLoopGroup();
        serverBootstrap.group(boss, work)
                .handler(new LoggingHandler(LogLevel.INFO))
                .channel(NioServerSocketChannel.class)
                .childHandler(new HttpServerInitializer());


        try {
            ChannelFuture f = serverBootstrap.bind(new InetSocketAddress(8080)).sync();
            logger.info(" server start up on port : {}", 8080);
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            throw new RuntimeException("server start fail -> " + e + "");
        }
    }

}
