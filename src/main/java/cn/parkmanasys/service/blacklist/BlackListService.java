package cn.parkmanasys.service.blacklist;
import cn.parkmanasys.entity.BlackList;

public interface BlackListService {
	//新增黑名单
	public boolean addBlackList(BlackList blAdd);
	
	//修改黑名单
	public BlackList updateBlackList(BlackList blUpdate);
	
	//删除删除黑名单
	public boolean delBlackList(Integer id);
}
