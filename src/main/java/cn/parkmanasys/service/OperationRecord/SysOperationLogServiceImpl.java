package cn.parkmanasys.service.OperationRecord;

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

import cn.parkmanasys.dao.SysOperationLogMapper;
import cn.parkmanasys.entity.SysOperationLog;
import cn.parkmanasys.entity.ParkingSpace;
import cn.parkmanasys.util.PageSupport;
@Service
public class SysOperationLogServiceImpl implements SysOperationLogService {
	@PersistenceContext
	private EntityManager em;
	
	@Resource
	private SysOperationLogMapper sysOperationLogMapper;

	@Override
	public List<SysOperationLog> findByParkingAccountId(SysOperationLog record,PageSupport psPage,Integer parkingId) {
		List<SysOperationLog> oList = null;
		
		try {
			
			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<SysOperationLog> criteriaQuery = criteriaBuilder.createQuery(SysOperationLog.class);
			Root<SysOperationLog> root = criteriaQuery.from(SysOperationLog.class);
			List<Predicate> conditions = new ArrayList<Predicate>();
			
			conditions.add(criteriaBuilder.equal(root.get("parkingAccount").get("id"), parkingId));
			
			if(record != null){

			}
			if(conditions != null){
				criteriaQuery.where(conditions.toArray(new Predicate[0]));
			}
			
			TypedQuery<SysOperationLog> typedQuery = em.createQuery(criteriaQuery);
			
			//分页计算
			typedQuery.setFirstResult((psPage.getCurrentPageNo()-1)*psPage.getPageSize());
			typedQuery.setMaxResults(psPage.getPageSize());
			
			oList = typedQuery.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return oList;
	}

	@Override
	public int getOperationRecordCount(SysOperationLog record,Integer parkingId) {
		int count = 0;
		
		try {
			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
			Root<SysOperationLog> root = criteriaQuery.from(SysOperationLog.class);
			List<Predicate> conditions = new ArrayList<Predicate>();
			
			conditions.add(criteriaBuilder.equal(root.get("parkingAccount").get("id"), parkingId));
			
			if(record != null){

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
	public boolean addOperationRecord(SysOperationLog orAdd) {
		SysOperationLog operationRecord = null;
		
		try {
			operationRecord = sysOperationLogMapper.save(orAdd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(operationRecord != null){
			return true;
		}
		
		return false;
	}
	
	/**
	 * 插入操作记录
	 * @param soLog
	 * @return
	 */
	@Override
	public boolean addOperationLog(SysOperationLog soLog) {
		
		return false;
	}

}
