package cn.parkmanasys.service.parkingdictionary;

import java.util.List;

import cn.parkmanasys.entity.ParkingAccount;
import cn.parkmanasys.entity.ParkingDictionary;
import cn.parkmanasys.entity.ParkingSpace;
import cn.parkmanasys.util.PageSupport;

public interface ParkingDictionaryService {

	//根据分页和条件，获得所有的区域
	public List<ParkingDictionary> getParkingDictionaryByPage(ParkingDictionary pa, PageSupport psPage, Integer parkingInfoId);

	//根据条件，获得所有的区域编号，数量
	public int getParkingDictionaryCount(ParkingDictionary pa,Integer parkingInfoId);
	
	//根据id，获取区域信息
	public ParkingDictionary getParkingDictionaryById(Integer id);
		
	//新增区域
	public boolean addParkingDictionary(ParkingDictionary psAdd);

	//修改区域
	public ParkingDictionary updateParkingDictionary(ParkingDictionary psUpdate);
		
	//删除区域
	public boolean delParkingDictionary(Integer id);
		
	//根据停车场id获取,字典表的信息
	public List<ParkingDictionary> getParkingDictionaryByParkingId(Integer parkingId);
}
