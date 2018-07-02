package cn.parkmanasys.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.parkmanasys.entity.ParkingInfo;


@Repository
public interface ParkingInfoMapper extends JpaRepository<ParkingInfo, Integer>{
	/**
	 * 查询所有停车场信息
	 * @param Id
	 * @return List
	 */
	@Query("from #{#entityName} p where p.id = :Id")
	public ParkingInfo findParkingInfoById(@Param("Id")Integer Id);
}
