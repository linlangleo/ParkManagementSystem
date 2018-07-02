package cn.parkmanasys.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import cn.parkmanasys.entity.ParkingPayment;
import cn.parkmanasys.entity.ParkingTimesStatistics;
import cn.parkmanasys.entity.StopStatus;
import cn.parkmanasys.service.parkingpayment.ParkingPaymentService;
import cn.parkmanasys.service.stopstatus.StopStatusService;
import cn.parkmanasys.util.Constants;
import cn.parkmanasys.util.PageSupport;

/**
 * 交易信息控制器 
 */
@Controller
@RequestMapping("parkingpayment")
public class ParkingPayMentController {
	@Resource
	private ParkingPaymentService parkingPaymentService;
	
	@Resource
	private StopStatusService stopStatusService;
	
	@RequestMapping("/getpaymentbypage")
	@ResponseBody 
	public Object getAllParkingPayMentByPage(PageSupport ppPage, ParkingPayment pp,HttpSession session){
		ParkingAccount logUser = (ParkingAccount) session.getAttribute("logUser");//获取当前登陆对象
		//获得所有的停车位数据，并分页。
		//分页
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

		int totalCount = parkingPaymentService.getParkingPaymentCount(pp,logUser.getParkingInfo().getId());
		ppPage.setPageSize(pageSize);
		ppPage.setTotalCount(totalCount);
		int totalPageCount = ppPage.getTotalPageCount();
		//判断
		if(currentPageNo > totalPageCount){
			currentPageNo = totalPageCount;
		}
		ppPage.setCurrentPageNo(currentPageNo);
		
		List<ParkingPayment> ppList = null;
		try {			
			ppList = parkingPaymentService.getParkingPaymentByPage(pp, ppPage,logUser.getParkingInfo().getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//返回给layui数据
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("data", ppList);
		result.put("code", 0);
		result.put("msg", "");
		result.put("count", ppPage.getTotalCount());//返回总数量即可
		result.put("currentPageNo", ppPage.getCurrentPageNo());//返回当前页数
		result.put("pageSize", ppPage.getPageSize());//返回页面大小
		
		return JSONArray.toJSON(result);
	}
	
	@RequestMapping("/layeropen")
	public String layerOptions(String eventType, Integer id, HttpSession session){
		ParkingAccount logUser = (ParkingAccount) session.getAttribute("logUser");//获取当前登陆对象

		
		List<StopStatus> stopList = stopStatusService.getFindAllStopStatusInfo();
		
		session.setAttribute("stopList", stopList);
		if(eventType.equals("add")){//新增
			session.removeAttribute("parkingPayment");
		}else if(eventType.equals("edit") || eventType.equals("detail")){//修改或查看：先根据id查出数据
			//根据id查询实体的信息
			ParkingPayment parkingPayment = null;
			if(id != null){
				parkingPayment = parkingPaymentService.getParkingPayMentById(id);
			}
			session.setAttribute("parkingPayment", parkingPayment);
		}

		return "ParkAccount/layerTemp/ParkingPaymentTemp";
	}

	@RequestMapping("/updateparkingpayment")
	@ResponseBody
	public Object updateParkingPayMent(HttpSession session, ParkingPayment ppUpadte){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", "false");
		
		ParkingPayment parkingPayMentUpdate = null;
		try {
			parkingPayMentUpdate = parkingPaymentService.updateParkingPayment(ppUpadte);
			result.put("parkingSpaceUpdate", parkingPayMentUpdate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(parkingPayMentUpdate != null){
			result.put("result", "true");
		}
		
		return result;
	}

	
	@RequestMapping("/addparkingpayment")
	@ResponseBody
	public Object addParkingPayMent(HttpSession session, ParkingPayment ppAdd){
		Map<String, String> result = new HashMap<String, String>();
		result.put("result", "false");	
		if(parkingPaymentService.addParkingPayMent(ppAdd)){
			result.put("result", "true");
		}
		return result;
	}
	
	
	@RequestMapping("/delparkingpayment")
	@ResponseBody
	public Object delParkingPayMent(HttpSession session, Integer id){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("result", "false");
		
		try {
			if(parkingPaymentService.delParkingPayMent(id)){
				result.put("result", "true");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * 获取昨日交易流水
	 * @author Dickson
	 */
	@RequestMapping("/findByPayYesterday")
	public String findByPayYesterday(HttpSession session)
	{
		ParkingAccount park=(ParkingAccount) session.getAttribute("logUser");
		Integer count=parkingPaymentService.findByPayYesterday(park.getParkingInfo().getId());
		List<String> year=parkingPaymentService.findByDateYear(park.getParkingInfo().getId());
		session.setAttribute("count", count);
		session.setAttribute("year", year);
		return "/ParkAccount/Transactionreport";
	}
	
	
	/**
	 * 交易额统计查询 
	 */
	@RequestMapping("/Transactionstatistics")
	@ResponseBody
	public Object Transactionstatistics(Integer eventType,Integer year,HttpSession session)
	{
		 ParkingAccount paaccount =(ParkingAccount) session.getAttribute("logUser");
		 List<String> Transactionsum=new ArrayList<String>();   //交易额
		 List<Object[]> park=null;
		 List<String> multiMonDate = new ArrayList<String>();  //阶段日期
		 Map<String, Object> result = new HashMap<String, Object>();
		 SimpleDateFormat myFmt3=new SimpleDateFormat("yyyy-MM-dd"); 
		 SimpleDateFormat myFmt1=new SimpleDateFormat("dd");  //转换天数
		 SimpleDateFormat myFmt2=new SimpleDateFormat("MM");  //转换月份
		 SimpleDateFormat myFmt4=new SimpleDateFormat("yyyy");  //转换月份
		 //当前	
		 Calendar b=Calendar.getInstance();//取得系统时间
		 Calendar c = Calendar.getInstance();  
		 Calendar a=null;
		 boolean bool;
		if(eventType==1)
		 {
				Date dae=b.getTime();
				b.add(b.DATE, -7);
				Date das=b.getTime();
				park=parkingPaymentService.findByDayPayInfo(myFmt3.format(das), myFmt3.format(dae), paaccount.getParkingInfo().getId());
				for(int day = -7 ; day<0; day++){//循环得到前7天
					 a=Calendar.getInstance();
					 a.add(a.DATE, day);
					 Date date=a.getTime();
					 String MonthDay=myFmt4.format(date)+"-"+myFmt2.format(date)+"-"+myFmt1.format(date);
					 for(Object[] item :park)
					 {
						 String MonthDay2=(String) item[1];
						 if(MonthDay.equals(MonthDay2))
						 {
							 Transactionsum.add(String.valueOf(item[0]));
						 }
					 }
					 multiMonDate.add(MonthDay);
				 };
			//当月	 
		 }else if(eventType==2)
		 {
		        b.add(Calendar.MONTH, 0);
		        b.set(Calendar.DAY_OF_MONTH,1);
		        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		        park=parkingPaymentService.findByMothPayInfo(myFmt3.format(b.getTime()),myFmt3.format(c.getTime()),paaccount.getParkingInfo().getId());
		        //天
		        Integer da=Integer.parseInt(myFmt1.format(b.getTime()));
		        Integer da2=Integer.parseInt(myFmt1.format(c.getTime()));
		        //月
		        Integer num=park.size();
		        Integer mo=Integer.parseInt(myFmt2.format(c.getTime()));
		        for(int day = da ; day<=da2; day++){//循环得到当月多少天
					 String MonthDay=myFmt4.format(c.getTime())+"-"+myFmt2.format(c.getTime())+"-"+day;
					 bool=true;
					 for(Object[] item :park)
					 {
						 Date db=null;
						try {
							db = myFmt3.parse((String) item[1]);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						 String MonthDay2=String.valueOf(myFmt4.format(db)+"-"+myFmt2.format(db)+"-"+Integer.parseInt(myFmt1.format(db)));
						 if(MonthDay.equals(MonthDay2))
						 {
							 Transactionsum.add(String.valueOf(item[0]));
							 num--;
							 bool=false;
						 }
					 }
					 if(num==0)
					 {
						 bool=false;
					 }
					  if(bool)
						 {
						  Transactionsum.add(String.valueOf(0));
						 }
					 
					  multiMonDate.add(MonthDay);
				 };
		 }else if(eventType==3)
		 {
			 
			 b.add(Calendar.MONTH, -1);
		     b.set(Calendar.DAY_OF_MONTH,1);
		     c.set(Calendar.DAY_OF_MONTH,0);
		     park=parkingPaymentService.findByMothPayInfo(myFmt3.format(b.getTime()),myFmt3.format(c.getTime()),paaccount.getParkingInfo().getId());
		     //天
		        Integer da=Integer.parseInt(myFmt1.format(b.getTime()));
		        Integer da2=Integer.parseInt(myFmt1.format(c.getTime()));
		        //月
		        Integer mo=Integer.parseInt(myFmt2.format(c.getTime()));
		        Integer num=park.size();
		        for(int day = da ; day<=da2; day++){//循环得到当月多少天
		        	 String MonthDay=myFmt4.format(c.getTime())+"-"+myFmt2.format(c.getTime())+"-"+day;
					 bool=true;
					 for(Object[] item :park)
					 {
						 Date db=null;
						try {
							db = myFmt3.parse((String) item[1]);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						 String MonthDay2=String.valueOf(myFmt4.format(db)+"-"+myFmt2.format(db)+"-"+Integer.parseInt(myFmt1.format(db)));
						 if(MonthDay.equals(MonthDay2))
						 {
							 Transactionsum.add(String.valueOf(item[0]));
							 num--;
							 bool=false;
						 }
					 }
					 if(num==0)
					 {
						 bool=false;
					 }
					  if(bool)
						 {
						  Transactionsum.add(String.valueOf(0));
						 }
					 multiMonDate.add(MonthDay);
				 };
		 }else if(eventType==4)
		 {
			 b.clear();
			 c.clear();
			 b.set(Calendar.YEAR,year);
			 c.set(Calendar.YEAR,year);
			 c.roll(Calendar.DAY_OF_YEAR, -1);
			 List<Object[]> lis=parkingPaymentService.findByYearPayInfo(String.valueOf(year),paaccount.getParkingInfo().getId());
			 
			 Integer month_staint=Integer.parseInt(myFmt2.format(b.getTime()));
			 Integer month_endint=Integer.parseInt(myFmt2.format(c.getTime()));
			 Integer num=lis.size();
			  for(int day = month_staint ; day<=month_endint; day++){//循环月份
				  bool=true;
					  for(Object[] item :lis)
					  {
							 Integer moth=Integer.parseInt((String) item[1]);
							 if(day==moth)
							 {
								 Transactionsum.add(String.valueOf(item[0]));
								 num--;
								 bool=false;
							 }
					  }
				 if(num==0)
				 {
					 bool=false;
				 }
				  if(bool)
					 {
					  Transactionsum.add(String.valueOf(0));
					 }
				  multiMonDate.add(String.valueOf(day));
			  }
		 }
		 result.put("categories", multiMonDate);
		 result.put("Transactionsum", Transactionsum);
		return JSONArray.toJSON(result);
	}
	
	

}
