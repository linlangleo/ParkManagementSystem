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

import cn.parkmanasys.entity.FeedbackAndDispute;
import cn.parkmanasys.service.feedbackanddispute.FeedbackAndDisputeService;
import cn.parkmanasys.util.Constants;
import cn.parkmanasys.util.PageSupport;

@Controller
@RequestMapping("/feedbackanddispute")
public class FeedbackAndDisputeController {
	@Resource
	private FeedbackAndDisputeService feedbackanddisputeservice;
	
	@RequestMapping("/getallfdbypage")
	@ResponseBody 
	public Object getAllFeedbackAndDisputeByPage(PageSupport fdPage, FeedbackAndDispute fd){//获得所有的共享数据，并分页。
		
		//分页
		int currentPageNo = 1;
		int pageSize = Constants.pageSize;
		
		if(fdPage != null){
			if(fdPage.getCurrentPageNo() > 0){
				currentPageNo = fdPage.getCurrentPageNo();
			}
			if(fdPage.getPageSize() > 0){
				pageSize = fdPage.getPageSize();
			}
		}

		int totalCount = feedbackanddisputeservice.getFeedbackAndDisputeCount(fd);
		fdPage.setPageSize(pageSize);
		fdPage.setTotalCount(totalCount);
		int totalPageCount = fdPage.getTotalPageCount();
		//判断
		if(currentPageNo > totalPageCount){
			currentPageNo = totalPageCount;
		}
		fdPage.setCurrentPageNo(currentPageNo);
		
		List<FeedbackAndDispute> fdList = null;
		try {
			fdList = feedbackanddisputeservice.getFeedbackAndDispute(fd, fdPage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//返回给layui数据
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", fdList);
		result.put("code", 0);
		result.put("msg", "");
		result.put("count", fdPage.getTotalCount());//返回总数量即可
		result.put("currentPageNo", fdPage.getCurrentPageNo());//返回当前页数
		result.put("pageSize", fdPage.getPageSize());//返回页面大小
		
		return JSONArray.toJSON(result);
	}
	
	/**
	 * 拉黑查询方法
	 * @param eventType
	 * @param id 查询的ID
	 * @param session
	 * @return
	 */
	@RequestMapping("/blackList")
	public String blackList(Integer id, HttpSession session){
			if(id != null){
				FeedbackAndDispute back = null;
				back = feedbackanddisputeservice.getFeedbackAndDisputeById(id);
				session.setAttribute("back", back);
				return "ParkAccount/layerTemp/BacklistTemp";
			}
			return "ParkAccount/Sharinfplatformfkui";
	}
	
	@RequestMapping("/layeroptions")
	public String layerOptions(String eventType, Integer id, HttpSession session){
		if(eventType.equals("add")){//新增
			session.removeAttribute("feedbackAndDispute");
		}else if(eventType.equals("edit") || eventType.equals("detail")){//修改或查看：先根据id查出数据
			//根据id查询实体的信息
			FeedbackAndDispute feedbackAndDispute = null;
			if(id != null){
				feedbackAndDispute = feedbackanddisputeservice.getFeedbackAndDisputeById(id);
			}
			session.setAttribute("feedbackAndDispute", feedbackAndDispute);
		}

		return "ParkAccount/layerTemp/FeedbackAndDisputeTemp";
	}
	
	@RequestMapping("/feedlayeroptions")
	public String feedlayerOptions(String eventType, Integer id, HttpSession session){
		if(eventType.equals("add")){//新增
			session.removeAttribute("feedbackAndDispute");
		}else if(eventType.equals("edit") || eventType.equals("detail")){//修改或查看：先根据id查出数据
			//根据id查询实体的信息
			FeedbackAndDispute feedbackAndDispute = null;
			if(id != null){
				feedbackAndDispute = feedbackanddisputeservice.getFeedbackAndDisputeById(id);
			}
			session.setAttribute("feedbackAndDispute", feedbackAndDispute);
		}

		return "AdministratorAccount/layerTemp/FeedbackAndDisputeTemp";
	}
	
	@RequestMapping("/addfeedbackanddispute")
	@ResponseBody
	public Object addFeedbackAndDispute(FeedbackAndDispute psAdd,HttpSession session){
		Map<String, String> result = new HashMap<String, String>();
		result.put("result", "false");
		
		if(feedbackanddisputeservice.addFeedbackAndDispute(psAdd)){
			result.put("result", "true");
		}
		
		return result;
	}
	
	@RequestMapping("/updatefeedbackanddispute")
	@ResponseBody
	public Object updateFeedbackAndDispute(FeedbackAndDispute psUpadte,HttpSession session){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", "false");
		
		FeedbackAndDispute feedbackAndDisputeUpdate = null;
		try {
			feedbackAndDisputeUpdate = feedbackanddisputeservice.updateFeedbackAndDispute(psUpadte);
			result.put("feedbackAndDisputeUpdate", feedbackAndDisputeUpdate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(feedbackAndDisputeUpdate != null){
			result.put("result", "true");
		}
		
		return result;
	}

	@RequestMapping("/delfeedbackanddispute")
	@ResponseBody
	public Object delFeedbackAndDispute(HttpSession session, Integer id){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", "false");
		
		try {
			if(feedbackanddisputeservice.delFeedbackAndDispute(id)){
				result.put("result", "true");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	//根据id查询停车位;ajax方法
	@RequestMapping("/getfeedbackanddisputebyid")
	@ResponseBody
	public Object getFeedbackAndDisputeById(HttpSession session, Integer id){
		FeedbackAndDispute feedbackanddispute = null;
		try {
			feedbackanddispute = feedbackanddisputeservice.getFeedbackAndDisputeById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return feedbackanddispute;
	}
	
	@RequestMapping("/backlist")
	public String backlist(String eventType, Integer id, HttpSession session){
		if(eventType.equals("add")){//新增
			session.removeAttribute("feedbackAndDispute");
		}else if(eventType.equals("edit") || eventType.equals("detail")){//修改或查看：先根据id查出数据
			//根据id查询实体的信息
			FeedbackAndDispute feedbackAndDispute = null;
			if(id != null){
				feedbackAndDispute = feedbackanddisputeservice.getFeedbackAndDisputeById(id);
			}
			session.setAttribute("feedbackAndDispute", feedbackAndDispute);
		}

		return "ParkAccount/layerTemp/backlist";
	}
	
	@RequestMapping("/addblackList")
	public Object addblackList(FeedbackAndDispute psAdd,HttpSession session){
		Map<String, String> result = new HashMap<String, String>();
		result.put("result", "false");
		
		if(feedbackanddisputeservice.addFeedbackAndDispute(psAdd)){
			result.put("result", "true");
		}
		
		return result;
	}
	
}

