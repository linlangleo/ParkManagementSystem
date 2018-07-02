package cn.parkmanasys.service.OperationRecord;

import java.util.List;

import cn.parkmanasys.entity.SysOperationLog;
import cn.parkmanasys.util.PageSupport;
public interface SysOperationLogService {
	/**
	 * 获得操作记录表信息并分页
	 * @param record 操作记录实体
	 * @return
	 */
	public List<SysOperationLog> findByParkingAccountId(SysOperationLog record,PageSupport psPage,Integer parkingId);
	
	/**
	 * 获得操作记录表总条数
	 * @param record
	 * @return
	 */
	public int getOperationRecordCount(SysOperationLog record,Integer parkingId);
	
	/**
	 * 插入操作记录
	 * @param orAdd
	 * @return
	 */
	public boolean addOperationRecord(SysOperationLog orAdd);
	
	/**
	 * 插入操作记录
	 * @param soLog
	 * @return
	 */
	public boolean addOperationLog(SysOperationLog soLog);
}
