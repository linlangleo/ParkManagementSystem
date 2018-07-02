package cn.parkmanasys.service.sharingplatformauthorization;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import cn.parkmanasys.dao.SharingPlatformAuthorizationMapper;
import cn.parkmanasys.entity.SharingPlatform;
import cn.parkmanasys.entity.SharingPlatformAuthorization;

@Service
public class SharingPlatformAuthorizationServiceImpl implements SharingPlatformAuthorizationService{

	@Resource
	private SharingPlatformAuthorizationMapper sharingPlatformAuthorizationMapper;
	
	
	public SharingPlatformAuthorization addSharingPlatformAuthorization(SharingPlatformAuthorization sa) {
		SharingPlatformAuthorization sharingplatformauthorization = null;

		try {
			
			sharingplatformauthorization = sharingPlatformAuthorizationMapper.save(sa);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sharingplatformauthorization;
	}


	
	public SharingPlatformAuthorization updateSharingPlatformAuthorization(SharingPlatformAuthorization sa) {
		SharingPlatformAuthorization sharingPlatformAuthorizationupdate = null;
		
		try {
			//根据id，获取共享信息
			sharingPlatformAuthorizationupdate =sharingPlatformAuthorizationMapper.findOne(sa.getId());
			sharingPlatformAuthorizationupdate.setIfAvailable("true");
			sharingPlatformAuthorizationupdate = sharingPlatformAuthorizationMapper.save(sharingPlatformAuthorizationupdate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sharingPlatformAuthorizationupdate;
	}



	//根据账号id判断是否存在验证码表
	public boolean getparkingAccountUserId(Integer parkingAccountUserId) {
		try {
			sharingPlatformAuthorizationMapper.findOne(parkingAccountUserId);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
