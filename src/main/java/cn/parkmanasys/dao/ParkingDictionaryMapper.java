package cn.parkmanasys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.parkmanasys.entity.ParkingAccount;
import cn.parkmanasys.entity.ParkingDictionary;
import cn.parkmanasys.entity.ParkingSpace;


@Repository
public interface ParkingDictionaryMapper extends JpaRepository<ParkingDictionary, Integer>{

	/*@Query("from #{#entityName} p where p.parkingInfo.id = :ParkingId")*/
/*	public List<ParkingDictionary> findByParkingId(@Param("ParkingId") String ParkingId);*/
	
	//根据停车场id获取,字典表的信息
	@Query("from #{#entityName} p where p.parkingInfo.id = :ParkingId")
	public List<ParkingDictionary> findParkingDictionaryByParkingId(@Param("ParkingId") Integer parkingId);
}
