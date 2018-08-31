package cn.parkmanasys.service.sysinterfacemanager;

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
import org.springframework.transaction.annotation.Transactional;

import cn.parkmanasys.dao.SysInterfaceManagerMapper;
import cn.parkmanasys.entity.ParkingInfo;
import cn.parkmanasys.entity.SysInterfaceManager;
import cn.parkmanasys.util.PageSupport;

/**
 * 接口管理service 实现类
 * @return
 */
@Service
public class SysInterfaceManagerServiceImpl implements SysInterfaceManagerService{
	//实体管理器
	@PersistenceContext
	private EntityManager em;
	
	//引入相关Dao层
	@Resource
	private SysInterfaceManagerMapper sysInterfaceManagerMapper;
	
	/**
	 * 查询接口信息
	 */
	@Override
	@Transactional(readOnly = true)
	public List<SysInterfaceManager> getInterfaceInfo(SysInterfaceManager sim,PageSupport psPage) {
		List<SysInterfaceManager> simList = null;
		try {
			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<SysInterfaceManager> criteriaQuery = criteriaBuilder.createQuery(SysInterfaceManager.class);
			Root<SysInterfaceManager> root = criteriaQuery.from(SysInterfaceManager.class);
			List<Predicate> conditions = new ArrayList<Predicate>();
			
			if(sim != null){//存放条件
//				if(pi.getParkingName() != null && !pi.getParkingName().equals("")){
//					conditions.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("parkingName")), StringUtils.lowerCase("%"+pi.getParkingName()+"%")));
//				}
//				if(pi.getParkingAddress() != null && !pi.getParkingAddress().equals("")){
//					conditions.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("parkingAddress")), StringUtils.lowerCase("%"+pi.getParkingAddress()+"%")));
//				}
			}
			if(conditions != null){
				criteriaQuery.where(conditions.toArray(new Predicate[0]));
			}
			
			TypedQuery<SysInterfaceManager> typedQuery = em.createQuery(criteriaQuery);
	
			
			//分页
//			typedQuery.setFirstResult((psPage.getCurrentPageNo()-1)*psPage.getPageSize());
//			typedQuery.setMaxResults(psPage.getPageSize());
			
			simList = typedQuery.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return simList;
	}

	/**
	 * 新增接口信息
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void addInterfaceInfo(SysInterfaceManager sim) {
		sysInterfaceManagerMapper.saveAndFlush(sim);
	}
	
}
