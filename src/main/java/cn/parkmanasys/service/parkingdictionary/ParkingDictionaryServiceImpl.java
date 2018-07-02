package cn.parkmanasys.service.parkingdictionary;

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

import cn.parkmanasys.dao.ParkingAccountMapper;
import cn.parkmanasys.dao.ParkingDictionaryMapper;
import cn.parkmanasys.dao.ParkingSpaceMapper;
import cn.parkmanasys.entity.ParkingAccount;
import cn.parkmanasys.entity.ParkingDictionary;
import cn.parkmanasys.entity.ParkingSpace;
import cn.parkmanasys.util.PageSupport;

@Service
public class ParkingDictionaryServiceImpl implements ParkingDictionaryService{

	@PersistenceContext
	private EntityManager em;
	@Resource
	private ParkingDictionaryMapper parkingDictionaryMapper;
	
	@Override
	public List<ParkingDictionary> getParkingDictionaryByPage(ParkingDictionary pa, PageSupport psPage , Integer parkingInfoId) {
        List<ParkingDictionary> paList = null;
		try {
			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<ParkingDictionary> criteriaQuery = criteriaBuilder.createQuery(ParkingDictionary.class);
			Root<ParkingDictionary> root = criteriaQuery.from(ParkingDictionary.class);
//			Predicate condition = null;			
			List<Predicate> conditions = new ArrayList<Predicate>();
			conditions.add(criteriaBuilder.equal(root.get("parkingInfo").get("id"), parkingInfoId));
			if(pa != null){
				if(pa.getAreaNumber() != null && !pa.getAreaNumber().equals("")){
//					condition = criteriaBuilder.like(criteriaBuilder.lower(root.get("areaNumber")), StringUtils.lowerCase("%"+pa.getAreaNumber()+"%"));
					conditions.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("areaNumber")), StringUtils.lowerCase("%"+pa.getAreaNumber()+"%")));
					
				}
			}
			/*if(condition != null){
				criteriaQuery.where(condition);
			}	*/	
			if(conditions != null){
				criteriaQuery.where(conditions.toArray(new Predicate[0]));
			}
			
			TypedQuery<ParkingDictionary> typedQuery = em.createQuery(criteriaQuery);
			//分页
			typedQuery.setFirstResult((psPage.getCurrentPageNo()-1)*psPage.getPageSize());
			typedQuery.setMaxResults(psPage.getPageSize());	
			paList = typedQuery.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return paList;
	}
	@Override
	public int getParkingDictionaryCount(ParkingDictionary pa , Integer parkingInfoId) {
        int count = 0;
		try {
			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
			Root<ParkingDictionary> root = criteriaQuery.from(ParkingDictionary.class);
			List<Predicate> conditions = new ArrayList<Predicate>();
			conditions.add(criteriaBuilder.equal(root.get("parkingInfo").get("id"), parkingInfoId));
//			Predicate condition = null;
			if(pa != null){
				if(pa.getAreaNumber() != null && !pa.getAreaNumber().equals("")){
					conditions.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("areaNumber")), StringUtils.lowerCase("%"+pa.getAreaNumber()+"%")));
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
	@Override
	public ParkingDictionary getParkingDictionaryById(Integer id) {
		ParkingDictionary parkingDictionary = null;
		
		try {
			parkingDictionary = parkingDictionaryMapper.findOne(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return parkingDictionary;
	}
	@Override
	public boolean addParkingDictionary(ParkingDictionary psAdd) {
		ParkingDictionary parkingDictionary = null;
		
		try {
			parkingDictionary = parkingDictionaryMapper.save(psAdd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(parkingDictionary != null){
			return true;
		}
		
		return false;
	}
	@Override
	public ParkingDictionary updateParkingDictionary(ParkingDictionary psUpdate) {
		ParkingDictionary parkingDictionaryUpdate= null;
		
		try {
			//根据id，获取停车位信息
			parkingDictionaryUpdate = parkingDictionaryMapper.findOne(psUpdate.getId());
			
			if(psUpdate.getAreaName() != null){
				parkingDictionaryUpdate.setAreaName(psUpdate.getAreaName());
			}
			if(psUpdate.getAreaNumber() != null){
				parkingDictionaryUpdate.setAreaNumber(psUpdate.getAreaNumber());
			}
//			if(psUpdate.getParkingId() != null){
//				parkingDictionaryUpdate.setParkingId(psUpdate.getParkingId());
//			}

			parkingDictionaryUpdate = parkingDictionaryMapper.save(parkingDictionaryUpdate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return parkingDictionaryUpdate;
	}
	@Override
	public boolean delParkingDictionary(Integer id) {
		try {
			//根据id，获取停车位信息
			parkingDictionaryMapper.delete(id);
				
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//根据停车场id获取,字典表的信息
	public List<ParkingDictionary> getParkingDictionaryByParkingId(Integer parkingId){
		List<ParkingDictionary> parkingDictionaryList = null;
	
		try {
			parkingDictionaryList = parkingDictionaryMapper.findParkingDictionaryByParkingId(parkingId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return parkingDictionaryList;
	}
	
}
