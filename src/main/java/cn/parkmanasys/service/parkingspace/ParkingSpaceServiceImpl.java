package cn.parkmanasys.service.parkingspace;

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
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.parkmanasys.dao.ParkingSpaceMapper;
import cn.parkmanasys.entity.ParkingInfo;
import cn.parkmanasys.entity.ParkingSpace;
import cn.parkmanasys.util.PageSupport;

@Service
public class ParkingSpaceServiceImpl implements ParkingSpaceService{
	@PersistenceContext
	private EntityManager em;
	@Resource
	private ParkingSpaceMapper parkingSpaceMapper;
	
	//获得所有的停车位
	public List<ParkingSpace> getParkingSpaceByPage(ParkingSpace ps, PageSupport psPage, Integer parkingInfoId){
		List<ParkingSpace> psList = null;
		
		try {
			//创建匹配器，即如何使用查询条件（实例查询）
//	        ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
//	        		.withIgnoreCase(true)
//	                .withMatcher("parkingSpaceNumber", GenericPropertyMatchers.startsWith()) //姓名采用“开始匹配”的方式查询
//	                .withIgnorePaths("focus");  //忽略属性：是否关注。因为是基本类型，需要忽略掉
//			Example<ParkingSpace> ex = Example.of(ps, matcher); 
//			
//			psList = parkingSpaceMapper.findByParkingSpaceNumber("X988");
//			psList = parkingSpaceMapper.findAll();
			
			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<ParkingSpace> criteriaQuery = criteriaBuilder.createQuery(ParkingSpace.class);
			Root<ParkingSpace> root = criteriaQuery.from(ParkingSpace.class);
			List<Predicate> conditions = new ArrayList<Predicate>();
//			
			//确保查询的数据是本停车场的数据,进行停车场信息的判断
			conditions.add(criteriaBuilder.equal(root.get("parkingInfo").get("id"), parkingInfoId));
			
			if(ps != null){//存放条件
				if(ps.getParkingSpaceNumber() != null && !ps.getParkingSpaceNumber().equals("")){
					conditions.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("parkingSpaceNumber")), StringUtils.lowerCase("%"+ps.getParkingSpaceNumber()+"%")));
				}
				if(ps.getParkingDictionary().getAreaName() != null && !ps.getParkingDictionary().getAreaName().equals("")){
					conditions.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("parkingDictionary").get("areaName")), StringUtils.lowerCase("%"+ps.getParkingDictionary().getAreaName()+"%")));
				}
			}
			if(conditions != null){
				criteriaQuery.where(conditions.toArray(new Predicate[0]));
			}
			
			TypedQuery<ParkingSpace> typedQuery = em.createQuery(criteriaQuery);

			
			//分页
			typedQuery.setFirstResult((psPage.getCurrentPageNo()-1)*psPage.getPageSize());
			typedQuery.setMaxResults(psPage.getPageSize());
			
			psList = typedQuery.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return psList;
	}
	
	
	//根据条件，获得所有的停车位数据，数量
	public int getParkingSpaceCount(ParkingSpace ps, Integer parkingInfoId){
		int count = 0;
		
		try {
			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
			Root<ParkingSpace> root = criteriaQuery.from(ParkingSpace.class);
			List<Predicate> conditions = new ArrayList<Predicate>();

			//确保查询的数据是本停车场的数据,进行停车场信息的判断
			conditions.add(criteriaBuilder.equal(root.get("parkingInfo").get("id"), parkingInfoId));
			
			if(ps != null){//存放条件
				if(ps.getParkingSpaceNumber() != null && !ps.getParkingSpaceNumber().equals("")){
					conditions.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("parkingSpaceNumber")), StringUtils.lowerCase("%"+ps.getParkingSpaceNumber()+"%")));
				}
				if(ps.getParkingDictionary().getAreaName() != null && !ps.getParkingDictionary().getAreaName().equals("")){
					conditions.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("parkingDictionary").get("areaName")), StringUtils.lowerCase("%"+ps.getParkingDictionary().getAreaName()+"%")));
				}
			}
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

	//根据id，获取停车位信息
	public ParkingSpace getParkingSpaceById(Integer id){
		ParkingSpace parkingSpace = null;
		
		try {
			parkingSpace = parkingSpaceMapper.getParkingSpaceById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return parkingSpace;
	}
	

	//新增停车位
	public boolean addParkingSpace(ParkingSpace psAdd){
		ParkingSpace parkingSpace = null;
		
		try {
			parkingSpace = parkingSpaceMapper.save(psAdd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(parkingSpace != null){
			return true;
		}
		
		return false;
	}
	

	//修改停车位
	public ParkingSpace updateParkingSpace(ParkingSpace psUpdate){
		ParkingSpace parkingSpaceUpdate= null;
		
		try {
			//根据id，获取停车位信息
			parkingSpaceUpdate = parkingSpaceMapper.findOne(psUpdate.getId());
			
			if(psUpdate.getParkingSpaceNumber() != null){
				parkingSpaceUpdate.setParkingSpaceNumber(psUpdate.getParkingSpaceNumber());
			}
			if(psUpdate.getParkingDictionary().getId() != null){
				parkingSpaceUpdate.setParkingDictionary(psUpdate.getParkingDictionary());
			}
			if(psUpdate.getParkingSpaceStatus().getId() != null){
				parkingSpaceUpdate.setParkingSpaceStatus(psUpdate.getParkingSpaceStatus());
			}

			parkingSpaceUpdate = parkingSpaceMapper.saveAndFlush(parkingSpaceUpdate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return parkingSpaceUpdate;
	}
	

	//删除停车位
	public boolean delParkingSpace(Integer id){
		try {
			//根据id，获取停车位信息
			parkingSpaceMapper.delete(id);
				
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	@Override
	public List<ParkingSpace> getParkingSpaceByParkingId(Integer parkingId) {
	List<ParkingSpace> parkingSpaceList = null;
		
		try {
			parkingSpaceList = parkingSpaceMapper.findParkingSpaceByParkingId(parkingId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return parkingSpaceList;
	}
	

	//获取本停车场，所有的停车位信息
	public List<ParkingSpace> getAllParkingSpace(Integer parkingInfoId){
		List<ParkingSpace> psList = null;
		
		try {
			//根据id，获取停车位信息
			psList = parkingSpaceMapper.getParkingSpaceByParkingId(parkingInfoId);
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return psList;
	}
	
}
