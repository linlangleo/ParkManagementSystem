package cn.parkmanasys.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cn.parkmanasys.entity.ParkingTemporaryInfo;
/**
 * 停车场信息修改 
 */
@Repository
public interface ParkingTemporaryInfoMapper extends JpaRepository<ParkingTemporaryInfo,Integer> {
		
}
