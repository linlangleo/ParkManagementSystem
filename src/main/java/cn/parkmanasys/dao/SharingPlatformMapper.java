package cn.parkmanasys.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.parkmanasys.entity.ParkingDictionary;
import cn.parkmanasys.entity.ParkingSpace;
import cn.parkmanasys.entity.SharingPlatform;


@Repository
public interface SharingPlatformMapper extends JpaRepository<SharingPlatform, Integer>{
	//根据id获取共享信息
	public List<SharingPlatform> findByid(String parkingSpaceId);
	
	
}
