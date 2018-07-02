package cn.parkmanasys.service.sharingflatform;

import java.util.List;

import cn.parkmanasys.entity.ParkingDictionary;
import cn.parkmanasys.entity.ParkingSpace;
import cn.parkmanasys.entity.SharingPlatform;
import cn.parkmanasys.entity.SharingStatus;
import cn.parkmanasys.util.PageSupport;

public interface SharingPlatformService {
	//根据分页获取共享信息
	public List<SharingPlatform> getSharingPlatform(SharingPlatform sp,PageSupport spPage,Integer parkingInfoId);
	
	//根据条件，获得所有的共享数据，数量
	public int getSharingPlatformCount(SharingPlatform sp,Integer parkingInfoId);
	
	//根据id，获取共享信息
	public SharingPlatform getSharingPlatformById(Integer id);
		
	//新增共享信息
	public boolean addSharingPlatform(SharingPlatform psAdd);

	//新增共享信息
	public SharingPlatform updateSharingPlatform(SharingPlatform psUpdate);

	//新增共享信息
	public boolean delSharingPlatform(Integer id);
	

	
}
