package cn.parkmanasys.service.stopstatus;

import java.util.List;

import cn.parkmanasys.entity.StopStatus;

public interface StopStatusService {
	/**
	 * 获得全部停车状态信息
	 * @return
	 */
	public List<StopStatus> getFindAllStopStatusInfo();
}
