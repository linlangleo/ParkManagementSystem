package cn.parkmanasys.scheduled;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.parkmanasys.entity.ExamineStatusInfo;
import cn.parkmanasys.entity.ParkingTemporaryInfo;
import cn.parkmanasys.service.parkingTemporaryInfo.ParkingTemporaryInfoService;

@Component
public class RevokeScheduledTasks {
		
	private static final Logger logger = LoggerFactory.getLogger(RevokeScheduledTasks.class);
	
	@Resource
	private ParkingTemporaryInfoService ParkingTemporaryInfoservice;
	
	@Scheduled(cron="0 0 0 * * ?")
    public void executeFileDownLoadTask() {
        // 间隔6秒钟,执行任务   
        Thread current = Thread.currentThread(); 
      /*  System.out.println("撤销所有修改申请超过30天或等于30天的信息:"+current.getId());*/
        List<ParkingTemporaryInfo> parkList=null;
        ParkingTemporaryInfo par=null;
        try{
        	parkList=ParkingTemporaryInfoservice.UnrevocableinfoList();
        
        	if(parkList!=null)
        {
        	int day=30;
        	Date date=new Date();
        	
        	for(ParkingTemporaryInfo item:parkList)
        	{
        		int days = (int)((item.getCreationDate().getTime() - date.getTime())/86400000);
        		if(days>=30)
        		{
        			par=ParkingTemporaryInfoservice.RevokeParkingInfoUp(item.getId());
        			if(par==null)
        			{
        				 throw new Exception("撤销失败");
        			}
        		}
        	}
        }
        }catch (Exception e) {
			// TODO: handle exception
        	e.printStackTrace();
        	logger.debug("异常！！！");
		}
        logger.info("ScheduledTest.executeFileDownLoadTask 正常  撤销任务:"+current.getId()+ ",name:"+current.getName());
    }
}
