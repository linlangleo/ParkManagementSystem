package cn.parkmanasys.service.carowneruser;

import java.util.List;

import cn.parkmanasys.entity.CarOwnerUser;
import cn.parkmanasys.util.PageSupport;

public interface CarOwnerUserService {
	    //根据分页和条件，获得所有账号信息
		public List<CarOwnerUser> getParkingCarOwnerUserByPage(CarOwnerUser pa, PageSupport psPage);

		//根据条件，获得所有账号信息
		public int getParkingCarOwnerUserCount(CarOwnerUser pa);
		
		//根据id，获取车主
		public CarOwnerUser getParkingCarOwnerUserById(Integer id);
			
		//新增账号信息
		public CarOwnerUser addParkingCarOwnerUser(CarOwnerUser psAdd);

		//修改账号信息
		public CarOwnerUser updateParkingCarOwnerUser(CarOwnerUser psUpdate);
			
		//删除账号信息
		public boolean delParkingCarOwnerUser(Integer id);
}
