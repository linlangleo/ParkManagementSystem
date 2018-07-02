package cn.parkmanasys.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.parkmanasys.entity.CustomParkingSpaceInfo;
import cn.parkmanasys.entity.ParkingAccount;
import cn.parkmanasys.entity.ParkingInfo;
import cn.parkmanasys.service.customparkingspaceinfo.CustomParkingSpaceInfoService;

@Controller
@RequestMapping("/customparkingspaceinfo")
public class CustomParkingSpaceInfoController {
	@Resource
	private CustomParkingSpaceInfoService customParkingSpaceInfoService;
	
	//保存自定义停车位信息
	@RequestMapping("/savecustomparkingspace")
	@ResponseBody
	public Object saveCustomParkingSpace(String parkingSpaceInfo, HttpSession session){
		ParkingAccount logUser = (ParkingAccount) session.getAttribute("logUser");//获取当前登陆对象
		
		CustomParkingSpaceInfo customParkingSpaceInfo = new CustomParkingSpaceInfo();
		
		//绑定信息
		ParkingInfo parking = new ParkingInfo();
		parking.setId(logUser.getParkingInfo().getId());
		customParkingSpaceInfo.setParkingInfo(parking);
		customParkingSpaceInfo.setParkingSpaceInfo(parkingSpaceInfo);
		customParkingSpaceInfo.setCreationDate(new Date());

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", "false");
		if(customParkingSpaceInfoService.addOrUpdateCustomParkingSpace(customParkingSpaceInfo)){
			result.put("result", "true");
		}
		
		return result;
	}
	

	//根据id,获取自定义停车位信息
	@RequestMapping("/getinfobyid")
	@ResponseBody
	public Object getInfoById(HttpSession session){
		ParkingAccount logUser = (ParkingAccount) session.getAttribute("logUser");//获取当前登陆对象

		CustomParkingSpaceInfo customParkingSpaceInfo = null;
		try {
			customParkingSpaceInfo = customParkingSpaceInfoService.getInfoById(logUser.getParkingInfo().getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return customParkingSpaceInfo;
	}
}
