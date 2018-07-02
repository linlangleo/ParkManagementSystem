package cn.parkmanasys.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cn.parkmanasys.entity.ParkingAccount;


@Repository
public interface AccountTypeMapper extends JpaRepository<ParkingAccount, Integer>{
	
}
