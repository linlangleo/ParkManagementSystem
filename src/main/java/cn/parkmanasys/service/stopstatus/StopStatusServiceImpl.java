package cn.parkmanasys.service.stopstatus;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.parkmanasys.dao.ParkingAccountMapper;
import cn.parkmanasys.dao.StopStatusMapper;
import cn.parkmanasys.entity.ParkingAccount;
import cn.parkmanasys.entity.StopStatus;

@Service
public class StopStatusServiceImpl implements StopStatusService{
	@Resource
	private StopStatusMapper stopStatusMapper;
	@Override
	/**
	 * 获得全部停车信息
	 * 供下拉框使用
	 */
	public List<StopStatus> getFindAllStopStatusInfo() {
		List<StopStatus> stopList = null;
		try {
			stopList = stopStatusMapper.findStopStatusInfo();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stopList;
	}
	
}
