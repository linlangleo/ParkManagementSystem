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

import cn.parkmanasys.entity.SysOperationLog;
import cn.parkmanasys.entity.ParkingAccount;
import cn.parkmanasys.service.OperationRecord.SysOperationLogService;
import cn.parkmanasys.util.Constants;
import cn.parkmanasys.util.PageSupport;

@Controller
@RequestMapping("/operationrecord")
public class SysOperationLogController {
	@Resource
	private SysOperationLogService sysOperationLogService;
	
	@RequestMapping("/getoperationInfoPage")
	@ResponseBody 
	private Object getOperationRecord(SysOperationLog record,PageSupport psPage,HttpSession session){
		ParkingAccount logUser = (ParkingAccount) session.getAttribute("logUser");//获取当前登陆对象
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

		int totalCount = sysOperationLogService.getOperationRecordCount(record,logUser.getParkingInfo().getId());
		psPage.setPageSize(pageSize);
		psPage.setTotalCount(totalCount);
		int totalPageCount = psPage.getTotalPageCount();
		//判断
		if(currentPageNo > totalPageCount){
			currentPageNo = totalPageCount;
		}
		psPage.setCurrentPageNo(currentPageNo);
		
		List<SysOperationLog> oList = null;
		try {			
			oList = sysOperationLogService.findByParkingAccountId(record, psPage,logUser.getParkingInfo().getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//返回给layui数据
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", oList);
		result.put("code", 0);
		result.put("msg", "");
		result.put("count", psPage.getTotalCount());//返回总数量即可
		result.put("currentPageNo", psPage.getCurrentPageNo());//返回当前页数
		result.put("pageSize", psPage.getPageSize());//返回页面大小
		
		return JSONArray.toJSONString(result);
	}
	
	@RequestMapping("/addoperationInfo")
	@ResponseBody
	public Object addOperationRecord(HttpSession session, SysOperationLog orAdd){
		//ParkingAccount logUser = (ParkingAccount) session.getAttribute("logUser");//获取当前登陆对象
		
		Map<String, String> result = new HashMap<String, String>();
		result.put("result", "false");
		
		orAdd.setCreationTime(new Date());
		if(sysOperationLogService.addOperationRecord(orAdd)){
			result.put("result", "true");
		}
		
		return result;
	}
}
