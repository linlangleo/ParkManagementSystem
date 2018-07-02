package cn.parkmanasys.service.parkingaccount;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.parkmanasys.dao.ParkingAccountMapper;
import cn.parkmanasys.entity.ParkingAccount;

@Service
public class ParkingAccountServiceImpl implements ParkingAccountService{
	@Resource
	public ParkingAccountMapper userMapper;
	
	//根据用户名获取用户信息
	public ParkingAccount getAccountByName(String accountName){
		ParkingAccount user = null;
		
		try {
			user = userMapper.getAccountByName(accountName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	public List<ParkingAccount> findAll(){
		List<ParkingAccount> user = null;	
		
		try {
			user = userMapper.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	//根据id获取账号信息
	@Override
	public ParkingAccount findOne(Integer id) {
		// TODO Auto-generated method stub
			ParkingAccount park=null;
				try{
				park=userMapper.findOne(id);
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
		return park;
	}

	//根据停车场id查询账号对象
	public ParkingAccount getParkingAccountbyId(Integer parkingId) {
		ParkingAccount parkingaccout=null;
		try {
			parkingaccout=userMapper.findOne(parkingId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return parkingaccout;
	}
	//修改密码
	@Override
	public ParkingAccount updateParkingAccountPassword(ParkingAccount park) {
		// TODO Auto-generated method stub
		ParkingAccount parkingaccout=null;
		try{
			parkingaccout=userMapper.saveAndFlush(park);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return parkingaccout;
	}
}
