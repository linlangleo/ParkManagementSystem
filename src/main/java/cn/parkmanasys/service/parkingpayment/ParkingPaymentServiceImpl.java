package cn.parkmanasys.service.parkingpayment;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cn.parkmanasys.dao.ParkingPaymentMapper;
import cn.parkmanasys.entity.ParkingDictionary;
import cn.parkmanasys.entity.ParkingPayment;
import cn.parkmanasys.util.PageSupport;

@Service
public class ParkingPaymentServiceImpl implements ParkingPaymentService{
	@PersistenceContext
	/**
	 * 实体管理器
	 */
	private EntityManager em;
	
	@Resource
	/**
	 * 相对应的Dao层
	 */
	private ParkingPaymentMapper parkingPaymentMapper;
	
	@Override
	/**
	 * 查询停车缴费表信息并分页
	 */
	public List<ParkingPayment> getParkingPaymentByPage(ParkingPayment pp, PageSupport ppPage,Integer parkingId) {
			List<ParkingPayment> pList = null;
			try {
				CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
				CriteriaQuery<ParkingPayment> criteriaQuery = criteriaBuilder.createQuery(ParkingPayment.class);
				Root<ParkingPayment> root = criteriaQuery.from(ParkingPayment.class);
				List<Predicate> conditions = new ArrayList<Predicate>();
				
				conditions.add(criteriaBuilder.equal(root.get("parkingInfo").get("id"), parkingId));
				
/*				if(pp != null){
					if (null!=pp.getStopType().getTypeName()&&!pp.getStopType().getTypeName().equals("")) {
						conditions.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("stopType").get("typeName")),StringUtils.lowerCase("%"+pp.getStopType().getTypeName()+"%")));
					}
				}*/
				
				if(conditions != null){
					criteriaQuery.where(conditions.toArray(new Predicate[0]));
				}
				
				TypedQuery<ParkingPayment> typedQuery = em.createQuery(criteriaQuery);

				
				//分页计算
				typedQuery.setFirstResult((ppPage.getCurrentPageNo()-1)*ppPage.getPageSize());
				typedQuery.setMaxResults(ppPage.getPageSize());
				
				pList = typedQuery.getResultList();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return pList;
		}

	@Override
	/**
	 * 
	 */
	public int getParkingPaymentCount(ParkingPayment pp,Integer parkingId) {
		int count = 0;
		
		try {
			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
			Root<ParkingPayment> root = criteriaQuery.from(ParkingPayment.class);
			List<Predicate> conditions = new ArrayList<Predicate>();
			
			conditions.add(criteriaBuilder.equal(root.get("parkingInfo").get("id"), parkingId));
			
/*			if(pp != null){
				if (null!=pp.getStopType().getTypeName()&&!pp.getStopType().getTypeName().equals("")) {
					conditions.add(criteriaBuilder.like
					(criteriaBuilder.lower(root.get("stopType").get("typeName")), 
					StringUtils.lowerCase("%"+pp.getStopType().getTypeName()+"%")));
				}
			}*/
			if(conditions != null){
				criteriaQuery.where(conditions.toArray(new Predicate[0]));
			}
			
			criteriaQuery.select(criteriaBuilder.count(root));//查询总数，未去重复
			
			TypedQuery<Long> typedQuery = em.createQuery(criteriaQuery);

			count = new Long(typedQuery.getSingleResult()).intValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	@Override
	public ParkingPayment getParkingPayMentById(Integer id) {
		ParkingPayment parkingPayment = null;
		
		try {
			parkingPayment = parkingPaymentMapper.findOne(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return parkingPayment;
	}
	
	//新增停车缴费信息
	public boolean addParkingPayMent(ParkingPayment ppAdd){
		ParkingPayment parkingPayment = null;
		
		try {
			parkingPayment = parkingPaymentMapper.save(ppAdd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(parkingPayment != null){
			return true;
		}
		return false;
	}
	

	//修改停车缴费信息
	public ParkingPayment updateParkingPayment(ParkingPayment ppUpdate){
		ParkingPayment parkingPaymentUpdate = null;
		
		try {
			//根据id，获取停车位信息
			parkingPaymentUpdate = parkingPaymentMapper.findOne(ppUpdate.getId());

			if(null!=ppUpdate.getTotalCost()){
				parkingPaymentUpdate.setTotalCost(ppUpdate.getTotalCost());
			}
			if (null!=ppUpdate.getTotalTime()) {
				parkingPaymentUpdate.setTotalTime(ppUpdate.getTotalTime());
			}
			if (null!=ppUpdate.getStopStatus().getId()) {
				parkingPaymentUpdate.setStopStatus(ppUpdate.getStopStatus());
			}
			if (null!=ppUpdate.getStartDate()) {
				parkingPaymentUpdate.setStartDate(ppUpdate.getStartDate());
			}
			if (null!=ppUpdate.getEndDate()) {
				parkingPaymentUpdate.setEndDate(ppUpdate.getEndDate());
			}
			parkingPaymentUpdate = parkingPaymentMapper.save(parkingPaymentUpdate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return parkingPaymentUpdate;
	}
	

	//删除停车缴费信息
	public boolean delParkingPayMent(Integer id){
		try {
			//根据id，获取停车位信息
			parkingPaymentMapper.delete(id);
				
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	//查询昨天的停车场 的交易流水
	@Override
	public Integer findByPayYesterday(Integer parkingId) {
		Integer park=null;
		try{
			park=parkingPaymentMapper.findByPayYesterday(parkingId);
		}catch (Exception e) {
			
		}
		return park;
	}
	
	//查询前面七天交易图形数据
	@Override
	public List<Object[]> findByDayPayInfo(String newtime, String oldtime, Integer parkingId) {
		List<Object[]> park=null;
		try{
			park=parkingPaymentMapper.findByDayPayInfo(newtime, oldtime, parkingId);
		}catch (Exception e) {
		}
		return park;
	}

	@Override
	public List<String> findByDateYear(Integer parkingid) {
		List<String> park=null;
		try{
			park=parkingPaymentMapper.findByDateYear(parkingid);
		}catch (Exception e) {
		}
		return park;
	}
	//查询月交易图形数据
	@Override
	public List<Object[]> findByMothPayInfo(String newtime,String oldtime,Integer parkingId) {
		// TODO Auto-generated method stub
		List<Object[]> park=null;
		try{
			park=parkingPaymentMapper.findByMothPayInfo(newtime,oldtime,parkingId);
		}catch (Exception e) {
		}
		return park;
	}
	//查询年交易图形数据
	@Override
	public List<Object[]> findByYearPayInfo(String newtime, Integer parkingId) {
		// TODO Auto-generated method stub
		List<Object[]> park=null;
		try{
			park=parkingPaymentMapper.findByYearPayInfo(newtime, parkingId);
		}catch (Exception e) {
		}
		return park;
	}

	//查询昨日的交易流水Top5
	@Override
	public List<Object[]> findByPayTopYesterday(String time) {
		// TODO Auto-generated method stub
		List<Object[]> lis=null;
		try{
			lis=parkingPaymentMapper.findByPayTopYesterday(time);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return lis;
	}
	//查询上月的交易流水Top5
	@Override
	public List<Object[]> findByPayTopMoth(String time) {
		// TODO Auto-generated method stub
		List<Object[]> lis=null;
		try{
			lis=parkingPaymentMapper.findByPayTopMoth(time);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return lis;
	}
	//查询上年的交易流水Top5
	@Override
	public List<Object[]> findByPayTopYear(String time) {
		// TODO Auto-generated method stub
		List<Object[]> lis=null;
		try{
			lis=parkingPaymentMapper.findByPayTopYear(time);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return lis;
	}
}
	
