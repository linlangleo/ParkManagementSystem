package cn.parkmanasys.util;

import java.util.Map;
import java.util.HashMap;

import cn.parkmanasys.entity.ParkingAccount;
import io.netty.channel.ChannelHandlerContext;

public class StaticClass {
	//存储不同账号的线程
	public static Map<Integer, ChannelHandlerContext> channels = new HashMap<Integer, ChannelHandlerContext>(0);
	
	//当前的登陆账号
	public static ParkingAccount currentAccount;

}
