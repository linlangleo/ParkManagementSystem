package cn.parkmanasys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.parkmanasys.entity.ParkingAccount;
import cn.parkmanasys.entity.Peccancy;
import cn.parkmanasys.entity.PlateNumber;


@Repository
public interface PeccancyMapper extends JpaRepository<Peccancy, Integer>{
	@Query(value = "select * from  Peccancy  ", nativeQuery = true)
	public List<Peccancy> getPeccancy();
}
