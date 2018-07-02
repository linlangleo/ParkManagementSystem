package cn.parkmanasys.service.car;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import cn.parkmanasys.dao.CarMapper;
import cn.parkmanasys.dao.ParkingAccountMapper;
import cn.parkmanasys.dao.PeccancyMapper;
import cn.parkmanasys.entity.Car;
import cn.parkmanasys.entity.ParkingAccount;
import cn.parkmanasys.entity.Peccancy;

@Service
public class CarServiceImpl implements CarService{

	@PersistenceContext
	private EntityManager em;
	@Resource
	private CarMapper carMapper;
	
	@Override
	public List<Car> getCar() {
		List<Car> psList = null;

		try {
			// 根据id，获取停车位信息
			psList = carMapper.getCar();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return psList;
	}
	
}
