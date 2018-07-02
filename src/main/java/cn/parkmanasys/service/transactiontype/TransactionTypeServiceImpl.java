package cn.parkmanasys.service.transactiontype;

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

import cn.parkmanasys.dao.TransactionTypeMapper;
import cn.parkmanasys.entity.TransactionType;

@Service
public class TransactionTypeServiceImpl implements TransactionTypeService {
	// 实体管理器
	@PersistenceContext
	private EntityManager em;

	// 引入相关DAO
	@Resource
	private TransactionTypeMapper transactionTypeMapper;

	@Override
	public List<TransactionType> findTransactionTypeInfo() {
		List<TransactionType> ttList = null;
		try {
			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<TransactionType> criteriaQuery = criteriaBuilder.createQuery(TransactionType.class);
			Root<TransactionType> root = criteriaQuery.from(TransactionType.class);
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

			TypedQuery<TransactionType> typedQuery = em.createQuery(criteriaQuery);

			// 分页
			/*
			 * typedQuery.setFirstResult((psPage.getCurrentPageNo()-1)*psPage.
			 * getPageSize()); typedQuery.setMaxResults(psPage.getPageSize());
			 */

			ttList = typedQuery.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ttList;
	}

}
