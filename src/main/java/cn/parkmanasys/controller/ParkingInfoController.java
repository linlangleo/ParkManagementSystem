package cn.parkmanasys.controller;

import java.util.ArrayList;
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

import cn.parkmanasys.entity.ExamineStatusInfo;
import cn.parkmanasys.entity.ParkingAccount;
import cn.parkmanasys.entity.ParkingInfo;
import cn.parkmanasys.entity.ParkingTemporaryInfo;
import cn.parkmanasys.service.parkingTemporaryInfo.ParkingTemporaryInfoService;
import cn.parkmanasys.service.parkingaccount.ParkingAccountService;
import cn.parkmanasys.service.parkinginfo.ParkingInfoService;
import cn.parkmanasys.util.Constants;
import cn.parkmanasys.util.PageSupport;


/**
 * 停车场信息控制器 
 */
@Controller
@RequestMapping("/ParkingInfo")
public class ParkingInfoController {
	
	//停车场信息业务层
	@Resource
	private ParkingInfoService parkinginfoService;
	
	//停车场信息临时业务层
	@Resource
	private ParkingTemporaryInfoService parkingTempoaryInfoService;
	
	@Resource
	private ParkingAccountService parkingaccount;
	
	/**
	 * 获取停车场的信息
	 */
	@RequestMapping("/getparkinginfo")
	@ResponseBody
	public Object getparkinginfo(HttpSession session)
	{
		ParkingAccount park=(ParkingAccount) session.getAttribute("logUser");
		park=parkingaccount.getParkingAccountbyId(park.getId());
		Map<String,Object> result= new HashMap<String,Object>();
		List<Object> list=new ArrayList<Object>();
		list.add(park);
		result.put("data",list);
		result.put("code", 0);
		result.put("msg", "");
		result.put("count", 1);//返回总数量即可
		return JSONArray.toJSON(result);
	}
	
	
	/**
	 * 查看停车场信息 
	 */
	@RequestMapping("/Viewparkinginfo")
	public String findparkinginfo(String eventType,HttpSession session)
	{	
		ParkingAccount park=(ParkingAccount) session.getAttribute("logUser");
		park=parkingaccount.getParkingAccountbyId(park.getId());
		session.setAttribute("parkinfo", park.getParkingInfo());
		if(eventType.equals("edit"))
		{
			return "ParkAccount/layerTemp/ParkingInfodetailTemp";
		}
		//查看
		return "ParkAccount/layerTemp/ParkingInfodetailTemp";
	}
	
	/**
	 * 新增修改申请 
	 */ 
	@RequestMapping("/updateparkinginfo")
	@ResponseBody
	public Object updateparkinginfo(ParkingInfo parkinfo)
	{
		Map<String, String> result = new HashMap<String, String>();
		ParkingTemporaryInfo parks=null;
		result.put("result", "false");
		
		ParkingTemporaryInfo park=new ParkingTemporaryInfo();
		
		
		park.setDistrictOrCounty(parkinfo.getDistrictOrCounty());
		park.setStreet(parkinfo.getStreet());
		//停车场地址
		park.setParkingAddress(parkinfo.getParkingAddress());
		//停车场名称
		park.setParkingName(parkinfo.getParkingName());
		//停车场车位数量
		park.setParkingSpaceCount(parkinfo.getParkingSpaceCount());
		//停车场计费
		park.setCharge(parkinfo.getCharge());
		//审核状态  现在为待审批
		ExamineStatusInfo  ex=new ExamineStatusInfo();
		ex.setId(1);
		park.setExamineStatusInfo(ex);
		//创建时间
		park.setCreationDate(new Date());
		//停车场信息
		park.setParkingInfo(parkinfo);
		int day=30;
		try{
			parks=parkingTempoaryInfoService.getParkingByParkingInfo(parkinfo.getId());
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if(parks!=null)
		{
			Date Date=new Date();
			int days = (int)((Date.getTime() - parks.getCreationDate().getTime())/86400000);
			if(days>=day)
			{
				if(parkingTempoaryInfoService.addAmendtheapplication(park))
				{
					result.put("result", "true");
				}
			}else
			{
				result.put("result", "day");
			}
		}else
		{
			if(parkingTempoaryInfoService.addAmendtheapplication(park))
			{
				result.put("result", "true");
			}
		}
		return result;
	}
	
	
	/**
	 * 停车场查看自己提交的修改申请记录 
	 */
	@RequestMapping(value="/getParkingUpdateInfoList")
	@ResponseBody
	public Object  getParkingUpdateInfoList(PageSupport psPage,HttpSession session)
	{
				ParkingAccount park=(ParkingAccount) session.getAttribute("logUser");
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
				int totalCount =parkingTempoaryInfoService.findByParkingInfoUpCount(park.getParkingInfo().getId());
				psPage.setPageSize(pageSize);
				psPage.setTotalCount(totalCount);
				int totalPageCount = psPage.getTotalPageCount();
				//判断
				if(currentPageNo > totalPageCount){
					currentPageNo = totalPageCount;
				}
				psPage.setCurrentPageNo(currentPageNo);
				List<ParkingTemporaryInfo> ParkingInfoUpList=parkingTempoaryInfoService.getParkingInfoUp(park.getParkingInfo().getId(), psPage);
				Map<String,Object> date=new HashMap<String, Object>();
				date.put("data", ParkingInfoUpList);
				date.put("code", 0);
				date.put("msg", "");
				date.put("count",psPage.getTotalCount());
				date.put("currentPageNo", psPage.getCurrentPageNo());//返回当前页数
				date.put("pageSize", psPage.getPageSize());//返回页面大小
				return JSONArray.toJSON(date);
	}
	
	/**
	 * 获取申请修改的停车场信息 
	 */
	@RequestMapping(value="/getUpdateParkinginfo")
	@ResponseBody
	public Object getUpdateParkinginfo(PageSupport psPage)
	{
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
		
		int totalCount =parkingTempoaryInfoService. getUpdateCount();
		psPage.setPageSize(pageSize);
		psPage.setTotalCount(totalCount);
		int totalPageCount = psPage.getTotalPageCount();
		//判断
		if(currentPageNo > totalPageCount){
			currentPageNo = totalPageCount;
		}
		psPage.setCurrentPageNo(currentPageNo);
		
		List<ParkingTemporaryInfo> UpparkinginfoList=parkingTempoaryInfoService.getUpdateParkingInfo(psPage);
		Map<String,Object> date=new HashMap<String, Object>();
		date.put("data", UpparkinginfoList);
		date.put("code", 0);
		date.put("msg", "");
		date.put("count",psPage.getTotalCount());
		date.put("currentPageNo", psPage.getCurrentPageNo());//返回当前页数
		date.put("pageSize", psPage.getPageSize());//返回页面大小
		return JSONArray.toJSON(date);
		
	}
	
	/**
	 * 获取修改审批单个数据
	 */
	@RequestMapping(value="/getParkingInfoUpdateByid")
	public String getParkingInfoUpdateByid(String eventType,Integer id,HttpSession session)
	{
		
		ParkingTemporaryInfo park=parkingTempoaryInfoService.getParkingInfoUpdateByid(id);
		session.setAttribute("TemporaryInfo", park);
	/*	if(eventType.equals("detail"))
		{
			return "";
		}else
		{
			
		}*/
		return "AdministratorAccount/layerTemp/ParkingTemporaryInfoTemp";
	}
	
	//审批通过或失败
	@RequestMapping(value="UpdateParkingTemporaryInfo")
	@ResponseBody
	public Object UpdateParkingTemporaryInfo(ParkingTemporaryInfo parkingTemporary,Integer Examination)
	{
		Map<String, String> result = new HashMap<String, String>();
		ParkingTemporaryInfo park=null;
		ParkingInfo parks=null;
		parkingTemporary.getExamineStatusInfo().setId(Examination);
		result.put("result", "false");
		if(Examination==3)
		{
			park=parkingTempoaryInfoService.updateParkingTemporaryInfo(parkingTemporary);
			if(park!=null)
			{
				result.put("result", "true");
			}
		}else
		{
			park=parkingTempoaryInfoService.updateParkingTemporaryInfo(parkingTemporary);
			parks=parkinginfoService.updateParkingInfo(parkingTemporary);
			if(park!=null&&parks!=null)
			{
				result.put("result", "true");
			}
		}
		return result;
	}
	
	/**
	 * 撤销 
	 */
	@RequestMapping(value="RevokeParkingInfoUp")
	@ResponseBody
	public Object RevokeParkingInfoUp(Integer id)
	{
		Map<String, String> result = new HashMap<String, String>();
		ParkingTemporaryInfo parks=null;
		result.put("result", "false");
		parks=parkingTempoaryInfoService.RevokeParkingInfoUp(id);
		if(parks!=null)
		{
			result.put("result", "true");
		}
		return result;
	}
	
	/**
	 * 更新页面 
	 */
	@RequestMapping(value="UpdateJspPark")
	@ResponseBody
	public Object UpdateJspPark(Integer id)
	{
		Map<String, String> result = new HashMap<String, String>();
		ParkingTemporaryInfo parks=null;
		parks=parkingTempoaryInfoService.UpdateJspPark(id);
		if(parks!=null)
		{
			result.put("ex", parks.getExamineStatusInfo().getExamineName());
		}
		return result;
	}
}
