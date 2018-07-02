package cn.parkmanasys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.parkmanasys.entity.ParkingAccount;
import cn.parkmanasys.entity.ParkingSpace;
import cn.parkmanasys.entity.PlateNumber;


@Repository
public interface PlateNumberMapper extends JpaRepository<PlateNumber, Integer>{
	
	
	
}
