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
import cn.parkmanasys.entity.ParkingDictionary;
import cn.parkmanasys.entity.ParkingSpace;
import cn.parkmanasys.service.parkingdictionary.ParkingDictionaryService;
import cn.parkmanasys.service.parkingspace.ParkingSpaceService;
import cn.parkmanasys.util.Constants;
import cn.parkmanasys.util.PageSupport;

@Controller
@RequestMapping("/parkingdictionary")
public class ParkingDictionaryController {
	@Resource
	private ParkingDictionaryService parkingDictionaryService;
	
	@RequestMapping("/getallpsbypage")
	@ResponseBody 
	public Object getAllParkingDictionaryByPage(PageSupport psPage, ParkingDictionary pd, HttpSession session){//获得所有的停车位数据，并分页。
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

		int totalCount = parkingDictionaryService.getParkingDictionaryCount(pd,logUser.getParkingInfo().getId());
		psPage.setPageSize(pageSize);
		psPage.setTotalCount(totalCount);
		int totalPageCount = psPage.getTotalPageCount();
		//判断
		if(currentPageNo > totalPageCount){
			currentPageNo = totalPageCount;
		}
		psPage.setCurrentPageNo(currentPageNo);
		
		List<ParkingDictionary> psList = null;
		try {
			psList = parkingDictionaryService.getParkingDictionaryByPage(pd, psPage,logUser.getParkingInfo().getId());
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
		if(eventType.equals("add")){//新增
			session.removeAttribute("parkingDictionary");
		}else if(eventType.equals("edit") || eventType.equals("detail")){//修改或查看：先根据id查出数据
			//根据id查询实体的信息
			ParkingDictionary parkingDictionary = null;
			if(id != null){
				parkingDictionary = parkingDictionaryService.getParkingDictionaryById(id);
			}
			session.setAttribute("parkingDictionary", parkingDictionary);
		}

		return "ParkAccount/layerTemp/ParkingDictionaryTemp";
	}
	
	@RequestMapping("/addparkingspace")
	@ResponseBody
	public Object addParkingDictionary(HttpSession session, ParkingDictionary psAdd){
		Map<String, String> result = new HashMap<String, String>();
		result.put("result", "false");
		ParkingAccount logUser = (ParkingAccount) session.getAttribute("logUser");//获取当前登陆对象
		psAdd.setParkingInfo(logUser.getParkingInfo());
		psAdd.setCreationDate(new Date());
		if(parkingDictionaryService.addParkingDictionary(psAdd)){
			result.put("result", "true");
		}
		return result;
	}
	
	@RequestMapping("/updateparkingspace")
	@ResponseBody
	public Object updateParkingDictionary(HttpSession session, ParkingDictionary psUpadte){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", "false");
		
		ParkingDictionary parkingDictionaryUpdate = null;
		try {
			parkingDictionaryUpdate = parkingDictionaryService.updateParkingDictionary(psUpadte);
			result.put("parkingDictionaryUpdate", parkingDictionaryUpdate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(parkingDictionaryUpdate != null){
			result.put("result", "true");
		}
		
		return result;
	}

	@RequestMapping("/delparkingspace")
	@ResponseBody
	public Object delParkingDictionary(HttpSession session, Integer id){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", "false");
		
		try {
			if(parkingDictionaryService.delParkingDictionary(id)){
				result.put("result", "true");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return result;
	}
}
