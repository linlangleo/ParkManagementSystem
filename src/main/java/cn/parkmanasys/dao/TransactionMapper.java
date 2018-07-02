package cn.parkmanasys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.parkmanasys.entity.Peccancy;
import cn.parkmanasys.entity.PlateNumber;
import cn.parkmanasys.entity.Transaction;

@Repository
public interface TransactionMapper extends JpaRepository<Transaction, Integer> {
	// 根据停车场ID获得本停车场的交易记录
	@Query("from #{#entityName} t where t.parkingInfo.id = :ParkingId")
	public List<Transaction> findTransactionInfo();

	// 根据ID获得停车记录
	@Query("from #{#entityName} t where t.id = :id")
	public Transaction getTransactionById(@Param("id") Integer id);

	/*
	 * @Query("from #{#entityName} t where t.parkingInfo.id = :ParkingId and transactionTypeId=2"
	 * )
	 */
	@Query(value = "select * from Transaction where parkingId=:parkingId and transactionTypeId=2", nativeQuery = true)
	public List<Transaction> getTransaction(@Param("parkingId") Integer parkingId);

	@Query(value = "select * from Transaction where parkingId=:parkingId and transactionTypeId=1", nativeQuery = true)
	public List<Transaction> getTransactionzu(@Param("parkingId") Integer parkingId);

	// 操作

	// 租用车位交易记录
	@Query(value = "select * from TRANSACTION t inner join TRANSACTIONSTATUSINFO r on t.id=r.transactionid where parkingId=:parkingId and transactiontypeid=1 and r.transactionstatusid <> 2 ", nativeQuery = true)
	public List<Transaction> selectTransaction(@Param("parkingId") Integer parkingId);

	// 租用车位审批记录
	@Query(value = "select * from TRANSACTION t inner join TRANSACTIONSTATUSINFO r on t.id=r.transactionid where parkingId=:parkingId and transactiontypeid=1 and r.transactionstatusid =2", nativeQuery = true)
	public List<Transaction> selectspTransaction(@Param("parkingId") Integer parkingId);

	// 购买车位交易记录
	@Query(value = "select * from TRANSACTION t inner join TRANSACTIONSTATUSINFO r on t.id=r.transactionid where parkingId=:parkingId and transactiontypeid=2 and r.transactionstatusid <> 2 ", nativeQuery = true)
	public List<Transaction> selectgouTransaction(@Param("parkingId") Integer parkingId);

	// 购买车位审批记录
	@Query(value = "select * from TRANSACTION t inner join TRANSACTIONSTATUSINFO r on t.id=r.transactionid where parkingId=:parkingId and transactiontypeid=2 and r.transactionstatusid =2", nativeQuery = true)
	public List<Transaction> selectgouspTransaction(@Param("parkingId") Integer parkingId);

	// 购买车位审批状态记录
	@Query(value = "select * from TRANSACTION t inner join TRANSACTIONSTATUSINFO r on t.id=r.transactionid where parkingId=:parkingId and transactiontypeid=2 and r.transactionstatusid =:transactionstatusid", nativeQuery = true)
	public List<Transaction> selectspStatusTransaction(@Param("parkingId") Integer parkingId,@Param("transactionstatusid") Integer transactionstatusid);
	
	// 租用车位审批状态记录
		@Query(value = "select * from TRANSACTION t inner join TRANSACTIONSTATUSINFO r on t.id=r.transactionid where parkingId=:parkingId and transactiontypeid=1 and r.transactionstatusid =:transactionstatusid", nativeQuery = true)
		public List<Transaction> selectspStatuszuTransaction(@Param("parkingId") Integer parkingId,@Param("transactionstatusid") Integer transactionstatusid);
}
