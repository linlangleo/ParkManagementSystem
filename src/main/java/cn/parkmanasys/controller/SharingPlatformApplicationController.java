package cn.parkmanasys.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;

import cn.parkmanasys.entity.ParkingAccount;
import cn.parkmanasys.entity.ParkingSpace;
import cn.parkmanasys.entity.SharingPlatform;
import cn.parkmanasys.entity.SharingPlatformApplication;
import cn.parkmanasys.entity.SharingStatus;
import cn.parkmanasys.service.sharingplatformapplication.SharingPlatformApplicationService;
import cn.parkmanasys.util.Constants;
import cn.parkmanasys.util.PageSupport;

@Controller
@RequestMapping("/sharingplatformapplication")
public class SharingPlatformApplicationController {
	@Resource
	private SharingPlatformApplicationService sharingplatformapplicationservice;
	
	@RequestMapping("/addsharingplatformapplication")
	@ResponseBody
	public Object addsharingplatformapplication(HttpSession session, SharingPlatformApplication spAdd){
		Map<String, String> result = new HashMap<String, String>();
		result.put("result", "false");
		ParkingAccount logUser = (ParkingAccount) session.getAttribute("logUser");//获取当前登陆对象
		spAdd.setParkingInfo(logUser.getParkingInfo());
		spAdd.setCreationDate(new Date());
		if(sharingplatformapplicationservice.addSharingPlatformApplication(spAdd)){
			result.put("result", "true");
		}
		
		return "ParkAccount/Sharinfplatshengqi";
	}
	
	@RequestMapping("/getsharingplatformapplication")
	@ResponseBody 
	public Object getAllSharingPlatformApplicationByPage(PageSupport spPage, SharingPlatformApplication sp, HttpSession session){//获得所有的共享数据，并分页。
		ParkingAccount logUser = (ParkingAccount) session.getAttribute("logUser");//获取当前登陆对象
		//分页
		int currentPageNo = 1;
		int pageSize = Constants.pageSize;
		
		if(spPage != null){
			if(spPage.getCurrentPageNo() > 0){
				currentPageNo = spPage.getCurrentPageNo();
			}
			if(spPage.getPageSize() > 0){
				pageSize = spPage.getPageSize();
			}
		}

		int totalCount = sharingplatformapplicationservice.getSharingPlatformApplicationCount(sp,logUser.getParkingInfo().getId());
		spPage.setPageSize(pageSize);
		spPage.setTotalCount(totalCount);
		int totalPageCount = spPage.getTotalPageCount();
		//判断
		if(currentPageNo > totalPageCount){
			currentPageNo = totalPageCount;
		}
		spPage.setCurrentPageNo(currentPageNo);
		
		List<SharingPlatformApplication> spList = null;
		try {
			spList = sharingplatformapplicationservice.getSharingPlatformApplication(sp, spPage,logUser.getParkingInfo().getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//返回给layui数据
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", spList);
		result.put("code", 0);
		result.put("msg", "");
		result.put("count", spPage.getTotalCount());//返回总数量即可
		result.put("currentPageNo", spPage.getCurrentPageNo());//返回当前页数
		result.put("pageSize", spPage.getPageSize());//返回页面大小
		
		return JSONArray.toJSON(result);
	}

	
	@RequestMapping("/layeroptions")
	public String layerOptions(String eventType, Integer id, HttpSession session){
		ParkingAccount logUser = (ParkingAccount) session.getAttribute("logUser");//获取当前登陆对象
			if(eventType.equals("edit")||eventType.equals("detail")){//修改或查看：先根据id查出数据
			//根据id查询实体的信息
			SharingPlatformApplication sharingplatformapplication = null;
			if(id != null){
				sharingplatformapplication = sharingplatformapplicationservice.getSharingPlatformApplicationById(id);
			}
			session.setAttribute("sharingplatformapplication", sharingplatformapplication);
		}

		return "AdministratorAccount/layerTemp/Sharinfplatformshengqi";
	}

	
	@RequestMapping("/delsSharingplatformapplication")
	@ResponseBody
	public Object delSharingPlatformApplication(HttpSession session, Integer id){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", "false");
		
		try {
			if(sharingplatformapplicationservice.delSharingPlatformApplication(id)){
				result.put("result", "true");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
