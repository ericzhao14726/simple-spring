package com.zx.simpleSpring.httpServer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.*;

public class HttpServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast("encode",new HttpResponseEncoder());
        // http请求编码
        pipeline.addLast("decode",new HttpRequestDecoder());
        // 聚合http请求
        pipeline.addLast("aggregator",
                new HttpObjectAggregator(10*1024*1024));
        // 启用http压缩
        pipeline.addLast("compressor",new HttpContentCompressor());
        pipeline.addLast("handler",new HttpRequestHandler());
    }
}
