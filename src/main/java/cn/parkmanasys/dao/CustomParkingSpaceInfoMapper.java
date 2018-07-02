package cn.parkmanasys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.parkmanasys.entity.CustomParkingSpaceInfo;
import cn.parkmanasys.entity.ParkingSpace;



@Repository
public interface CustomParkingSpaceInfoMapper extends JpaRepository<CustomParkingSpaceInfo, Integer>{
	//根据停车场id获取自定义车位信息
	@Query("from #{#entityName} c where c.parkingInfo.id = :parkingId")
	public CustomParkingSpaceInfo getInfoByParkingId(@Param("parkingId") Integer parkingId);
	
}