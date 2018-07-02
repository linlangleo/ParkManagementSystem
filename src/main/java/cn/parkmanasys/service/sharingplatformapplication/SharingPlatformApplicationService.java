package cn.parkmanasys.service.sharingplatformapplication;

import java.util.List;

import cn.parkmanasys.entity.SharingPlatform;
import cn.parkmanasys.entity.SharingPlatformApplication;
import cn.parkmanasys.util.PageSupport;

public interface SharingPlatformApplicationService {
	// 新增共享信息
	public boolean addSharingPlatformApplication(SharingPlatformApplication spAdd);

	// 根据分页获取共享信息
	public List<SharingPlatformApplication> getSharingPlatformApplication(SharingPlatformApplication sp, PageSupport spPage,Integer parkingInfoId);

	// 根据条件，获得所有的共享数据，数量
	public int getSharingPlatformApplicationCount(SharingPlatformApplication sp,Integer parkingInfoId);

	// 根据id，获取共享信息
	public SharingPlatformApplication getSharingPlatformApplicationById(Integer id);
	
	//共享拒绝
	public boolean delSharingPlatformApplication(Integer id);
	
}

