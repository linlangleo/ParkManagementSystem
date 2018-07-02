package cn.parkmanasys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.parkmanasys.entity.ParkingPayment;

@Repository
public interface ParkingPaymentMapper extends JpaRepository<ParkingPayment, Integer>{
	
	//根据订单ID查询停车缴费信息
	@Query("from #{#entityName} p where p.parkingInfo.id=:parkingId")
	public List<ParkingPayment> findByOrderId(@Param("parkingId")Integer parkingId);
	
	//查询昨天的停车场 的交易流水
	@Query(value="select sum(t.totalcost) from PARKINGPAYMENT t where t.enddate IN (select s.enddate from PARKINGPAYMENT s where floor(sysdate-s.enddate)=1) and t.parkingid=:parkingId group by t.parkingid",nativeQuery=true)
	public Integer findByPayYesterday(@Param("parkingId")Integer parkingId);
	
	//查询前面七天交易图形数据
	@Query(value="select sum(t.totalcost),TO_CHAR(t.enddate,'yyyy-mm-dd') from PARKINGPAYMENT t where t.parkingid=:parkingId and t.enddate between to_date(:newtime,'yyyy-mm-dd')and to_date(:oldtime,'yyyy-mm-dd') group by TO_CHAR(t.enddate,'yyyy-mm-dd')",nativeQuery=true)
	public List<Object[]> findByDayPayInfo(@Param("newtime")String newtime,@Param("oldtime")String oldtime,@Param("parkingId")Integer parkingId);
	
	//查询月交易图形数据
	@Query(value="select sum(t.totalcost),TO_CHAR(t.enddate,'yyyy-mm-dd') from PARKINGPAYMENT t where t.parkingid=:parkingId and t.enddate between to_date(:newtime,'yyyy-mm-dd')and to_date(:oldtime,'yyyy-mm-dd') group by TO_CHAR(t.enddate,'yyyy-mm-dd')",nativeQuery=true)
	public List<Object[]> findByMothPayInfo(@Param("newtime")String newtime,@Param("oldtime")String oldtime,@Param("parkingId")Integer parkingId);
	
	//查询年交易图形数据
	@Query(value="select Sum(t.totalcost),TO_CHAR(t.enddate,'MM') from PARKINGPAYMENT t where t.parkingid=:parkingId and TO_CHAR(t.enddate,'yyyy')=:newtime group by TO_CHAR(t.enddate,'MM')",nativeQuery=true)
	public List<Object[]> findByYearPayInfo(@Param("newtime")String newtime,@Param("parkingId")Integer parkingId);
	
	//查询停车场使用停车场的交易年份
	@Query(value="select TO_CHAR(t.enddate,'yyyy') from PARKINGPAYMENT t where t.parkingid=:parkingId group by TO_CHAR(t.enddate,'yyyy')",nativeQuery=true)
	public List<String> findByDateYear(@Param("parkingId")Integer parkingid);
	
	//查询昨日的交易流水Top5
	@Query(value="SELECT * FROM (SELECT sum(t.totalcost),t.parkingid FROM PARKINGPAYMENT t where t.enddate=to_date(:newtime,'yyyy-mm-dd') group by t.parkingid ORDER BY sum(t.totalcost) DESC) where rownum<=5",nativeQuery=true)
	public List<Object[]> findByPayTopYesterday(@Param("newtime")String time);
	
	//查询上月的交易流水Top5
	@Query(value="SELECT * FROM (SELECT sum(t.totalcost),t.parkingid FROM PARKINGPAYMENT t where to_char(t.enddate,'yyyy-mm')=:newtime group by t.parkingid ORDER BY sum(t.totalcost) DESC) where rownum<=5",nativeQuery=true)
	public List<Object[]> findByPayTopMoth(@Param("newtime")String time);
	
	//查询上年的交易流水Top5
	@Query(value="SELECT * FROM (SELECT sum(t.totalcost),t.parkingid FROM PARKINGPAYMENT t where  to_char(t.enddate,'yyyy') =:newtime group by t.parkingid ORDER BY sum(t.totalcost) DESC) where rownum<=5",nativeQuery=true)
	public List<Object[]> findByPayTopYear(@Param("newtime")String time);
	
	
}
