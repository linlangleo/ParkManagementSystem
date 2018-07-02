package cn.parkmanasys.service.parkingTimesStatistics;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import cn.parkmanasys.dao.ParkingTimesStatisticsMapper;
import cn.parkmanasys.entity.ParkingTemporaryInfo;
import cn.parkmanasys.entity.ParkingTimesStatistics;

/**
 * 车流业务层
 * @author Dickson 
 */
@Service
public class ParkingTimesStatisticsServiceimpl implements ParkingTimesStatisticsService{
		
	
	//实体管理器
	@PersistenceContext
	private EntityManager em;
	
	//注入业务
	@Resource
	private ParkingTimesStatisticsMapper parkingTimesMapper;
	
	/**
	 * 查询昨日车流 
	 * @author Dickson
	 */
	@Override
	public ParkingTimesStatistics findBydateYesterday(Integer parkingid) {
		// TODO Auto-generated method stub
		ParkingTimesStatistics park=null;
		try{
			park=parkingTimesMapper.findBydateYesterday(parkingid);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return park;
	}
	/**
	 * 查询每7天的折线图数据
	 * @author Dickson
	 */
	@Override
	public List<ParkingTimesStatistics> findByBrokenlineDate(Integer parkingid,String start,String end) {
		// TODO Auto-generated method stub
		List<ParkingTimesStatistics> psList = null;
		try {
			System.out.println(start);
			psList = parkingTimesMapper.findByBrokenlineDate(parkingid,start, end);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return psList;
	}
	@Override
	public List<String> findByDateYear(Integer parkingid) {
		// TODO Auto-generated method stub
		List<String> year=null;
		try{
			year=parkingTimesMapper.findByDateYear(parkingid);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return year;
	}
	@Override
	public List<Object[]> findByYearDate(Integer parkingid, Integer year) {
		// TODO Auto-generated method stub
		List<Object[]> list=null;
		try{
			list=parkingTimesMapper.findByYearDate(parkingid, year);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	//查询昨日排名前五的进次数
	@Override
	public List<ParkingTimesStatistics> findByParkingGoTop(String time) {
		// TODO Auto-generated method stub
		List<ParkingTimesStatistics> parklist=null;
		try{
			parklist=parkingTimesMapper.findByParkingGoTop(time);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return parklist;
	}
	//查询昨日排名前五的出次数
	@Override
	public List<ParkingTimesStatistics> findByParkingComeTop(String time) {
		// TODO Auto-generated method stub
		List<ParkingTimesStatistics> parklist=null;
		try{
			parklist=parkingTimesMapper.findByParkingComeTop(time);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return parklist;
	}
	//查询上月的排名前五的进次数
	@Override
	public List<Object[]> findByParkingMothgoTop(String time) {
		// TODO Auto-generated method stub
		List<Object[]> lis=null;
		try{
			lis=parkingTimesMapper.findByParkingMothgoTop(time);
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return lis;
	}
	//查询上月的排名前五的出次数
	@Override
	public List<Object[]> findByParkingMothComeTop(String time) {
		// TODO Auto-generated method stub
		List<Object[]> lis=null;
		try{
			lis=parkingTimesMapper.findByParkingMothComeTop(time);
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return lis;
	}
	
	//查询上一年的排名前五的进次数
	@Override
	public List<Object[]> findByParkingYearGoTop(String time) {
		// TODO Auto-generated method stub
		List<Object[]> lis=null;
		try{
			lis=parkingTimesMapper.findByParkingYearGoTop(time);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return lis;
	}
	//查询上一年的排名前五的出次数
	@Override
	public List<Object[]> findByParkingYearComeTop(String time) {
		// TODO Auto-generated method stub
		List<Object[]> lis=null;
		try{
			lis=parkingTimesMapper.findByParkingYearComeTop(time);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return lis;
	}
	
}
