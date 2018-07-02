package cn.parkmanasys.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.parkmanasys.entity.ParkingTimesStatistics;

/**
 * 车流dao
 * @author Dickson 
 */
@Repository
public interface ParkingTimesStatisticsMapper extends JpaRepository<ParkingTimesStatistics, Integer> {
			
	//查询昨日车流
	@Query(value="select * from PARKINGTIMESSTATISTICS t where t.creationdate IN (select s.creationdate from PARKINGTIMESSTATISTICS s where floor(sysdate-s.creationdate)=1) and t.parkingid=:parkingid", nativeQuery = true)
	public ParkingTimesStatistics findBydateYesterday(@Param("parkingid")Integer parkingid);
	
	//查询折线图数据
	@Query(value="select * from PARKINGTIMESSTATISTICS t where t.parkingid=:parkingid and t.creationdate between to_date(:start,'yyyy-mm-dd')and to_date(:end,'yyyy-mm-dd')",nativeQuery=true)
	public List<ParkingTimesStatistics> findByBrokenlineDate(@Param("parkingid")Integer parkingid,@Param("start")String start,@Param("end")String end);
	
	//查询停车场使用所有年份
	@Query(value="select TO_CHAR(t.creationdate,'yyyy') from PARKINGTIMESSTATISTICS t where t.parkingid=:parkingid group by TO_CHAR(t.creationdate,'yyyy') ",nativeQuery=true)
	public List<String> findByDateYear(@Param("parkingid")Integer parkingid);
	
	//查询一年的车流进出数据
	@Query(value="select Sum(t.entertimes),SUM(t.leavetimes),TO_CHAR(t.creationdate,'MM') from PARKINGTIMESSTATISTICS t where t.parkingid=:parkingid and TO_CHAR(t.creationdate,'yyyy')=:year group by TO_CHAR(t.creationdate,'MM')",nativeQuery=true)
	public List<Object[]> findByYearDate(@Param("parkingid")Integer parkingid,@Param("year")Integer year);
	
	//查询昨日排名前五的进次数
	@Query(value="SELECT * FROM (SELECT * FROM PARKINGTIMESSTATISTICS t where t.creationdate=to_date(:date,'yyyy-mm-dd') ORDER BY t.entertimes DESC)WHERE ROWNUM <= 5",nativeQuery=true)
	public List<ParkingTimesStatistics> findByParkingGoTop(@Param("date")String time);
	//查询昨日排名前五的出次数
	@Query(value="SELECT * FROM (SELECT * FROM PARKINGTIMESSTATISTICS t where t.creationdate=to_date(:date,'yyyy-mm-dd') ORDER BY t.leavetimes DESC)WHERE ROWNUM <= 5",nativeQuery=true)
	public List<ParkingTimesStatistics> findByParkingComeTop(@Param("date")String time);
	
	
	//查询上月的排名前五的进次数
	@Query(value="SELECT * FROM (SELECT sum(t.entertimes) as t1 ,sum(t.leavetimes) as t2,t.parkingid as t3 FROM PARKINGTIMESSTATISTICS t where to_char(t.creationdate,'yyyy-mm')=:date group by t.parkingid     ) s WHERE ROWNUM <= 5 ORDER BY s.t1 DESC",nativeQuery=true)
	public List<Object[]> findByParkingMothgoTop(@Param("date")String time);
	
	//查询上月的排名前五的出次数
	@Query(value="SELECT * FROM (SELECT sum(t.entertimes) as t1 ,sum(t.leavetimes) as t2,t.parkingid as t3 FROM PARKINGTIMESSTATISTICS t where to_char(t.creationdate,'yyyy-mm')=:date group by t.parkingid      ) s WHERE ROWNUM <= 5 ORDER BY s.t1 DESC",nativeQuery=true)
	public List<Object[]> findByParkingMothComeTop(@Param("date")String time);
	
	//查询上一年的排名前五的进次数
	@Query(value="SELECT * FROM (SELECT sum(t.entertimes) as t1 ,sum(t.leavetimes) as t2,t.parkingid as t3 FROM PARKINGTIMESSTATISTICS t where to_char(t.creationdate,'yyyy')=:date group by t.parkingid      ) s WHERE ROWNUM <= 5 ORDER BY s.t1 DESC",nativeQuery=true)
	public List<Object[]> findByParkingYearGoTop(@Param("date")String time);
	
	//查询上一年的排名前五的出次数
	@Query(value="SELECT * FROM (SELECT sum(t.entertimes) as t1 ,sum(t.leavetimes) as t2,t.parkingid as t3 FROM PARKINGTIMESSTATISTICS t where to_char(t.creationdate,'yyyy')=:date group by t.parkingid      ) s WHERE ROWNUM <= 5 ORDER BY s.t2 DESC",nativeQuery=true)
	public List<Object[]> findByParkingYearComeTop(@Param("date")String time);
	
	
}
