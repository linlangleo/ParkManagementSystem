package cn.parkmanasys.service.sharingplatformapplication;

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

import org.springframework.stereotype.Service;

import cn.parkmanasys.dao.SharingPlatformApplicationMapper;
import cn.parkmanasys.entity.SharingPlatform;
import cn.parkmanasys.entity.SharingPlatformApplication;
import cn.parkmanasys.util.PageSupport;

@Service
public class SharingPlatformApplicationServiceImpl implements SharingPlatformApplicationService {

	@PersistenceContext
	private EntityManager em;
	@Resource
	private SharingPlatformApplicationMapper sharingPlatformApplicationMapper;

	public boolean addSharingPlatformApplication(SharingPlatformApplication spAdd) {
		SharingPlatformApplication sharingplatformapplication = null;

		try {
			sharingplatformapplication = sharingPlatformApplicationMapper.save(spAdd);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (sharingplatformapplication != null) {
			return true;
		}
		return false;
	}

	public List<SharingPlatformApplication> getSharingPlatformApplication(SharingPlatformApplication sp,
			PageSupport spPage, Integer parkingInfoId) {
		List<SharingPlatformApplication> spList = null;
		try {
			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<SharingPlatformApplication> criteriaQuery = criteriaBuilder
					.createQuery(SharingPlatformApplication.class);
			Root<SharingPlatformApplication> root = criteriaQuery.from(SharingPlatformApplication.class);
			// Predicate condition = null;
			List<Predicate> conditions = new ArrayList<Predicate>();
			conditions.add(criteriaBuilder.equal(root.get("parkingInfo").get("id"), parkingInfoId));
			if (sp != null) {
				// if(sp.getParkingSpaceId()!= null &&
				// !sp.getParkingSpaceId().equals("")){
				// condition =
				// criteriaBuilder.equal(root.get("parkingSpaceId"),sp.getParkingSpaceId());
				// }
			}
			// if(condition != null){
			// criteriaQuery.where(condition);
			// }
			if (conditions != null) {
				criteriaQuery.where(conditions.toArray(new Predicate[0]));
			}
			TypedQuery<SharingPlatformApplication> typedQuery = em.createQuery(criteriaQuery);

			// 分页
			typedQuery.setFirstResult((spPage.getCurrentPageNo() - 1) * spPage.getPageSize());
			typedQuery.setMaxResults(spPage.getPageSize());

			spList = typedQuery.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return spList;
	}

	public int getSharingPlatformApplicationCount(SharingPlatformApplication sp, Integer parkingInfoId) {
		int count = 0;

		try {
			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
			Root<SharingPlatformApplication> root = criteriaQuery.from(SharingPlatformApplication.class);
			// Predicate condition = null;
			List<Predicate> conditions = new ArrayList<Predicate>();
			conditions.add(criteriaBuilder.equal(root.get("parkingInfo").get("id"), parkingInfoId));
			if (sp != null) {
				// if(sp.getParkingSpaceId() != null &&
				// !sp.getParkingSpaceId().equals("")){
				// condition =
				// criteriaBuilder.equal(root.get("parkingSpaceId"),sp.getParkingSpaceId());
				// }
			}
			// if(condition != null){
			// criteriaQuery.where(condition);
			// }
			if (conditions != null) {
				criteriaQuery.where(conditions.toArray(new Predicate[0]));
			}
			criteriaQuery.select(criteriaBuilder.count(root));// 查询总数，未去重复

			TypedQuery<Long> typedQuery = em.createQuery(criteriaQuery);

			count = new Long(typedQuery.getSingleResult()).intValue();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;
	}

	public SharingPlatformApplication getSharingPlatformApplicationById(Integer id) {
		SharingPlatformApplication sharingplatformapplication = null;

		try {
			sharingplatformapplication = sharingPlatformApplicationMapper.findOne(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sharingplatformapplication;
	}

	
	public boolean delSharingPlatformApplication(Integer id) {
		try {
			//根据id，获取共享信息
			sharingPlatformApplicationMapper.delete(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
