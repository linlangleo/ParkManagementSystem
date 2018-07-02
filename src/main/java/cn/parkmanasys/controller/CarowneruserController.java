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

import cn.parkmanasys.entity.BoundOfPlateNumber;
import cn.parkmanasys.entity.CarOwnerUser;
import cn.parkmanasys.entity.FeedbackAndDispute;
import cn.parkmanasys.entity.ParkingAccount;
import cn.parkmanasys.entity.ParkingDictionary;
import cn.parkmanasys.entity.ParkingSpace;
import cn.parkmanasys.entity.PlateNumber;
import cn.parkmanasys.service.boundofplatenumber.BoundOfPlateNumberService;
import cn.parkmanasys.service.carowneruser.CarOwnerUserService;
import cn.parkmanasys.service.platenumber.PlateNumberService;
import cn.parkmanasys.util.Constants;
import cn.parkmanasys.util.PageSupport;
import com.alibaba.fastjson.JSONArray;

@Controller
@RequestMapping("/carowneruser")
public class CarowneruserController {
	
	@Resource
	private BoundOfPlateNumberService boundOfPlateNumberService;
	@Resource
	private CarOwnerUserService carOwnerUserService;
	@Resource
	private PlateNumberService plateNumberService;
	
	
	@RequestMapping("/getallpsbypage")
	@ResponseBody 
	public Object getAllParkingSpaceByPage(PageSupport psPage, BoundOfPlateNumber pa){//获得所有的停车位数据，并分页。
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

		int totalCount = boundOfPlateNumberService.getParkingCarOwnerUserCount(pa);
		psPage.setPageSize(pageSize);
		psPage.setTotalCount(totalCount);
		int totalPageCount = psPage.getTotalPageCount();
		//判断
		if(currentPageNo > totalPageCount){
			currentPageNo = totalPageCount;
		}
		psPage.setCurrentPageNo(currentPageNo);
		
		List<BoundOfPlateNumber> psList = null;
		try {
			psList = boundOfPlateNumberService.getParkingCarOwnerUserByPage(pa, psPage);
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
			session.removeAttribute("boundOfPlateNumber");
		}else if(eventType.equals("edit") || eventType.equals("detail")){//修改或查看：先根据id查出数据
			//根据id查询实体的信息
			BoundOfPlateNumber boundOfPlateNumber = null;
			if(id != null){
				boundOfPlateNumber = boundOfPlateNumberService.getBoundOfPlateNumberById(id);
			}
			session.setAttribute("boundOfPlateNumber", boundOfPlateNumber);
		}

		return "AdministratorAccount/layerTemp/CarOwnerUserTemp";
	}
	
	private String  userName;	
	private String  password;	
	private String  realName;	
	private String  plateNumber;	

	public String getPlateNumber() {
		return plateNumber;
	}


	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getRealName() {
		return realName;
	}


	public void setRealName(String realName) {
		this.realName = realName;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	@RequestMapping("/addCarOwnerUser")
	@ResponseBody
	public Object addUser(HttpSession session, String userName,String password,String realName,String plateNumber){
		Map<String, String> result = new HashMap<String, String>();
		result.put("result", "false");
		
		CarOwnerUser cu=new CarOwnerUser();		
		cu.setUserName(userName);
		cu.setPassword(password);
		cu.setRealName(realName);
		carOwnerUserService.addParkingCarOwnerUser(cu);
		PlateNumber pn=new PlateNumber();
		pn.setPlateNumber(plateNumber);
		plateNumberService.addPlateNumber(pn);
	
		
		BoundOfPlateNumber bn=new BoundOfPlateNumber();
		bn.setCarOwnerUser(cu);
		bn.setPlateNumber(pn);		
		bn.setCreationDate(new Date());
		
		if(boundOfPlateNumberService.addPlateNumber(bn)){
			result.put("result", "true");
		}
		return result;
	}
	
	@RequestMapping("/updateCarOwnerUser")
	@ResponseBody
	public Object updateCarOwnerUser(HttpSession session,String userName,String password,String realName, Integer carOwnerUserid,String plateNumber,Integer plateNumberid){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", "false");
		PlateNumber pn=new PlateNumber();
		pn.setPlateNumber(plateNumber);
		pn.setId(plateNumberid);
		CarOwnerUser cu=new CarOwnerUser();	
		cu.setUserName(userName);
		cu.setPassword(password);
		cu.setRealName(realName);
		cu.setId(carOwnerUserid);
		try {			
			pn=plateNumberService.updatePlateNumber(pn);
			result.put("pn", pn);
			cu=carOwnerUserService.updateParkingCarOwnerUser(cu);
			result.put("cu", cu);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(pn != null){
			result.put("result", "true");
		}		
		return result;
	}
	
	//根据id查询车主;ajax方法
	@RequestMapping("/getCarOwnerUserbyid")
	@ResponseBody
	public Object getCarOwnerUserbyid(HttpSession session, Integer id){
		BoundOfPlateNumber boundOfPlateNumber = null;
		//id=750;
		try {
			boundOfPlateNumber = boundOfPlateNumberService.getBoundOfPlateNumberById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boundOfPlateNumber;
	}

	
	@RequestMapping("/delCarOwnerUser")
	@ResponseBody
	public Object delCarOwnerUser(HttpSession session, Integer id){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", "false");
		
		try {
			if(boundOfPlateNumberService.delCarOwnerUser(id)){
				result.put("result", "true");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return result;
	}
	

}
