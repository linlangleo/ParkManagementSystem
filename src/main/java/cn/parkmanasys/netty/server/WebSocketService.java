package cn.parkmanasys.netty.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

public interface WebSocketService {

    void handleFrame(ChannelHandlerContext ctx,WebSocketFrame frame);
    
}
