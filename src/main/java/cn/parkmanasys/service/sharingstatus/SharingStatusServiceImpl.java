package cn.parkmanasys.service.sharingstatus;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.parkmanasys.dao.ParkingAccountMapper;
import cn.parkmanasys.dao.SharingStatusMapper;
import cn.parkmanasys.entity.ParkingAccount;
import cn.parkmanasys.entity.ParkingDictionary;
import cn.parkmanasys.entity.ParkingSpaceStatus;
import cn.parkmanasys.entity.SharingStatus;

@Service
public class SharingStatusServiceImpl implements SharingStatusService{

	@Resource
	private SharingStatusMapper sharingStatusMapper;

	@Override
	public List<SharingStatus> getAllSharingStatus() {
		List<SharingStatus> sharingStatusList = null;
		
		try {
			sharingStatusList = sharingStatusMapper.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return sharingStatusList;
	}
}
	
