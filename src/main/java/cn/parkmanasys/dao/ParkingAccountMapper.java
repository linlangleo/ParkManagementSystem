package cn.parkmanasys.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.parkmanasys.entity.FeedbackAndDispute;
import cn.parkmanasys.entity.ParkingAccount;

@Repository
public interface ParkingAccountMapper extends JpaRepository<ParkingAccount, Integer> {

	@Query("from #{#entityName} p where p.accountName = :accountName")
	public ParkingAccount getAccountByName(@Param("accountName") String accountName);

	// 根据停车场id，获取账号信息
	@Query("from #{#entityName} p where p.parkingInfo.id = :parkingId")
	public ParkingAccount getParkingAccountById(@Param("parkingId") Integer parkingId);
}
