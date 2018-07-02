package cn.parkmanasys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.parkmanasys.entity.ParkingSpace;



@Repository
public interface ParkingSpaceMapper extends JpaRepository<ParkingSpace, Integer>{
	//根据停车位编号获取停车位信息
	public List<ParkingSpace> findByParkingSpaceNumber(String parkingSpaceNumber);
	
	//根据id，获取停车位信息
	@Query("from #{#entityName} p where p.id = :id")
	public ParkingSpace getParkingSpaceById(@Param("id") Integer id);
	
	//根据停车场id，获取所有停车位信息
	@Query("from #{#entityName} p where p.parkingInfo.id = :parkingInfoId")
	public List<ParkingSpace> getParkingSpaceByParkingId(@Param("parkingInfoId") Integer parkingInfoId);
	
	@Query("from #{#entityName} p where p.parkingInfo.id = :ParkingId")
	public List<ParkingSpace> findParkingSpaceByParkingId(@Param("ParkingId") Integer parkingId);
}