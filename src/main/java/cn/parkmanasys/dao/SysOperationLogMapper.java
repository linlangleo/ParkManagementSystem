package cn.parkmanasys.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cn.parkmanasys.entity.SysOperationLog;

@Repository
public interface SysOperationLogMapper extends JpaRepository<SysOperationLog, Integer>{
	
	/**
	 * 根据账号ID查询本停车场操作记录
	 * @param userId
	 * @return
	 */
	public List<SysOperationLog> findByUserId(Integer userId);
}
