package cn.parkmanasys.service.transaction;

import java.util.List;

import org.springframework.data.repository.query.Param;

import cn.parkmanasys.entity.Transaction;
import cn.parkmanasys.util.PageSupport;

public interface TransactionService {
	//获得所有交易记录并实现分页
	public List<Transaction> findTransactionInfo(Transaction transaction,Integer parkingId,PageSupport ppPage);
	//根据条件，获得所有交易记录数据，数量
	public int getTransactionCount(Transaction transaction,Integer parkingInfoId);
	
	//根据id，获取获得所有交易记录信息
	public Transaction getTransactionById(Integer id);
	
	//新增交易信息
	public boolean addTransaction(Transaction tcAdd);

	//修改交易记录
	public Transaction updateTransaction(Transaction tcUpdate);
	
	//删除交易记录
	public boolean delTransaction(Integer id);
	
	public List<Transaction> getTransaction(@Param("parkingId")Integer parkingId);
	
	public List<Transaction> getTransactionzu(@Param("parkingId")Integer parkingId);
	
	//操作
	
	//租用车位交易记录
	public List<Transaction> selectTransaction(@Param("parkingId")Integer parkingId);
	
	//租用车位审批记录
	public List<Transaction> selectspTransaction(@Param("parkingId")Integer parkingId);
	
	//购买车位交易记录
	public List<Transaction> selectgouTransaction(@Param("parkingId")Integer parkingId);
	
	//购买车位审批记录
	public List<Transaction> selectgouspTransaction(@Param("parkingId")Integer parkingId);
	
	//购买车位审批状态记录
	public List<Transaction> selectspStatusTransaction(@Param("parkingId") Integer parkingId,@Param("transactionstatusid") Integer transactionstatusid);
	
	public List<Transaction> selectspStatuszuTransaction(@Param("parkingId") Integer parkingId,@Param("transactionstatusid") Integer transactionstatusid);
}
