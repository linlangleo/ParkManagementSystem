package cn.parkmanasys.service.sharingplatformauthorization;


import cn.parkmanasys.entity.SharingPlatformAuthorization;

public interface SharingPlatformAuthorizationService {
	//添加通过
	public SharingPlatformAuthorization addSharingPlatformAuthorization(SharingPlatformAuthorization sa);
	
	//修改通过
	public SharingPlatformAuthorization updateSharingPlatformAuthorization(SharingPlatformAuthorization sa);
	
	//根据账号id判断是否存在验证码表
	public boolean getparkingAccountUserId(Integer parkingAccountUserId);
}
