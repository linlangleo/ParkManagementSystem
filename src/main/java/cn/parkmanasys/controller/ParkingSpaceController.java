package cn.parkmanasys.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;

import cn.parkmanasys.entity.ParkingAccount;
import cn.parkmanasys.entity.ParkingDictionary;
import cn.parkmanasys.entity.ParkingSpace;
import cn.parkmanasys.entity.ParkingSpaceStatus;
import cn.parkmanasys.service.parkingdictionary.ParkingDictionaryService;
import cn.parkmanasys.service.parkingspace.ParkingSpaceService;
import cn.parkmanasys.service.parkingspace.ParkingSpaceServiceImpl;
import cn.parkmanasys.service.parkingspacestatus.ParkingSpaceStatusService;
import cn.parkmanasys.util.Constants;
import cn.parkmanasys.util.PageSupport;

@Controller
@RequestMapping("/parkingspace")
public class ParkingSpaceController {
	@Resource
	private ParkingSpaceService parkingSpaceService;
	@Resource
	private ParkingDictionaryService parkingDictionaryService;
	@Resource
	private ParkingSpaceStatusService parkingSpaceStatusService;
	
	@RequestMapping("/getallpsbypage")
	@ResponseBody 
	public Object getAllParkingSpaceByPage(PageSupport psPage, ParkingSpace ps, HttpSession session){//获得所有的停车位数据，并分页。
		ParkingAccount logUser = (ParkingAccount) session.getAttribute("logUser");//获取当前登陆对象
		
		//分页
		int currentPageNo = 1;
		int pageSize = Constants.pageSize;
		
		if(psPage != null){
			if(psPage.getCurrentPageNo() > 0){
				currentPageNo = psPage.getCurrentPageNo();
			}
			if(psPage.getPageSize() > 0){
				pageSize = psPage.getPageSize();
			}
		}

		int totalCount = parkingSpaceService.getParkingSpaceCount(ps, logUser.getParkingInfo().getId());
		psPage.setPageSize(pageSize);
		psPage.setTotalCount(totalCount);
		int totalPageCount = psPage.getTotalPageCount();
		//判断
		if(currentPageNo > totalPageCount){
			currentPageNo = totalPageCount;
		}
		psPage.setCurrentPageNo(currentPageNo);
		
		List<ParkingSpace> psList = null;
		try {
			psList = parkingSpaceService.getParkingSpaceByPage(ps, psPage, logUser.getParkingInfo().getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//返回给layui数据
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", psList);
		result.put("code", 0);
		result.put("msg", "");
		result.put("count", psPage.getTotalCount());//返回总数量即可
		result.put("currentPageNo", psPage.getCurrentPageNo());//返回当前页数
		result.put("pageSize", psPage.getPageSize());//返回页面大小
		
		return JSONArray.toJSON(result);
	}
	
	@RequestMapping("/layeroptions")
	public String layerOptions(String eventType, Integer id, HttpSession session){
		ParkingAccount logUser = (ParkingAccount) session.getAttribute("logUser");//获取当前登陆对象
		
		//查询需要的显示信息,下拉框
		List<ParkingDictionary> pdList = parkingDictionaryService.getParkingDictionaryByParkingId(logUser.getParkingInfo().getId());
		List<ParkingSpaceStatus> pssList = parkingSpaceStatusService.getAllParkingSpaceStatus();
		session.setAttribute("pdList", pdList);
		session.setAttribute("pssList", pssList);
		
		if(eventType.equals("add")){//新增
			session.removeAttribute("parkingSpace");
		}else if(eventType.equals("edit") || eventType.equals("detail")){//修改或查看：先根据id查出数据
			//根据id查询实体的信息
			ParkingSpace parkingSpace = null;
			if(id != null){
				parkingSpace = parkingSpaceService.getParkingSpaceById(id);
			}
			session.setAttribute("parkingSpace", parkingSpace);
		}

		return "ParkAccount/layerTemp/ParkingSpaceTemp";
	}
	
	@RequestMapping("/addparkingspace")
	@ResponseBody
	public Object addParkingSpace(HttpSession session, ParkingSpace psAdd){
		ParkingAccount logUser = (ParkingAccount) session.getAttribute("logUser");//获取当前登陆对象
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", "false");
		
		psAdd.setParkingInfo(logUser.getParkingInfo());//把新增的停车位设置为本停车场的
		psAdd.setCreationDate(new Date());
		if(parkingSpaceService.addParkingSpace(psAdd)){
			result.put("newParkingSpace", psAdd);
			result.put("result", "true");
		}
		
		return result;
	}
	
	@RequestMapping("/updateparkingspace")
	@ResponseBody
	public Object updateParkingSpace(HttpSession session, ParkingSpace psUpadte){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", "false");
		
		ParkingSpace parkingSpaceUpdate = null;
		try {
			parkingSpaceUpdate = parkingSpaceService.updateParkingSpace(psUpadte);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(parkingSpaceUpdate != null){
			result.put("result", "true");
		}
		
		return result;
	}

	@RequestMapping("/delparkingspace")
	@ResponseBody
	public Object delParkingSpace(HttpSession session, Integer id){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", "false");
		
		try {
			if(parkingSpaceService.delParkingSpace(id)){
				result.put("result", "true");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	//根据id查询停车位;ajax方法
	@RequestMapping("/getparkingspacebyid")
	@ResponseBody
	public Object getParkingSpaceById(HttpSession session, Integer id){
		ParkingSpace parkingSpace = null;
		
		try {
			parkingSpace = parkingSpaceService.getParkingSpaceById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return parkingSpace;
	}

	//根据id查询停车位;ajax方法
	@RequestMapping("/gotocustomparkingspace")
	public String gotoCustomParkingSpace(HttpSession session, Model model){
		ParkingAccount logUser = (ParkingAccount) session.getAttribute("logUser");//获取当前登陆对象
		
		try {
			//查询需要的显示信息,下拉框
			List<ParkingSpace> psList = parkingSpaceService.getAllParkingSpace(logUser.getParkingInfo().getId());
			List<ParkingDictionary> pdList = parkingDictionaryService.getParkingDictionaryByParkingId(logUser.getParkingInfo().getId());

			model.addAttribute("psList", psList);
			model.addAttribute("pdList", pdList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "ParkAccount/CustomParkingSpace";
	}
	
	
}
