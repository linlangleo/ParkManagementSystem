package cn.parkmanasys.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.parkmanasys.entity.CarOwnerUser;


@Repository
public interface CarOwnerUserMapper extends JpaRepository<CarOwnerUser, Integer>{
	/**
	 * 根据账号密码获取车主信息
	 * @param Id
	 * @return List
	 */
	@Query("from #{#entityName} c where c.phone = :phone and c.password = :password")
	public CarOwnerUser findCarOwnerById(@Param("phone")String name, @Param("password")String password);
}
