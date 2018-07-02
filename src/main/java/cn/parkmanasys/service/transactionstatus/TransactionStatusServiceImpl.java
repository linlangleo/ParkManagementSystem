package cn.parkmanasys.service.transactionstatus;

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

import cn.parkmanasys.dao.ParkingAccountMapper;
import cn.parkmanasys.dao.TransactionStatusMapper;
import cn.parkmanasys.dao.TransactionTypeMapper;
import cn.parkmanasys.entity.ParkingAccount;
import cn.parkmanasys.entity.TransactionStatus;
import cn.parkmanasys.entity.TransactionType;

@Service
public class TransactionStatusServiceImpl implements TransactionStatusService{
	// 实体管理器
	@PersistenceContext
	private EntityManager em;

	// 引入相关DAO
	@Resource
	private TransactionStatusMapper transactionStatusMapper;
	@Override
	public List<TransactionStatus> findTransactionStatusInfo() {
		List<TransactionStatus> tsList = null;
		try {
			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<TransactionStatus> criteriaQuery = criteriaBuilder.createQuery(TransactionStatus.class);
			Root<TransactionStatus> root = criteriaQuery.from(TransactionStatus.class);
			List<Predicate> conditions = new ArrayList<Predicate>();

			/*
			 * if(pi != null){//存放条件 if(pi.getParkingName() != null &&
			 * !pi.getParkingName().equals("")){
			 * conditions.add(criteriaBuilder.like(criteriaBuilder.lower(root.
			 * get("parkingName")),
			 * StringUtils.lowerCase("%"+pi.getParkingName()+"%"))); } }
			 */
			if (conditions != null) {
				criteriaQuery.where(conditions.toArray(new Predicate[0]));
			}

			TypedQuery<TransactionStatus> typedQuery = em.createQuery(criteriaQuery);

			// 分页
			/*
			 * typedQuery.setFirstResult((psPage.getCurrentPageNo()-1)*psPage.
			 * getPageSize()); typedQuery.setMaxResults(psPage.getPageSize());
			 */

			tsList = typedQuery.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return tsList;
	}
	
}
