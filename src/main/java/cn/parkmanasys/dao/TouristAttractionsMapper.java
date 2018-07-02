package cn.parkmanasys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.parkmanasys.entity.ParkingAccount;


@Repository
public interface TouristAttractionsMapper extends JpaRepository<ParkingAccount, Integer>{
	
}
