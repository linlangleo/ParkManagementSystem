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
import cn.parkmanasys.entity.ParkingDictionary;
import cn.parkmanasys.entity.ParkingSpace;
import cn.parkmanasys.entity.SharingPlatform;
import cn.parkmanasys.entity.SharingStatus;
import cn.parkmanasys.service.parkingspace.ParkingSpaceService;
import cn.parkmanasys.service.sharingflatform.SharingPlatformService;
import cn.parkmanasys.service.sharingstatus.SharingStatusService;
import cn.parkmanasys.util.Constants;
import cn.parkmanasys.util.PageSupport;

@Controller
@RequestMapping("/sharingplatform")
public class SharingPlatformController {
	@Resource
	private SharingPlatformService sharingplatformservice;
	@Resource
	private SharingStatusService sharingstatusservice;
	@Resource
	private ParkingSpaceService parkingSpaceService;
	
	@RequestMapping("/getallspbypage")
	@ResponseBody 
	public Object getAllParkingSpaceByPage(PageSupport spPage, SharingPlatform sp, HttpSession session){//获得所有的共享数据，并分页。
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

		int totalCount = sharingplatformservice.getSharingPlatformCount(sp,logUser.getParkingInfo().getId());
		spPage.setPageSize(pageSize);
		spPage.setTotalCount(totalCount);
		int totalPageCount = spPage.getTotalPageCount();
		//判断
		if(currentPageNo > totalPageCount){
			currentPageNo = totalPageCount;
		}
		spPage.setCurrentPageNo(currentPageNo);
		
		List<SharingPlatform> spList = null;
		try {
			spList = sharingplatformservice.getSharingPlatform(sp, spPage,logUser.getParkingInfo().getId());
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
		//查询需要的显示信息,下拉框
		List<SharingStatus> ssList = sharingstatusservice.getAllSharingStatus();
		session.setAttribute("ssList", ssList);
		List<SharingStatus> pdList = sharingstatusservice.getAllSharingStatus();
		List<ParkingSpace> psList = parkingSpaceService.getParkingSpaceByParkingId(logUser.getParkingInfo().getId());
		session.setAttribute("pdList", pdList);
		session.setAttribute("psList", psList);
		
		if(eventType.equals("add")){//新增
			session.removeAttribute("sharingPlatform");
		}else if(eventType.equals("edit") || eventType.equals("detail")){//修改或查看：先根据id查出数据
			//根据id查询实体的信息
			SharingPlatform sharingPlatform = null;
			if(id != null){
				sharingPlatform = sharingplatformservice.getSharingPlatformById(id);
			}
			session.setAttribute("sharingPlatform", sharingPlatform);
		}

		return "ParkAccount/layerTemp/SharingPlatformTemp";
	}
	
	@RequestMapping("/addsharingplatform")
	@ResponseBody
	public Object addSharingPlatform(HttpSession session, SharingPlatform psAdd){
		Map<String, String> result = new HashMap<String, String>();
		result.put("result", "false");
		ParkingAccount logUser = (ParkingAccount) session.getAttribute("logUser");//获取当前登陆对象
		psAdd.setParkingInfo(logUser.getParkingInfo());
		psAdd.setCreationDate(new Date());
		if(sharingplatformservice.addSharingPlatform(psAdd)){
			result.put("result", "true");
		}
		
		return result;
	}
	
	@RequestMapping("/updatesharingplatform")
	@ResponseBody
	public Object updateSharingPlatform(HttpSession session, SharingPlatform psUpadte){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", "false");
		
		SharingPlatform sharingPlatformUpdate = null;
		try {
			sharingPlatformUpdate = sharingplatformservice.updateSharingPlatform(psUpadte);
		} catch (Exception e) { 
			e.printStackTrace();
		}
		
		if(sharingPlatformUpdate != null){
			result.put("result", "true");
		}
		
		return result;
		
	}

	@RequestMapping("/delsharingplatform")
	@ResponseBody
	public Object delsharingPlatform(HttpSession session, Integer id){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", "false");
		
		try {
			if(sharingplatformservice.delSharingPlatform(id)){
				result.put("result", "true");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	//根据id查询共享平台信息;ajax方法
	@RequestMapping("/getsharingplatformbyid")
	@ResponseBody
	public Object getSharingPlatformById(HttpSession session, Integer id){
		SharingPlatform sharingPlatform = null;
		
		try {
			sharingPlatform = sharingplatformservice.getSharingPlatformById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sharingPlatform;
	}
}
