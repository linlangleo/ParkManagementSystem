package cn.parkmanasys.service.blacklist;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import cn.parkmanasys.dao.BlackListMapper;
import cn.parkmanasys.entity.BlackList;
import cn.parkmanasys.entity.DistrictOrCounty;
import cn.parkmanasys.entity.ParkingInfo;

@Service
public class BlackListServiceImpl implements BlackListService{
	//实体管理器
	@PersistenceContext
	private EntityManager em;
	
	//引入相关Dao层
	@Resource
	private BlackListMapper blackListMapper;
	@Override
	public boolean addBlackList(BlackList blAdd) {
		BlackList blackList = new BlackList();
		try {
			blackList = blackListMapper.save(blAdd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(blackList != null){
			return true;
		}
		
		return false;
	}

	@Override
	public BlackList updateBlackList(BlackList blUpdate) {
		BlackList blackListUpdate= null;
		
		try {
			blackListUpdate = blackListMapper.findOne(blUpdate.getId());
			if(blUpdate.getPlateNumber()!= null){
				blackListUpdate.setPlateNumber(blUpdate.getPlateNumber());
			}
			blackListUpdate = blackListMapper.saveAndFlush(blackListUpdate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return blackListUpdate;
	}

	@Override
	public boolean delBlackList(Integer id) {
		try {
			blackListMapper.delete(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
