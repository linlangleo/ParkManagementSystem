package cn.parkmanasys.service.sharingstatus;

import java.util.List;

import cn.parkmanasys.entity.SharingStatus;

public interface SharingStatusService {
	//根据id获取共享状态
	public List<SharingStatus> getAllSharingStatus();
}
