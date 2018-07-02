package cn.parkmanasys.service.platenumber;

import java.util.List;

import cn.parkmanasys.entity.BoundOfPlateNumber;
import cn.parkmanasys.entity.ParkingDictionary;
import cn.parkmanasys.entity.Peccancy;
import cn.parkmanasys.entity.PlateNumber;


public interface PlateNumberService {
	
	
	public PlateNumber addPlateNumber(PlateNumber psAdd);
	
	public PlateNumber updatePlateNumber(PlateNumber plateNumberUpdate);
}
