package cn.parkmanasys.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import cn.parkmanasys.netty.server.WebSocketServerImpl;

@Controller
@RequestMapping("/netty")
public class NettyServerController extends WebMvcConfigurerAdapter {
	//打开地址：http://localhost:8089/netty/openserver
	@RequestMapping("/openserver")
	public void openServer(){
//        WebSocketServerImpl socket = new WebSocketServerImpl("10.187.0.245", 9999);
	      WebSocketServerImpl socket = new WebSocketServerImpl("localhost", 9999);
	      socket.start();
	}

}
	