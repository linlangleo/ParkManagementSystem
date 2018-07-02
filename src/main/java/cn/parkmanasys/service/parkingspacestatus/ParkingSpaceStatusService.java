package cn.parkmanasys.service.parkingspacestatus;

import java.util.List;

import cn.parkmanasys.entity.ParkingSpaceStatus;

public interface ParkingSpaceStatusService {	
	//获取所有,停车位状态的信息
	public List<ParkingSpaceStatus> getAllParkingSpaceStatus();
}
