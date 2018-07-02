package cn.parkmanasys.controller;

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
import cn.parkmanasys.entity.ParkingInfo;
import cn.parkmanasys.entity.ParkingSpace;
import cn.parkmanasys.entity.ParkingSpaceStatus;
import cn.parkmanasys.service.parkinginfo.ParkingInfoService;
import cn.parkmanasys.util.Constants;
import cn.parkmanasys.util.PageSupport;

@Controller
@RequestMapping("/AdminParkInfo")
public class AdminParkingInfoController {
	//相对应的Server层
	@Resource
	private ParkingInfoService parkingInfoService;
	
	@RequestMapping("/getparkinfobypage")
	@ResponseBody
	/**
	 * 查询所有停车场信息并分页
	 * @param ppPage 分页辅助工具
	 * @param pi 查询实体类
	 * @param session
	 * @return
	 */
	public Object getAllParkInfoByPage(PageSupport ppPage, ParkingInfo pi,HttpSession session){
		int currentPageNo = 1;
		int pageSize = Constants.pageSize;
		
		if(ppPage != null){
			if(ppPage.getCurrentPageNo() > 0){
				currentPageNo = ppPage.getCurrentPageNo();
			}
			if(ppPage.getPageSize() > 0){
				pageSize = ppPage.getPageSize();
			}
		}

		int totalCount = parkingInfoService.getParkingInfoCount(pi);
		ppPage.setPageSize(pageSize);
		ppPage.setTotalCount(totalCount);
		int totalPageCount = ppPage.getTotalPageCount();
		//判断
		if(currentPageNo > totalPageCount){
			currentPageNo = totalPageCount;
		}
		ppPage.setCurrentPageNo(currentPageNo);
		
		List<ParkingInfo> piList = null;
		try {			
			piList = parkingInfoService.findParkingInfoById(pi, ppPage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//返回给layui数据
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", piList);
		result.put("code", 0);
		result.put("msg", "");
		result.put("count", ppPage.getTotalCount());//返回总数量即可
		result.put("currentPageNo", ppPage.getCurrentPageNo());//返回当前页数
		result.put("pageSize", ppPage.getPageSize());//返回页面大小
		
		return JSONArray.toJSON(result);
	}
	
	
	@RequestMapping("/updateparkingInfo")
	@ResponseBody
	/**
	 * 修改停车场信息
	 * @param session
	 * @param piUpdate 修改实体类
	 * @return
	 */
	public Object updateParkinfo(HttpSession session, ParkingInfo piUpdate){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", "false");
		
		ParkingInfo parkingInfoUpdate = null;
		try {
			parkingInfoUpdate = parkingInfoService.updateParkingInfo(piUpdate);
			result.put("parkingSpaceUpdate", parkingInfoUpdate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(parkingInfoUpdate != null){
			result.put("result", "true");
		}
		return result;
	}

	
	@RequestMapping("/addparkinginfo")
	@ResponseBody
	/**
	 * 新增停车场信息
	 * @param session
	 * @param piAdd 新增实体类
	 * @return
	 */
	public Object addParkingInfo(HttpSession session, ParkingInfo piAdd){
		Map<String, String> result = new HashMap<String, String>();
		result.put("result", "false");
		if(parkingInfoService.addParkingInfo(piAdd)){
			result.put("result", "true");
		}
		return result;
	}
	
	@RequestMapping("/delparkinginfo")
	@ResponseBody
	/**
	 * 删除停车场信息
	 * @param session
	 * @param id 删除行的ID
	 * @return
	 */
	public Object delParkinginfo(HttpSession session, Integer id){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", "false");
		try {
			if(parkingInfoService.delParkingInfo(id)){
				result.put("result", "true");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("/layeroptions")
	public String layerOptions(String eventType, Integer id, HttpSession session){
		
		if(eventType.equals("add")){//新增
			session.removeAttribute("parkingInfo");
		}else if(eventType.equals("edit") || eventType.equals("detail")){//修改或查看：先根据id查出数据
			//根据id查询实体的信息
			ParkingInfo parkingInfo = null;
			if(id != null){
				parkingInfo = parkingInfoService.getParkingInfoById(id);
			}
			session.setAttribute("parkingInfo", parkingInfo);
		}
		return "AdministratorAccount/layerTemp/ParkingInfoTemp";
	}
	
	@RequestMapping("/getparkinginfobyid")
	@ResponseBody
	public Object getParkingInfoById(HttpSession session, Integer id){
		ParkingInfo parkingInfo = null;
		
		try {
			parkingInfo = parkingInfoService.getParkingInfoById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return parkingInfo;
	}
}
