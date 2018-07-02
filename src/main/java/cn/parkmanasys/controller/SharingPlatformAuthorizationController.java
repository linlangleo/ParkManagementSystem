package cn.parkmanasys.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.parkmanasys.entity.ParkingAccount;
import cn.parkmanasys.entity.SharingPlatformAuthorization;
import cn.parkmanasys.service.parkingaccount.ParkingAccountService;
import cn.parkmanasys.service.sharingplatformauthorization.SharingPlatformAuthorizationService;

@Controller
@RequestMapping("/sharingplatformauthorization")
public class SharingPlatformAuthorizationController {
	@Resource
	private SharingPlatformAuthorizationService sharingPlatformAuthorizationService;
	
	
	@RequestMapping("/addsharingplatformauthorization")
	@ResponseBody
	public Object addsharingplatformapplication(HttpSession session,SharingPlatformAuthorization sa,Integer id){
		Map<String, String> result = new HashMap<String, String>();
		result.put("result", "false");
		ParkingAccount logUser = (ParkingAccount) session.getAttribute("logUser");//获取当前登陆对象
		if(!sharingPlatformAuthorizationService.getparkingAccountUserId(logUser.getId())){
			SharingPlatformAuthorization saa=new SharingPlatformAuthorization();
			int x=(int)((Math.random()*9+1)*100000);
			String b=Integer.toString(x);
			saa.setOpenDate(new Date());
			saa.setAuthorizationNumber(b);
			saa.setIfAvailable("false");
			saa.setIfClosed("false");
			saa.setParkingAccount(logUser);
			sharingPlatformAuthorizationService .addSharingPlatformAuthorization(saa);
		}else{
			sharingPlatformAuthorizationService.updateSharingPlatformAuthorization(sa);
		}
		return "AdministratorAccount/Sharinfplatshengqi";
	}
}
