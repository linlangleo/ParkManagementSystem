package cn.parkmanasys.netty.server;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.parkmanasys.entity.ParkingAccount;
import cn.parkmanasys.util.StaticClass;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object>{

    
    private WebSocketService webSocketServiceImpl;
    
    private HttpService httpServiceImpl;
    
    
    public WebSocketServerHandler(WebSocketService webSocketServiceImpl,
            HttpService httpServiceImpl) {
        super();
        this.webSocketServiceImpl = webSocketServiceImpl;
        this.httpServiceImpl = httpServiceImpl;
    }


    @Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
		
    	System.out.println("当前账号id:"+StaticClass.currentAccount.getId());
    	System.out.println("当前账号名:"+StaticClass.currentAccount.getAccountName());
    	
    	if(StaticClass.channels.get(StaticClass.currentAccount.getId()) == null){
        	StaticClass.channels.put(StaticClass.currentAccount.getId(), ctx);
    	}
    	System.out.println("当前线程数量:"+StaticClass.channels.size());

    	System.out.println("channelActive");
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		super.channelInactive(ctx);
    	StaticClass.channels.remove(StaticClass.currentAccount.getId());
    	
    	System.out.println("channelInactive");
	}


	@Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg)
            throws Exception {
    	System.out.println("msg---------recived");
    	/*
    	 * 在这个地方添加客服所需的响应逻辑。当前的demo中，将接收到的消息，又原封不动的发送给了客户端
    	 */
//    	NettyTest.ctx1.channel().writeAndFlush(new TextWebSocketFrame("welcome i am leo!".toUpperCase(Locale.US)));
    	
    	//消息原返
        if(msg instanceof FullHttpRequest){

//        	System.out.println(msg.toString());
            httpServiceImpl.handleHttpRequset(ctx, (FullHttpRequest)msg);
        }else if(msg instanceof WebSocketFrame){
            
            webSocketServiceImpl.handleFrame(ctx, (WebSocketFrame)msg);
        }
    }
	

	@Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // TODO Auto-generated method stub
    	System.out.println("---------channelReadComplete");
        ctx.flush();
    }
}
