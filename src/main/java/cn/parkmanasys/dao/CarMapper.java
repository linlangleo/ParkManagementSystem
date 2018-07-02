package cn.parkmanasys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.parkmanasys.entity.Car;
import cn.parkmanasys.entity.ParkingAccount;
import cn.parkmanasys.entity.Peccancy;


@Repository
public interface CarMapper extends JpaRepository<Car, Integer>{
	@Query(value = "select * from  Car  ", nativeQuery = true)
	public List<Car> getCar();
}
