package cn.parkmanasys.service.peccancy;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import cn.parkmanasys.dao.ParkingAccountMapper;
import cn.parkmanasys.dao.PeccancyMapper;
import cn.parkmanasys.dao.PlateNumberMapper;
import cn.parkmanasys.entity.CarOwnerUser;
import cn.parkmanasys.entity.ParkingAccount;
import cn.parkmanasys.entity.ParkingSpace;
import cn.parkmanasys.entity.Peccancy;

@Service
public class PeccancyServiceImpl implements PeccancyService {

	@PersistenceContext
	private EntityManager em;
	@Resource
	private PeccancyMapper peccancyMapper;

	@Override
	public List<Peccancy> getAll() {
		List<Peccancy> psList = null;

		try {
			// 根据id，获取停车位信息
			psList = peccancyMapper.getPeccancy();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return psList;
	}

	@Override
	public boolean delPeccancy(Integer id) {
		try {
			peccancyMapper.delete(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Peccancy addParkingCarOwnerUser(Peccancy peccancyAdd) {
		Peccancy peccancy = null;
		
		try {
			peccancy = peccancyMapper.save(peccancyAdd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return peccancy;
	}

}
