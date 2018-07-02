package cn.parkmanasys.service.boundofplatenumber;

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

import cn.parkmanasys.dao.BoundOfplateNumberMapper;
import cn.parkmanasys.dao.CarOwnerUserMapper;
import cn.parkmanasys.dao.ParkingAccountMapper;
import cn.parkmanasys.entity.BoundOfPlateNumber;
import cn.parkmanasys.entity.CarOwnerUser;
import cn.parkmanasys.entity.FeedbackAndDispute;
import cn.parkmanasys.entity.ParkingAccount;
import cn.parkmanasys.entity.ParkingDictionary;
import cn.parkmanasys.entity.PlateNumber;
import cn.parkmanasys.util.PageSupport;

@Service
public class BoundOfPlateNumberServiceImpl implements BoundOfPlateNumberService{

	@PersistenceContext
	private EntityManager em;
	@Resource
	private BoundOfplateNumberMapper boundOfplateNumberMapper;
	
	@Override
	public List<BoundOfPlateNumber> getParkingCarOwnerUserByPage(BoundOfPlateNumber pa, PageSupport psPage) {
		 List<BoundOfPlateNumber> paList = null;
			try {
				CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
				CriteriaQuery<BoundOfPlateNumber> criteriaQuery = criteriaBuilder.createQuery(BoundOfPlateNumber.class);
				Root<BoundOfPlateNumber> root = criteriaQuery.from(BoundOfPlateNumber.class);
				Predicate condition = null;			
				/*if(pa != null){
					if(pa.getPlateNumber()!= null && !pa.getCarOwnerUser().getRealName().equals("")){
						condition = criteriaBuilder.like(criteriaBuilder.lower(root.get("RealName")), StringUtils.lowerCase("%"+pa.getCarOwnerUser().getRealName()+"%"));
					}
				}
				if(condition != null){
					criteriaQuery.where(condition);
				}			*/
				TypedQuery<BoundOfPlateNumber> typedQuery = em.createQuery(criteriaQuery);
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
	public int getParkingCarOwnerUserCount(BoundOfPlateNumber pa) {
		 int count = 0;
			try {
				CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
				CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
				Root<ParkingDictionary> root = criteriaQuery.from(ParkingDictionary.class);
				Predicate condition = null;
//				
				/*	if(pa != null){
						if(pa.getCarOwnerUser().getRealName()!= null && !pa.getCarOwnerUser().getRealName().equals("")){
							condition = criteriaBuilder.like(criteriaBuilder.lower(root.get("RealName")), StringUtils.lowerCase("%"+pa.getCarOwnerUser().getRealName()+"%"));
						}
					}
					if(condition != null){
						criteriaQuery.where(condition);
					}
					*/
				criteriaQuery.select(criteriaBuilder.count(root));//查询总数，未去重复
				
				TypedQuery<Long> typedQuery = em.createQuery(criteriaQuery);

				count = new Long(typedQuery.getSingleResult()).intValue();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return count;
	}

	@Override
	public boolean addPlateNumber(BoundOfPlateNumber psAdd) {
		BoundOfPlateNumber boundOfPlateNumber = null;
		
		try {
			boundOfPlateNumber = boundOfplateNumberMapper.save(psAdd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(boundOfPlateNumber != null){
			return true;
		}
		
		return false;
	}

	@Override
	public BoundOfPlateNumber getBoundOfPlateNumberById(Integer id) {
		BoundOfPlateNumber boundOfPlateNumber = null;
		
		try {
			boundOfPlateNumber =boundOfplateNumberMapper.findOne(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return boundOfPlateNumber;
	}

	@Override
	public boolean delCarOwnerUser(Integer id) {
		try {
			boundOfplateNumberMapper.delete(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
