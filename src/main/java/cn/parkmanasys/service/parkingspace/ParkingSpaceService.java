package cn.parkmanasys.service.parkingspace;

import java.util.List;

import cn.parkmanasys.entity.ParkingSpace;
import cn.parkmanasys.util.PageSupport;

public interface ParkingSpaceService {
	//根据分页和条件，获得所有的停车位
	public List<ParkingSpace> getParkingSpaceByPage(ParkingSpace ps, PageSupport psPage, Integer parkingInfoId);
	
	//根据条件，获得所有的停车位数据，数量
	public int getParkingSpaceCount(ParkingSpace ps, Integer parkingInfoId);
	
	//根据id，获取停车位信息
	public ParkingSpace getParkingSpaceById(Integer id);
	
	//新增停车位
	public boolean addParkingSpace(ParkingSpace psAdd);

	//修改停车位
	public ParkingSpace updateParkingSpace(ParkingSpace psUpdate);
	
	//删除停车位
	public boolean delParkingSpace(Integer id);
	
	//获取本停车场，所有的停车位信息
	public List<ParkingSpace> getAllParkingSpace(Integer parkingInfoId);
	
	public List<ParkingSpace> getParkingSpaceByParkingId(Integer parkingId);
}
