package cn.parkmanasys.netty.server;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.AttributeKey;

public class WebSocketServerImpl implements WebSocketService, HttpService{

    
    private static final String HN_HTTP_CODEC = "HN_HTTP_CODEC";
    private static final String NH_HTTP_AGGREGATOR ="NH_HTTP_AGGREGATOR";
    private static final String NH_HTTP_CHUNK = "HN_HTTP_CHUNK";
    private static final String NH_SERVER = "NH_LOGIC";
    
    private static final AttributeKey<WebSocketServerHandshaker> ATTR_HANDSHAKER = AttributeKey.newInstance("ATTR_KEY_CHANNELID");
    
    private static final int MAX_CONTENT_LENGTH = 65536;
    
    private static final String WEBSOCKET_UPGRADE = "websocket";
    
    private static final String WEBSOCKET_CONNECTION = "Upgrade";
    
    private static final String WEBSOCKET_URI_ROOT_PATTERN = "ws://%s:%d";
    
    //地址
    private String host;
    
    //端口号
    private int port;
    
    //存放websocket连接
    private Map<ChannelId, Channel> channelMap = new ConcurrentHashMap<ChannelId, Channel>();
    
    
    private final String WEBSOCKET_URI_ROOT;
    
    public WebSocketServerImpl(String host, int port) {
        super();
        this.host = host;
        this.port = port;
        WEBSOCKET_URI_ROOT = String.format(WEBSOCKET_URI_ROOT_PATTERN, host, port);
    }
    
    //启动
    public void start(){
        
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap sb = new ServerBootstrap();
        sb.group(bossGroup, workerGroup);
        sb.channel(NioServerSocketChannel.class);
        sb.childHandler(new ChannelInitializer<Channel>() {

            @Override
            protected void initChannel(Channel ch) throws Exception {
                ChannelPipeline pl = ch.pipeline();
                //保存引用
                channelMap.put(ch.id(), ch);
                ch.closeFuture().addListener(new ChannelFutureListener() {
                    
                    public void operationComplete(ChannelFuture future) throws Exception {
                        //关闭后抛弃
                        channelMap.remove(future.channel().id());
                    }
                });
                
            pl.addLast(HN_HTTP_CODEC,new HttpServerCodec());
            pl.addLast(new StringDecoder());
            pl.addLast(NH_HTTP_AGGREGATOR,new HttpObjectAggregator(MAX_CONTENT_LENGTH));
            pl.addLast(NH_HTTP_CHUNK,new ChunkedWriteHandler());
            pl.addLast(NH_SERVER,new WebSocketServerHandler(WebSocketServerImpl.this,WebSocketServerImpl.this));
                
                
            }
            
            
            
        });
        
        
        try {
            ChannelFuture future = sb.bind(host,port).addListener(new ChannelFutureListener() {
                
                public void operationComplete(ChannelFuture future) throws Exception {
                    if(future.isSuccess()){
                        
                        System.out.println("websocket started");
                    }
                }
            }).sync();
            
            future.channel().closeFuture().addListener(new ChannelFutureListener() {
                
                public void operationComplete(ChannelFuture future) throws Exception {
                    System.out.println("channel is closed");
                }
            }).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally{
            
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
        
        System.out.println("websocket shutdown");
    }
    
    
    

    public void handleHttpRequset(ChannelHandlerContext ctx,
            FullHttpRequest request) {
        
        if(isWebSocketUpgrade(request)){
            
            String subProtocols = request.headers().get(HttpHeaderNames.SEC_WEBSOCKET_PROTOCOL);
            
            WebSocketServerHandshakerFactory factory = new WebSocketServerHandshakerFactory(WEBSOCKET_URI_ROOT, subProtocols, false);
        
            WebSocketServerHandshaker handshaker = factory.newHandshaker(request);
            
            if(handshaker == null){
                
                WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
                
            }else{
                //响应请求
                handshaker.handshake(ctx.channel(), request);
                //将handshaker绑定给channel
                ctx.channel().attr(ATTR_HANDSHAKER).set(handshaker);
            }
            return;
        }
        
    }

    public void handleFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        if(frame instanceof TextWebSocketFrame){
            
            String text = ((TextWebSocketFrame) frame).text();
            TextWebSocketFrame rsp = new TextWebSocketFrame(text);
            
            for(Channel ch:channelMap.values()){
                
                if(ctx.channel().equals(ch)){
                    
                    continue;
                }
                ch.writeAndFlush(rsp);
                
            }
            
            return;
        }
        
        //ping 回复 pong
        if(frame instanceof PingWebSocketFrame){
            
            ctx.channel().writeAndFlush(new PongWebSocketFrame(frame.content().retain()));
            return;
            
        }
        
        if(frame instanceof PongWebSocketFrame){
            
            return;
        }
        
        if(frame instanceof CloseWebSocketFrame){
            
            WebSocketServerHandshaker handshaker = ctx.channel().attr(ATTR_HANDSHAKER).get();
            
            if(handshaker == null){
                
                return;
            }
            handshaker.close(ctx.channel(), (CloseWebSocketFrame)frame.retain());
            return;
        }
        
    }

    //1、判断是否为get 2、判断Upgrade头 包含websocket字符串 3、Connection头 包换upgrade字符串
    private boolean isWebSocketUpgrade(FullHttpRequest request){
        
        HttpHeaders headers = request.headers();
        
        return request.method().equals(HttpMethod.GET) 
                && headers.get(HttpHeaderNames.UPGRADE).contains(WEBSOCKET_UPGRADE)
                && headers.get(HttpHeaderNames.CONNECTION).contains(WEBSOCKET_CONNECTION);
        
    }
}
