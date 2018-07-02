package cn.parkmanasys.service.boundofplatenumber;

import java.util.List;

import cn.parkmanasys.entity.BoundOfPlateNumber;
import cn.parkmanasys.entity.CarOwnerUser;
import cn.parkmanasys.entity.FeedbackAndDispute;
import cn.parkmanasys.util.PageSupport;

public interface BoundOfPlateNumberService {
	// 根据分页和条件，获得所有账号信息
	public List<BoundOfPlateNumber> getParkingCarOwnerUserByPage(BoundOfPlateNumber pa, PageSupport psPage);

	// 根据条件，获得所有账号信息
	public int getParkingCarOwnerUserCount(BoundOfPlateNumber pa);

	// 根据id，获取账号信息
	public BoundOfPlateNumber getBoundOfPlateNumberById(Integer id);

	public boolean addPlateNumber(BoundOfPlateNumber psAdd);

	public boolean delCarOwnerUser(Integer id);
	
	

}
