package cn.parkmanasys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cn.parkmanasys.entity.ParkingSpaceStatus;


@Repository
public interface ParkingSpaceStatusMapper extends JpaRepository<ParkingSpaceStatus, Integer>{
	
}
