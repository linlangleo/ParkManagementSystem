package cn.parkmanasys.service.peccancy;

import java.util.List;

import cn.parkmanasys.entity.CarOwnerUser;
import cn.parkmanasys.entity.Peccancy;

public interface PeccancyService {
	public List<Peccancy> getAll();
	
	public boolean delPeccancy(Integer id);
	
	public Peccancy addParkingCarOwnerUser(Peccancy peccancyAdd);
}
