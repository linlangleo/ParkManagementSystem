package cn.parkmanasys.controller;

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
import cn.parkmanasys.entity.ParkingInfo;
import cn.parkmanasys.entity.ParkingTimesStatistics;
import cn.parkmanasys.service.parkingTimesStatistics.ParkingTimesStatisticsService;
import cn.parkmanasys.service.parkinginfo.ParkingInfoService;
import cn.parkmanasys.service.parkingpayment.ParkingPaymentService;

@Controller
@RequestMapping("/ParkingTimes")
public class ParkingTimesStatisticsController {
		
	@Resource
	private ParkingTimesStatisticsService parkingTimeService;
	
	//交易记录业务层
	@Resource
	private ParkingPaymentService parkingPaymentService;
	
	//注入停车场信息业务
	@Resource
	private ParkingInfoService parkingInfoService;
	
	ParkingAccount paaccount=null;
	/**
	 * 昨日车流 
	 */
	@RequestMapping(value="findByYesterday")
	public String findBydateYesterday(HttpSession session)
	{
		paaccount =(ParkingAccount) session.getAttribute("logUser");
		ParkingTimesStatistics pars=parkingTimeService.findBydateYesterday(paaccount.getParkingInfo().getId());
		parkingTimeService.findByDateYear(paaccount.getParkingInfo().getId());
		session.setAttribute("ParkingTimes", pars);
		session.setAttribute("year",parkingTimeService.findByDateYear(paaccount.getParkingInfo().getId()));
		return "/ParkAccount/Trafficflowreport";
	}
	
	
	/**
	 * 车流统计查询 
	 */
	@RequestMapping(value="findByTrafficflowreport")
	@ResponseBody
	public Object findByTrafficflowreport(Integer eventType,Integer year,HttpSession session)
	{
		 paaccount =(ParkingAccount) session.getAttribute("logUser");
		 List<ParkingTimesStatistics> park=null;
		 List<String> multiMonDate = new ArrayList<String>();  //阶段日期
		 List<String> go=new ArrayList<String>();   //进
		 List<String> come=new ArrayList<String>();   //出
		 SimpleDateFormat myFmt3=new SimpleDateFormat("yyyy-MM-dd"); 
		 Calendar a=null;
		 boolean bool;
		 Map<String, Object> result = new HashMap<String, Object>();
		
		 SimpleDateFormat myFmt1=new SimpleDateFormat("dd");  //转换天数
		 SimpleDateFormat myFmt2=new SimpleDateFormat("MM");  //转换月份
		 //当前	
		 Calendar b=Calendar.getInstance();//取得系统时间
		 Calendar c = Calendar.getInstance();  
		 if(eventType==1)
		 {
				Date dae=b.getTime();
				b.add(b.DATE, -7);
				Date das=b.getTime();
				park=parkingTimeService.findByBrokenlineDate(paaccount.getParkingInfo().getId(),myFmt3.format(das),myFmt3.format(dae));
				Integer num=park.size();
				for(int day = -7 ; day<0; day++){//循环得到前7天
					 bool=true;
					 a=Calendar.getInstance();
					 a.add(a.DATE, day);
					 Date date=a.getTime();
					 String MonthDay=myFmt2.format(date)+"-"+myFmt1.format(date);
					 for(ParkingTimesStatistics item :park)
					 {
						 String MonthDay2=myFmt2.format(item.getCreationDate())+"-"+myFmt1.format(item.getCreationDate());
						 if(MonthDay.equals(MonthDay2))
						 {
							 go.add(item.getEnterTimes());
							 come.add(item.getLeaveTimes());
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
						 go.add(String.valueOf(0));
						 come.add(String.valueOf(0));
					 }
					 multiMonDate.add(MonthDay);
				 };
			//当月	 
		 }else if(eventType==2)
		 {
		        b.add(Calendar.MONTH, 0);
		        b.set(Calendar.DAY_OF_MONTH,1);
		        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		        park=parkingTimeService.findByBrokenlineDate(paaccount.getParkingInfo().getId(),myFmt3.format(b.getTime()),myFmt3.format(c.getTime()));
		        //天
		        Integer da=Integer.parseInt(myFmt1.format(b.getTime()));
		        Integer da2=Integer.parseInt(myFmt1.format(c.getTime()));
		        //月
		        Integer num=park.size();
		        Integer mo=Integer.parseInt(myFmt2.format(c.getTime()));
		        for(int day = da ; day<=da2; day++){//循环得到当月多少天
					 String MonthDay=myFmt2.format(c.getTime())+"-"+day;
					 bool=true;
					 for(ParkingTimesStatistics item :park)
					 {
						 String MonthDay2=myFmt2.format(item.getCreationDate())+"-"+Integer.parseInt(myFmt1.format(item.getCreationDate()));
						 if(MonthDay.equals(MonthDay2))
						 {
							 go.add(item.getEnterTimes());
							 come.add(item.getLeaveTimes());
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
						 go.add(String.valueOf(0));
						 come.add(String.valueOf(0));
					 }
					 multiMonDate.add(MonthDay);
				 };
		 }else if(eventType==3)
		 {
			 
			 b.add(Calendar.MONTH, -1);
		     b.set(Calendar.DAY_OF_MONTH,1);
		     c.set(Calendar.DAY_OF_MONTH,0);
		     park=parkingTimeService.findByBrokenlineDate(paaccount.getParkingInfo().getId(),myFmt3.format(b.getTime()),myFmt3.format(c.getTime()));
		     //天
		        Integer da=Integer.parseInt(myFmt1.format(b.getTime()));
		        Integer da2=Integer.parseInt(myFmt1.format(c.getTime()));
		        //月
		        Integer mo=Integer.parseInt(myFmt2.format(c.getTime()));
		        Integer num=park.size();
		        for(int day = da ; day<=da2; day++){//循环得到当月多少天
		        	 bool=true;
					 String MonthDay=myFmt2.format(c.getTime())+"-"+day;
					 for(ParkingTimesStatistics item :park)
					 {
						 String MonthDay2=myFmt2.format(item.getCreationDate())+"-"+Integer.parseInt(myFmt1.format(item.getCreationDate()));
						 if(MonthDay.equals(MonthDay2))
						 {
							 go.add(item.getEnterTimes());
							 come.add(item.getLeaveTimes());
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
						 go.add(String.valueOf(0));
						 come.add(String.valueOf(0));
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
			 List<Object[]> lis=parkingTimeService.findByYearDate(paaccount.getParkingInfo().getId(), year);
			 
			 Integer month_staint=Integer.parseInt(myFmt2.format(b.getTime()));
			 Integer month_endint=Integer.parseInt(myFmt2.format(c.getTime()));
			 Integer num=lis.size();
			  for(int day = month_staint ; day<=month_endint; day++){//循环月份
				  bool=true;
					  for(Object[] item :lis)
					  {
							 Integer moth=Integer.parseInt((String) item[2]);
							 if(day==moth)
							 {
								 go.add(String.valueOf(item[0]));
								 come.add(String.valueOf(item[1]));
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
						 go.add(String.valueOf(0));
						 come.add(String.valueOf(0));
					 }
				  multiMonDate.add(String.valueOf(day)+"月");
			  }
		 }
		 result.put("categories", multiMonDate);
		 result.put("go", go);
		 result.put("come", come);
		return JSONArray.toJSON(result);
	}
	
	//查询tab 第一个选项卡的值
	@RequestMapping(value="/findBytabTop")
	@ResponseBody
	public Object findBytabTop(Integer tab,HttpSession session)
	{
		Map<String, Object> result = new HashMap<String, Object>();
		Calendar c = Calendar.getInstance();  
		SimpleDateFormat myFmt1=new SimpleDateFormat("yyyy-MM-dd");  //转换天数
		c.add(c.DATE, -1);
		//查询昨天
		List<ParkingTimesStatistics> park=null;
		List<Object[]> lis=null;
		List<Object[]> lisday=new ArrayList<Object []>();//添加第4个空间
		List<Object[]> lisMoth=new ArrayList<Object []>();//添加第4个空间
		List<Object[]> lisYear=new ArrayList<Object []>();//添加第4个空间
		
		if(tab==0)
		{
			park=parkingTimeService.findByParkingGoTop(myFmt1.format(c.getTime()));
			lis=parkingTimeService.findByParkingMothgoTop("2018-02");
			for(int i=0;i<lis.size();i++)
			{
				Integer id=Integer.parseInt(String.valueOf(lis.get(i)[2]));
				ParkingInfo parks=parkingInfoService.getParkingInfoById(id);
				Object[] moth=new Object[4];
				for(int j=0;j<moth.length;j++){
					if(j==3)
						moth[3]=parks;
					else
						moth[j]= lis.get(i)[j];
				}
				lisMoth.add(moth);
			}
			List<Object[]> listow=parkingTimeService.findByParkingYearGoTop("2018");
			for(int i=0;i<listow.size();i++)
			{
				Integer id=Integer.parseInt(String.valueOf(listow.get(i)[2]));
				ParkingInfo parks=parkingInfoService.getParkingInfoById(id);
				Object[] year=new Object[4];
				for(int j=0;j<year.length;j++){
					if(j==3)
						year[3]=parks;
					else
						year[j]= listow.get(i)[j];
				}
				lisYear.add(year);
			}
		}else if(tab==1)
		{
			park=parkingTimeService.findByParkingComeTop(myFmt1.format(c.getTime()));
			lis=parkingTimeService.findByParkingMothComeTop("2018-02");
			for(int i=0;i<lis.size();i++)
			{
				Integer id=Integer.parseInt(String.valueOf(lis.get(i)[2]));
				ParkingInfo parks=parkingInfoService.getParkingInfoById(id);
				Object[] moth=new Object[4];
				for(int j=0;j<moth.length;j++){
					if(j==3)
						moth[3]=parks;
					else
						moth[j]= lis.get(i)[j];
				}
				lisMoth.add(moth);
			}
			List<Object[]> listow=parkingTimeService.findByParkingYearComeTop("2018");
			for(int i=0;i<listow.size();i++)
			{
				Integer id=Integer.parseInt(String.valueOf(listow.get(i)[2]));
				ParkingInfo parks=parkingInfoService.getParkingInfoById(id);
				Object[] year=new Object[4];
				for(int j=0;j<year.length;j++){
					if(j==3)
						year[3]=parks;
					else
						year[j]= listow.get(i)[j];
				}
				lisYear.add(year);
			}
		}else if(tab==2)
		{
			//昨日
			List<Object[]> lib=parkingPaymentService.findByPayTopYesterday(myFmt1.format(c.getTime()));
			lis=parkingPaymentService.findByPayTopMoth("2018-03");
			for(int i=0;i<lib.size();i++)
			{
				Integer id=Integer.parseInt(String.valueOf(lib.get(i)[1]));
				ParkingInfo parks=parkingInfoService.getParkingInfoById(id);
				Object[] day=new Object[3];
				for(int j=0;j<day.length;j++){
					if(j==2)
						day[2]=parks;
					else
						day[j]= lib.get(i)[j];
				}
				lisday.add(day);
			}
			for(int i=0;i<lis.size();i++)
			{
				Integer id=Integer.parseInt(String.valueOf(lis.get(i)[1]));
				ParkingInfo parks=parkingInfoService.getParkingInfoById(id);
				Object[] moth=new Object[3];
				for(int j=0;j<moth.length;j++){
					if(j==2)
						moth[2]=parks;
					else
						moth[j]= lis.get(i)[j];
				}
				lisMoth.add(moth);
			}
			List<Object[]> listow=parkingPaymentService.findByPayTopYear("2018");
			for(int i=0;i<listow.size();i++)
			{
				Integer id=Integer.parseInt(String.valueOf(listow.get(i)[1]));
				ParkingInfo parks=parkingInfoService.getParkingInfoById(id);
				Object[] year=new Object[3];
				for(int j=0;j<year.length;j++){
					if(j==2)
						year[2]=parks;
					else
						year[j]= listow.get(i)[j];
				}
				lisYear.add(year);
			}
		}
		result.put("park", park);
		result.put("parkday", lisday);
		result.put("parkMoth", lisMoth);
		result.put("parkYear", lisYear);
		return JSONArray.toJSON(result);
	}
	
	
}
