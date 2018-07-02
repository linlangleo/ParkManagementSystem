package cn.parkmanasys.service.customparkingspaceinfo;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.parkmanasys.dao.CustomParkingSpaceInfoMapper;
import cn.parkmanasys.entity.CustomParkingSpaceInfo;
import cn.parkmanasys.entity.ParkingAccount;

@Service
public class CustomParkingSpaceInfoServiceImpl implements CustomParkingSpaceInfoService{
	@Resource
	private CustomParkingSpaceInfoMapper customParkingSpaceInfoMapper;
	

	//新增自定义停车位信息
	public boolean addOrUpdateCustomParkingSpace(CustomParkingSpaceInfo customParkingSpaceInfo){
		CustomParkingSpaceInfo customParkingSpaceInfoSave = null;
		
		try {
			//先去判断是否存在信息
			customParkingSpaceInfoSave = customParkingSpaceInfoMapper.getInfoByParkingId(customParkingSpaceInfo.getParkingInfo().getId());
			
			if(customParkingSpaceInfoSave != null && customParkingSpaceInfoSave.getId() != null){//如果存在，就修改
				customParkingSpaceInfoSave.setCreationDate(new Date());
				customParkingSpaceInfoSave.setParkingSpaceInfo(customParkingSpaceInfo.getParkingSpaceInfo());
				
				customParkingSpaceInfoSave = customParkingSpaceInfoMapper.saveAndFlush(customParkingSpaceInfoSave);
			}else{//否则新增
				customParkingSpaceInfoSave = null;
				customParkingSpaceInfoSave = customParkingSpaceInfoMapper.save(customParkingSpaceInfo);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(customParkingSpaceInfoSave != null){
			return true;
		}
		
		return false;
	}
	

	//根据id查询自定义停车位信息
	public CustomParkingSpaceInfo getInfoById(Integer parkingId){
		CustomParkingSpaceInfo customParkingSpaceInfo = null;
		
		try {
			customParkingSpaceInfo = customParkingSpaceInfoMapper.getInfoByParkingId(parkingId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return customParkingSpaceInfo;
	}
}
