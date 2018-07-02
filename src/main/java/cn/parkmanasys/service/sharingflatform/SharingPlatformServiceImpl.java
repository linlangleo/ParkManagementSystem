package cn.parkmanasys.service.sharingflatform;

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
import cn.parkmanasys.dao.SharingPlatformMapper;
import cn.parkmanasys.entity.ParkingAccount;
import cn.parkmanasys.entity.ParkingDictionary;
import cn.parkmanasys.entity.ParkingSpace;
import cn.parkmanasys.entity.SharingPlatform;
import cn.parkmanasys.entity.SharingStatus;
import cn.parkmanasys.util.PageSupport;

@Service
public class SharingPlatformServiceImpl implements SharingPlatformService{
	@PersistenceContext
	private EntityManager em;
	@Resource
	private SharingPlatformMapper sharingplatformmapper;

	//获得所有的共享信息
	public List<SharingPlatform> getSharingPlatform(SharingPlatform sp, PageSupport spPage,Integer parkingInfoId) {
		List<SharingPlatform> spList = null;
		
		try {
			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<SharingPlatform> criteriaQuery = criteriaBuilder.createQuery(SharingPlatform.class);
			Root<SharingPlatform> root = criteriaQuery.from(SharingPlatform.class);
//			Predicate condition = null;
			List<Predicate> conditions = new ArrayList<Predicate>();
			conditions.add(criteriaBuilder.equal(root.get("parkingInfo").get("id"), parkingInfoId));
			if(sp != null){
//				if(sp.getParkingSpaceId()!= null && !sp.getParkingSpaceId().equals("")){
//					condition = criteriaBuilder.equal(root.get("parkingSpaceId"),sp.getParkingSpaceId());
//				}
			}
//			if(condition != null){
//				criteriaQuery.where(condition);
//			}
			if(conditions != null){
				criteriaQuery.where(conditions.toArray(new Predicate[0]));
			}
			TypedQuery<SharingPlatform> typedQuery = em.createQuery(criteriaQuery);

			
			//分页
			typedQuery.setFirstResult((spPage.getCurrentPageNo()-1)*spPage.getPageSize());
			typedQuery.setMaxResults(spPage.getPageSize());
			
			spList = typedQuery.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return spList;
	}

	
	public int getSharingPlatformCount(SharingPlatform sp,Integer parkingInfoId) {
		int count = 0;
		
		try {
			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
			Root<SharingPlatform> root = criteriaQuery.from(SharingPlatform.class);
//			Predicate condition = null;
			List<Predicate> conditions = new ArrayList<Predicate>();
			conditions.add(criteriaBuilder.equal(root.get("parkingInfo").get("id"), parkingInfoId));
			if(sp != null){
//				if(sp.getParkingSpaceId() != null && !sp.getParkingSpaceId().equals("")){
//					condition = criteriaBuilder.equal(root.get("parkingSpaceId"),sp.getParkingSpaceId());
//				}
			}
//			if(condition != null){
//				criteriaQuery.where(condition);
//			}
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


	//根据id，获取共享信息
	public SharingPlatform getSharingPlatformById(Integer id) {
		SharingPlatform sharingplatform = null;
		
		try {
			sharingplatform =sharingplatformmapper.findOne(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sharingplatform;
	}

	//新增共享信息
	public boolean addSharingPlatform(SharingPlatform psAdd) {
		SharingPlatform sharingplatform = null;
		
		try {
			sharingplatform = sharingplatformmapper.save(psAdd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(sharingplatform != null){
			return true;
		}
		
		return false;
	}

	//新增共享信息
	public SharingPlatform updateSharingPlatform(SharingPlatform psUpdate) {
		SharingPlatform sharingPlatformUpdate = null;
		try {
			//根据id，获取共享信息
			sharingPlatformUpdate =sharingplatformmapper.findOne(psUpdate.getId());
			
			if(psUpdate.getSharingStatus().getId() != null){
				sharingPlatformUpdate.setSharingStatus(psUpdate.getSharingStatus());
			}

			sharingPlatformUpdate = sharingplatformmapper.saveAndFlush(sharingPlatformUpdate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return sharingPlatformUpdate;
	}

	// 新增共享信息
	public boolean delSharingPlatform(Integer id) {
		try {
			//根据id，获取共享信息
			sharingplatformmapper.delete(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}	
	
}
