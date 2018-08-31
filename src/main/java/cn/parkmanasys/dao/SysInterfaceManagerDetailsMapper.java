package cn.parkmanasys.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.parkmanasys.entity.SysInterfaceManagerDetails;


@Repository
public interface SysInterfaceManagerDetailsMapper extends JpaRepository<SysInterfaceManagerDetails, Integer>{
	/**
	 * 查询所有接口信息
	 * @param Id
	 * @return List
	 */
//	@Query("from #{#entityName} p where p.id = :Id")
//	public SysInterfaceManager findParkingInfoById(@Param("Id")Integer Id);
}
