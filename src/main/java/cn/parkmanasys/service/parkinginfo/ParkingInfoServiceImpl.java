package cn.parkmanasys.service.parkinginfo;

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

import cn.parkmanasys.dao.ParkingInfoMapper;
import cn.parkmanasys.entity.DistrictOrCounty;
import cn.parkmanasys.entity.ParkingInfo;
import cn.parkmanasys.entity.ParkingSpace;
import cn.parkmanasys.util.PageSupport;
import cn.parkmanasys.entity.ParkingTemporaryInfo;
import cn.parkmanasys.entity.Street;

@Service
public class ParkingInfoServiceImpl implements ParkingInfoService{
	//实体管理器
	@PersistenceContext
	private EntityManager em;
	
	//引入相关Dao层
	@Resource
	private ParkingInfoMapper parkingInfoMapper;
	
	/**
	 * 按照条件查询所有停车场信息并实现分页
	 */
	@Override
	public List<ParkingInfo> findParkingInfoById(ParkingInfo pi,PageSupport psPage) {
		List<ParkingInfo> piList = null;
		try {
			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<ParkingInfo> criteriaQuery = criteriaBuilder.createQuery(ParkingInfo.class);
			Root<ParkingInfo> root = criteriaQuery.from(ParkingInfo.class);
			List<Predicate> conditions = new ArrayList<Predicate>();
			
			if(pi != null){//存放条件
				if(pi.getParkingName() != null && !pi.getParkingName().equals("")){
					conditions.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("parkingName")), StringUtils.lowerCase("%"+pi.getParkingName()+"%")));
				}
				if(pi.getParkingAddress() != null && !pi.getParkingAddress().equals("")){
					conditions.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("parkingAddress")), StringUtils.lowerCase("%"+pi.getParkingAddress()+"%")));
				}
			}
			if(conditions != null){
				criteriaQuery.where(conditions.toArray(new Predicate[0]));
			}
			
			TypedQuery<ParkingInfo> typedQuery = em.createQuery(criteriaQuery);
	
			
			//分页
			typedQuery.setFirstResult((psPage.getCurrentPageNo()-1)*psPage.getPageSize());
			typedQuery.setMaxResults(psPage.getPageSize());
			
			piList = typedQuery.getResultList();
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	return piList;
	}
	
	/**
	 * 获得用于分页的总数量
	 * 返回一个整型值
	 */
	@Override
	public int getParkingInfoCount(ParkingInfo pi) {
		int count = 0;
		
		try {
			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
			Root<ParkingInfo> root = criteriaQuery.from(ParkingInfo.class);
			List<Predicate> conditions = new ArrayList<Predicate>();
			
			if(pi != null){//存放条件
				if(pi.getParkingName() != null && !pi.getParkingName().equals("")){
					conditions.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("parkingName")), StringUtils.lowerCase("%"+pi.getParkingName()+"%")));
				}
				if(pi.getParkingAddress() != null && !pi.getParkingAddress().equals("")){
					conditions.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("parkingAddress")), StringUtils.lowerCase("%"+pi.getParkingAddress()+"%")));
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
	public ParkingInfo getParkingInfoById(Integer id) {
		ParkingInfo parkingInfo = null;
		
		try {
			parkingInfo = parkingInfoMapper.findParkingInfoById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return parkingInfo;
	}

	@Override
	public boolean addParkingInfo(ParkingInfo piAdd) {
		ParkingInfo parkingInfo = new ParkingInfo();
		try {
			DistrictOrCounty districtOrCounty = new DistrictOrCounty();
			districtOrCounty.setDistrictOrCountyName(piAdd.getDistrictOrCounty().getDistrictOrCountyName());
			piAdd.setDistrictOrCounty(districtOrCounty);
			parkingInfo = parkingInfoMapper.save(piAdd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(parkingInfo != null){
			return true;
		}
		
		return false;
	}

	@Override
	public ParkingInfo updateParkingInfo(ParkingInfo piUpdate) {
		ParkingInfo parkingInfoUpdate= null;
		
		try {
			parkingInfoUpdate = parkingInfoMapper.findOne(piUpdate.getId());
			if(piUpdate.getParkingName() != null){
				parkingInfoUpdate.setParkingName(piUpdate.getParkingName());
			}
			parkingInfoUpdate = parkingInfoMapper.saveAndFlush(parkingInfoUpdate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return parkingInfoUpdate;
	}

	@Override
	public boolean delParkingInfo(Integer id) {
		try {
			parkingInfoMapper.delete(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public ParkingInfo updateParkingInfo(ParkingTemporaryInfo parkUp) {
		// TODO Auto-generated method stub
		ParkingInfo parkingInfoUpdate= null;
		try {
			parkingInfoUpdate = parkingInfoMapper.findOne(parkUp.getParkingInfo().getId());
			if(parkUp.getParkingInfo() != null){
				parkingInfoUpdate.setParkingName(parkUp.getParkingName());
				parkingInfoUpdate.setParkingAddress(parkUp.getParkingAddress());
				parkingInfoUpdate.setParkingSpaceCount(parkUp.getParkingSpaceCount());
			}
			parkingInfoUpdate = parkingInfoMapper.saveAndFlush(parkingInfoUpdate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return parkingInfoUpdate;
	}
}
