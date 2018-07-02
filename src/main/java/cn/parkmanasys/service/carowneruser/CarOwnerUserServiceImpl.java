package cn.parkmanasys.service.carowneruser;

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

import cn.parkmanasys.dao.CarOwnerUserMapper;
import cn.parkmanasys.dao.ParkingAccountMapper;
import cn.parkmanasys.dao.ParkingDictionaryMapper;
import cn.parkmanasys.entity.CarOwnerUser;
import cn.parkmanasys.entity.ParkingAccount;
import cn.parkmanasys.entity.ParkingDictionary;
import cn.parkmanasys.entity.PlateNumber;
import cn.parkmanasys.util.PageSupport;

@Service
public class CarOwnerUserServiceImpl implements CarOwnerUserService{
	
	@PersistenceContext
	private EntityManager em;
	@Resource
//	private ParkingDictionaryMapper parkingDictionaryMapper;
	private CarOwnerUserMapper carOwnerUserMapper;
	
	@Override
	public List<CarOwnerUser> getParkingCarOwnerUserByPage(CarOwnerUser pa, PageSupport psPage) {
		 List<CarOwnerUser> paList = null;
			try {
				CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
				CriteriaQuery<CarOwnerUser> criteriaQuery = criteriaBuilder.createQuery(CarOwnerUser.class);
				Root<CarOwnerUser> root = criteriaQuery.from(CarOwnerUser.class);
				Predicate condition = null;			
				if(pa != null){
					if(pa.getRealName()!= null && !pa.getRealName().equals("")){
						condition = criteriaBuilder.like(criteriaBuilder.lower(root.get("RealName")), StringUtils.lowerCase("%"+pa.getRealName()+"%"));
					}
				}
				if(condition != null){
					criteriaQuery.where(condition);
				}			
				TypedQuery<CarOwnerUser> typedQuery = em.createQuery(criteriaQuery);
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
	public int getParkingCarOwnerUserCount(CarOwnerUser pa) {
		 int count = 0;
			try {
				CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
				CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
				Root<ParkingDictionary> root = criteriaQuery.from(ParkingDictionary.class);
				Predicate condition = null;
//				
				if(pa != null){
					if(pa.getRealName()!= null && !pa.getRealName().equals("")){
						condition = criteriaBuilder.like(criteriaBuilder.lower(root.get("RealName")), StringUtils.lowerCase("%"+pa.getRealName()+"%"));
					}
				}
				if(condition != null){
					criteriaQuery.where(condition);
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
	public CarOwnerUser getParkingCarOwnerUserById(Integer id) {
		return null;
	}

	@Override
	public CarOwnerUser addParkingCarOwnerUser(CarOwnerUser psAdd) {
		CarOwnerUser carOwnerUser = null;
		
		try {
			carOwnerUser = carOwnerUserMapper.save(psAdd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return carOwnerUser;
	}

	@Override
	public CarOwnerUser updateParkingCarOwnerUser(CarOwnerUser carOwnerUserUpdate) {
		CarOwnerUser carOwnerUser = null;

		try {
			carOwnerUser = carOwnerUserMapper.findOne(carOwnerUserUpdate.getId());
			
			if(carOwnerUserUpdate.getUserName() != null){
				carOwnerUser.setUserName(carOwnerUserUpdate.getUserName());
			}
			if(carOwnerUserUpdate.getPassword() != null){
				carOwnerUser.setPassword(carOwnerUserUpdate.getPassword());
			}
			if(carOwnerUserUpdate.getRealName() != null){
				carOwnerUser.setRealName(carOwnerUserUpdate.getRealName());
			}

			carOwnerUser = carOwnerUserMapper.save(carOwnerUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return carOwnerUser;
	}

	@Override
	public boolean delParkingCarOwnerUser(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
