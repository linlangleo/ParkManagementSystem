package cn.parkmanasys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;

import cn.parkmanasys.entity.SysInterfaceManager;
import cn.parkmanasys.service.sysinterfacemanager.SysInterfaceManagerService;
import cn.parkmanasys.service.sysinterfacemanager.SysInterfaceManagerServiceImpl;
import cn.parkmanasys.util.annotation.LogTypeEnum;
import cn.parkmanasys.util.annotation.SystemLog;

@RestController
@RequestMapping("/interfacemanager")
public class SysInterfaceManagerController {
	@Resource
	private SysInterfaceManagerService parkingInfoService;

	/**
	 * 查询接口信息
	 * @return
	 */
	@SystemLog(logType = LogTypeEnum.INTERFACE_LOG, operationDesc = "登陆系统")
	@RequestMapping(value = "/getinterfaceinfo", method = RequestMethod.POST)
	public List<SysInterfaceManager> getInterfaceInfo(){
		int i = 1/0;
		List<SysInterfaceManager> simList = parkingInfoService.getInterfaceInfo(null, null);
		
		return simList;
	}
	
	/**
	 * 查询接口信息
	 * @return
	 */
	@RequestMapping(value = "/addinterfaceinfo", method = RequestMethod.POST)
	public String addInterfaceInfo(){
		SysInterfaceManager sim = new SysInterfaceManager();
		sim.setCreateBy("linlangleo");
		
		parkingInfoService.addInterfaceInfo(sim);
		
		return "操作成功";
	}
	
}
