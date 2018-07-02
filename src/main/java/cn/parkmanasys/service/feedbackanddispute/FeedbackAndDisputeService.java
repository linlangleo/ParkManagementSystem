package cn.parkmanasys.service.feedbackanddispute;

import java.util.List;

import cn.parkmanasys.entity.FeedbackAndDispute;
import cn.parkmanasys.entity.SharingPlatform;
import cn.parkmanasys.util.PageSupport;

public interface FeedbackAndDisputeService {
	//根据标题获取反馈信息
	public List<FeedbackAndDispute> getFeedbackAndDispute(FeedbackAndDispute fd,PageSupport spPage);
		
	//根据条件，获得所有的反馈数据，数量
	public int getFeedbackAndDisputeCount(FeedbackAndDispute fd);
	
	//根据id，获取共享信息
	public FeedbackAndDispute getFeedbackAndDisputeById(Integer id);

	// 新增共享信息
	public boolean addFeedbackAndDispute(FeedbackAndDispute psAdd);

	// 修改共享信息
	public FeedbackAndDispute updateFeedbackAndDispute(FeedbackAndDispute psUpdate);

	// 删除共享信息
	public boolean delFeedbackAndDispute(Integer id);
	
}
