package cn.parkmanasys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.parkmanasys.entity.ParkingAccount;
import cn.parkmanasys.entity.StopStatus;


@Repository
public interface StopStatusMapper extends JpaRepository<StopStatus, Integer>{
	@Query("from #{#entityName}")
	public List<StopStatus> findStopStatusInfo();
}
