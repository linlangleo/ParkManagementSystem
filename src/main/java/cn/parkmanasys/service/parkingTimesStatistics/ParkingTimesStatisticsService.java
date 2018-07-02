package cn.parkmanasys.service.parkingTimesStatistics;

import java.util.List;


import cn.parkmanasys.entity.ParkingTimesStatistics;

public interface ParkingTimesStatisticsService {
		
	//查询昨日车流
	public ParkingTimesStatistics findBydateYesterday(Integer parkingid);
	
	//查询每10天的折线图数据
	public List<ParkingTimesStatistics> findByBrokenlineDate(Integer parkingid,String start,String end);
	
	//查询停车场使用所有年份
	public List<String> findByDateYear(Integer parkingid);
	
	//查询一年的车流进出数据
	public List<Object[]> findByYearDate(Integer parkingid,Integer year);
	
	//查询昨日排名前五的进次数
	public List<ParkingTimesStatistics> findByParkingGoTop(String time);
	//查询昨日排名前五的出次数
	public List<ParkingTimesStatistics> findByParkingComeTop(String time);
		
		
	//查询上月的排名前五的进次数
	public List<Object[]> findByParkingMothgoTop(String time);
		
	//查询上月的排名前五的出次数
	public List<Object[]> findByParkingMothComeTop(String time);
		
	//查询上一年的排名前五的进次数
	public List<Object[]> findByParkingYearGoTop(String time);
	
	//查询上一年的排名前五的出次数
	public List<Object[]> findByParkingYearComeTop(String time);
}
