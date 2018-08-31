package cn.parkmanasys.service.sysinterfacemanager;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import cn.parkmanasys.entity.SysInterfaceManager;
import cn.parkmanasys.util.PageSupport;

/**
 * 接口管理service 接口
 * @return
 */
@Validated
public interface SysInterfaceManagerService {
	/**
	 * 查询接口信息
	 */
	public List<SysInterfaceManager> getInterfaceInfo(SysInterfaceManager sim,PageSupport ps);
	
	/**
	 * 新增接口信息
	 */
	public void addInterfaceInfo(SysInterfaceManager sim);
	
}
