package cn.parkmanasys.service.platenumber;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import cn.parkmanasys.dao.ParkingAccountMapper;
import cn.parkmanasys.dao.ParkingSpaceMapper;
import cn.parkmanasys.dao.PlateNumberMapper;
import cn.parkmanasys.entity.BoundOfPlateNumber;
import cn.parkmanasys.entity.CarOwnerUser;
import cn.parkmanasys.entity.ParkingAccount;
import cn.parkmanasys.entity.ParkingDictionary;
import cn.parkmanasys.entity.ParkingSpace;
import cn.parkmanasys.entity.PlateNumber;

@Service
public class PlateNumberServiceImpl implements PlateNumberService {

	@PersistenceContext
	private EntityManager em;
	@Resource
	private PlateNumberMapper plateNumberMapper;

	

	@Override
	public PlateNumber addPlateNumber(PlateNumber psAdd) {
		PlateNumber plateNumber = null;

		try {
			plateNumber = plateNumberMapper.save(psAdd);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return plateNumber;
	}

	@Override
	public PlateNumber updatePlateNumber(PlateNumber plateNumberUpdate) {
		PlateNumber plateNumber = null;

		try {
			plateNumber = plateNumberMapper.findOne(plateNumberUpdate.getId());
			
			if(plateNumberUpdate.getPlateNumber() != null){
				plateNumber.setPlateNumber(plateNumberUpdate.getPlateNumber());
			}

			plateNumber = plateNumberMapper.save(plateNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return plateNumber;
	}

	
}