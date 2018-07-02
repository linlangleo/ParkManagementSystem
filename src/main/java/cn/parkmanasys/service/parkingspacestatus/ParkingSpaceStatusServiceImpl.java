package cn.parkmanasys.service.parkingspacestatus;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.parkmanasys.dao.ParkingAccountMapper;
import cn.parkmanasys.dao.ParkingSpaceStatusMapper;
import cn.parkmanasys.entity.ParkingAccount;
import cn.parkmanasys.entity.ParkingSpaceStatus;

@Service
public class ParkingSpaceStatusServiceImpl implements ParkingSpaceStatusService{
	@Resource
	private ParkingSpaceStatusMapper parkingSpaceStatusMapper;
	
	
	//获取所有,停车位状态的信息
	public List<ParkingSpaceStatus> getAllParkingSpaceStatus(){
		List<ParkingSpaceStatus> parkingSpaceStatusList = null;
	
		try {
			parkingSpaceStatusList = parkingSpaceStatusMapper.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return parkingSpaceStatusList;
	}
}
