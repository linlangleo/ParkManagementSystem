package cn.parkmanasys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.parkmanasys.entity.ParkingAccount;
import cn.parkmanasys.entity.Transaction;
import cn.parkmanasys.entity.TransactionStatusInfo;


@Repository
public interface TransactionStatusInfoMapper extends JpaRepository<TransactionStatusInfo, Integer>{
	/*@Query(value = "update transactionstatusinfo set transactionstatusid=5 where id=2	", nativeQuery = true)
	public TransactionStatusInfo updateTransaction(TransactionStatusInfo TransactionUpdate);*/
}
