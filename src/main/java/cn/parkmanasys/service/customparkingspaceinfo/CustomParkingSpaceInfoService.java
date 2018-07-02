package cn.parkmanasys.service.customparkingspaceinfo;

import cn.parkmanasys.entity.CustomParkingSpaceInfo;

public interface CustomParkingSpaceInfoService {
	
	//新增自定义停车位信息
	public boolean addOrUpdateCustomParkingSpace(CustomParkingSpaceInfo customParkingSpaceInfo);
	
	//根据id查询自定义停车位信息
	public CustomParkingSpaceInfo getInfoById(Integer parkingId);
}
