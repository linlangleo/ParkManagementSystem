package cn.parkmanasys.service.parkingpayment;

import java.util.List;

import org.springframework.data.repository.query.Param;

import cn.parkmanasys.entity.ParkingPayment;
import cn.parkmanasys.util.PageSupport;

public interface ParkingPaymentService {
	//根据分页和条件，获得当前停车场的停车信息
	public List<ParkingPayment> getParkingPaymentByPage(ParkingPayment pp, PageSupport ppPage,Integer parkingId);
	
	//根据条件，获得当前停车场的停车信息，数量
	public int getParkingPaymentCount(ParkingPayment pp,Integer parkingId);
	
	//根据id，获取停车缴费信息
	public ParkingPayment getParkingPayMentById(Integer parkingId);
	
	//新增停车场缴费信息
	public boolean addParkingPayMent(ParkingPayment ppAdd);

	//更改停车缴费表信息
	public ParkingPayment updateParkingPayment(ParkingPayment ppUpdate);
	
	//删除停车缴费信息
	public boolean delParkingPayMent(Integer id);
	
	//查询昨天的停车场 的交易流水
	public Integer findByPayYesterday(Integer parkingId);
	
	//查询前面七天交易图形数据
	public List<Object[]> findByDayPayInfo(String newtime,String oldtime,Integer parkingId);
	
	//查询月交易图形数据
	public List<Object[]> findByMothPayInfo(String newtime,String oldtime,Integer parkingId);
	
	//查询年交易图形数据
	public List<Object[]> findByYearPayInfo(String newtime,Integer parkingId);
	
	//查询停车场使用停车场的交易年份
	public List<String> findByDateYear(Integer parkingid);
	
	//查询昨日的交易流水Top5
	public List<Object[]> findByPayTopYesterday(String time);
	
	//查询上月的交易流水Top5
	public List<Object[]> findByPayTopMoth(String time);
	
	//查询上年的交易流水Top5
	public List<Object[]> findByPayTopYear(String time);
	
}


