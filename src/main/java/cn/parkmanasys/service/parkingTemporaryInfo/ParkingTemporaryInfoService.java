package cn.parkmanasys.service.parkingTemporaryInfo;

import java.util.List;

import cn.parkmanasys.entity.ParkingTemporaryInfo;
import cn.parkmanasys.util.PageSupport;

public interface ParkingTemporaryInfoService {

	//提交修改申请
	public boolean addAmendtheapplication(ParkingTemporaryInfo parkingTemporaryInfo);
	
	//查询修改提交的数据
	public List<ParkingTemporaryInfo> getUpdateParkingInfo(PageSupport page);
	
	//查询总记录数
	public Integer getUpdateCount();
	
	//获取修改的单条数据
	public ParkingTemporaryInfo getParkingInfoUpdateByid(Integer id);
	
	//修改审批状态
	public ParkingTemporaryInfo updateParkingTemporaryInfo(ParkingTemporaryInfo ParkingTeporaryInfo);
	
	//查询停车场提交的待审批
	public ParkingTemporaryInfo getParkingByParkingInfo(Integer parkinginfoid);
	
	//查询停车场提交的修改申请记录
	public List<ParkingTemporaryInfo> getParkingInfoUp(Integer parkingInfoid,PageSupport page);
	
	//查询停车场提交的修改申请记录
	public Integer findByParkingInfoUpCount(Integer parkingInfoid);
	
	//撤销修改申请
    public ParkingTemporaryInfo RevokeParkingInfoUp(Integer parkingInfoid);
    
    //更新页面
    public ParkingTemporaryInfo UpdateJspPark(Integer id);
    
    //查询所有待审批的数据信息
    public List<ParkingTemporaryInfo> UnrevocableinfoList();
	
}
