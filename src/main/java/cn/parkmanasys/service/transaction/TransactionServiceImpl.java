package cn.parkmanasys.service.transaction;

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
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import cn.parkmanasys.dao.ParkingAccountMapper;
import cn.parkmanasys.dao.TransactionMapper;
import cn.parkmanasys.entity.ParkingAccount;
import cn.parkmanasys.entity.ParkingPayment;
import cn.parkmanasys.entity.ParkingSpace;
import cn.parkmanasys.entity.Peccancy;
import cn.parkmanasys.entity.Transaction;
import cn.parkmanasys.util.PageSupport;

@Service
public class TransactionServiceImpl implements TransactionService{
	@PersistenceContext
	/**
	 * 实体管理器
	 */
	private EntityManager em;
	
	@Resource
	/**
	 * 相对应的Dao层
	 */
	public TransactionMapper transactionMapper ;
	
	
	
	@Override
	/**
	 * 按照条件获得交易记录信息
	 */
	public List<Transaction> findTransactionInfo(Transaction transaction,Integer parkingId,PageSupport ppPage) {
		List<Transaction> tList = null;
		try {
			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<Transaction> criteriaQuery = criteriaBuilder.createQuery(Transaction.class);
			Root<Transaction> root = criteriaQuery.from(Transaction.class);
			List<Predicate> conditions = new ArrayList<Predicate>();
			
			conditions.add(criteriaBuilder.equal(root.get("parkingInfo").get("id"), parkingId));
			
/*				if(transaction.getTransactionType().getId() != null && !transaction.getTransactionType().getId().equals("")){
					conditions.add(criteriaBuilder.equal(root.get("transactionType").get("id"), 1));
				}
*/
			
			if(conditions != null){
				criteriaQuery.where(conditions.toArray(new Predicate[0]));
			}
			
			TypedQuery<Transaction> typedQuery = em.createQuery(criteriaQuery);

			
			//分页计算
			typedQuery.setFirstResult((ppPage.getCurrentPageNo()-1)*ppPage.getPageSize());
			typedQuery.setMaxResults(ppPage.getPageSize());
			
			tList = typedQuery.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tList;
	}
	@Override
	/**
	 * 按照条件获得交易记录的总数量
	 */
	public int getTransactionCount(Transaction transaction, Integer parkingInfoId) {
		int count = 0;
		
		try {
			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
			Root<Transaction> root = criteriaQuery.from(Transaction.class);
			List<Predicate> conditions = new ArrayList<Predicate>();

			//确保查询的数据是本停车场的数据,进行停车场信息的判断
			conditions.add(criteriaBuilder.equal(root.get("parkingInfo").get("id"), parkingInfoId));
			

/*				if(transaction.getTransactionType().getId() != null && !transaction.getTransactionType().getId().equals("")){
					conditions.add(criteriaBuilder.equal(root.get("transactionType").get("id"), 1));
				}*/

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
	/**
	 *根据ID查找交易信息
	 */
	public Transaction getTransactionById(Integer id) {
		Transaction transaction = null;
		
		try {
			transaction = transactionMapper.getTransactionById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return transaction;
	}
	@Override
	/**
	 * 修改交易信息
	 */
	public Transaction updateTransaction(Transaction tcUpdate) {
		Transaction transactionUpdate= null;
		
		try {
			//根据id，获取交易记录信息
			transactionUpdate = transactionMapper.findOne(tcUpdate.getId());
			
			if(tcUpdate.getParkingInfo().getParkingName() != null){
				transactionUpdate.setParkingInfo(tcUpdate.getParkingInfo());
			}

			transactionUpdate = transactionMapper.saveAndFlush(transactionUpdate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return transactionUpdate;
	}
	@Override
	/**
	 *删除交易信息
	 */
	public boolean delTransaction(Integer id) {
		try {
			//根据id，获取停车位信息
			transactionMapper.delete(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	/**
	 * 新增交易记录
	 */
	public boolean addTransaction(Transaction tcAdd) {
		Transaction transaction = null;
		
		try {
			transaction = transactionMapper.save(tcAdd);
					} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(transaction != null){
			return true;
		}
		
		return false;
	}
	/*@Override
	public List<Transaction> (@Param("parkingId")Integer parkingId){ 
		List<Transaction> psList = null;

		try {
			psList = transactionMapper.getTransaction(parkingId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return psList;
	}
	@Override
	public List<Transaction>(@Param("parkingId")Integer parkingId) {
		List<Transaction> psList = null;

		try {
			psList = transactionMapper.getTransactionzu(parkingId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return psList;
	}*/
	@Override
	public List<Transaction> getTransaction(Integer parkingId) {
		List<Transaction> psList = null;

		try {
			psList = transactionMapper.getTransaction(parkingId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return psList;
	}
	@Override
	public List<Transaction> getTransactionzu(Integer parkingId) {
		List<Transaction> psList = null;

		try {
			psList = transactionMapper.getTransactionzu(parkingId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return psList;
	}
	
	
	
	
	
	
	
	
	@Override
	public List<Transaction> selectTransaction(@Param("parkingId")Integer parkingId) {
		List<Transaction> psList = null;

		try {
			psList = transactionMapper.selectTransaction(parkingId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return psList;
	}
	@Override
	public List<Transaction> selectspTransaction(@Param("parkingId")Integer parkingId) {
		List<Transaction> psList = null;

		try {
			psList = transactionMapper.selectspTransaction(parkingId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return psList;
	}
	@Override
	public List<Transaction> selectgouTransaction(@Param("parkingId")Integer parkingId) {
		List<Transaction> psList = null;

		try {
			psList = transactionMapper.selectgouTransaction(parkingId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return psList;
	}
	
	@Override
	public List<Transaction> selectgouspTransaction(@Param("parkingId")Integer parkingId) {
		List<Transaction> psList = null;

		try {
			psList = transactionMapper.selectgouspTransaction(parkingId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return psList;
	}
	@Override
	public List<Transaction> selectspStatusTransaction(Integer parkingId, Integer transactionstatusid) {
		List<Transaction> psList = null;

		try {
			psList = transactionMapper.selectspStatusTransaction(parkingId,transactionstatusid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return psList;
	}
	@Override
	public List<Transaction> selectspStatuszuTransaction(Integer parkingId, Integer transactionstatusid) {
		List<Transaction> psList = null;

		try {
			psList = transactionMapper.selectspStatuszuTransaction(parkingId,transactionstatusid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return psList;
	}
}
