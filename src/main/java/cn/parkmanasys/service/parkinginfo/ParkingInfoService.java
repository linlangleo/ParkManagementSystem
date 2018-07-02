package cn.parkmanasys.service.parkinginfo;

import java.util.List;

import cn.parkmanasys.entity.ParkingInfo;
import cn.parkmanasys.entity.ParkingTemporaryInfo;

import cn.parkmanasys.util.PageSupport;

public interface ParkingInfoService {
	//根据条件，获得所有的停车场信息，并实现分页
	public List<ParkingInfo> findParkingInfoById(ParkingInfo pi,PageSupport ps);
	
	//根据条件，获得所有的停车场信息，数量
	public int getParkingInfoCount(ParkingInfo pi);

	//审批通过之后修改
	public ParkingInfo updateParkingInfo(ParkingTemporaryInfo parkUp);
	
	//根据id，获取停车场信息
	public ParkingInfo getParkingInfoById(Integer id);
	
	//新增停车场
	public boolean addParkingInfo(ParkingInfo piAdd);

	//修改停车场
	public ParkingInfo updateParkingInfo(ParkingInfo piUpdate);
	
	//删除停车场
	public boolean delParkingInfo(Integer id);
	
}
