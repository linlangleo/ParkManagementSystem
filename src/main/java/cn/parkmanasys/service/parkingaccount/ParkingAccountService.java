package cn.parkmanasys.service.parkingaccount;

import java.util.List;

import cn.parkmanasys.entity.ParkingAccount;

public interface ParkingAccountService {
	// 根据用户名获取用户信息
	public ParkingAccount getAccountByName(String accountName);

	public List<ParkingAccount> findAll();

	// 根据id获取账号信息
	public ParkingAccount findOne(Integer id);

	//根据停车场id查询账号对象
	public ParkingAccount getParkingAccountbyId(Integer parkingId);
	
	//修改密码
	public ParkingAccount updateParkingAccountPassword(ParkingAccount park);
	
	
	
	
}
