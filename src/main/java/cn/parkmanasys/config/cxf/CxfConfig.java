package cn.parkmanasys.config.cxf;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.parkmanasys.netty.server.WebSocketServerImpl;
import cn.parkmanasys.service.app.AppService;
import cn.parkmanasys.service.app.AppServiceImpl;

@Configuration
public class CxfConfig {
	@Autowired
	private Bus bus;

	@Autowired
	AppService appService;

	/** JAX-WS **/
	@Bean
	public Endpoint endpoint() {
		//同时发布netty服务器
//	      WebSocketServerImpl socket = new WebSocketServerImpl("localhost", 9999);
//	      socket.start();
		
		EndpointImpl endpoint = new EndpointImpl(bus, appService);
		endpoint.publish("/appService");
		return endpoint;
	}
}
