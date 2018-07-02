package cn.parkmanasys.service.feedbackanddispute;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cn.parkmanasys.dao.FeedbackAndDisputeMapper;
import cn.parkmanasys.dao.ParkingAccountMapper;
import cn.parkmanasys.dao.SharingPlatformMapper;
import cn.parkmanasys.entity.FeedbackAndDispute;
import cn.parkmanasys.entity.ParkingAccount;
import cn.parkmanasys.entity.SharingPlatform;
import cn.parkmanasys.util.PageSupport;

@Service
public class FeedbackAndDisputeServiceImpl implements FeedbackAndDisputeService{
	@PersistenceContext
	private EntityManager em;
	@Resource
	private FeedbackAndDisputeMapper feedbackanddisputemapper;
	
	public List<FeedbackAndDispute> getFeedbackAndDispute(FeedbackAndDispute fd, PageSupport spPage) {
		List<FeedbackAndDispute> fdList = null;
		
		try {
			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<FeedbackAndDispute> criteriaQuery = criteriaBuilder.createQuery(FeedbackAndDispute.class);
			Root<FeedbackAndDispute> parkingSpace = criteriaQuery.from(FeedbackAndDispute.class);
			Predicate condition = null;			
			if(fd != null){
				if(fd.getTitle()!= null && !fd.getTitle().equals("")){
					condition = criteriaBuilder.like(criteriaBuilder.lower(parkingSpace.get("title")), StringUtils.lowerCase("%"+fd.getTitle()+"%"));
				}
				if(fd.getDescription()!= null && !fd.getDescription().equals("")){
					condition =criteriaBuilder.like(criteriaBuilder.lower(parkingSpace.get("description")), StringUtils.lowerCase("%"+fd.getDescription()+"%"));
				}
			}
			
			if(condition != null){
				criteriaQuery.where(condition);
			}
			
			TypedQuery<FeedbackAndDispute> typedQuery = em.createQuery(criteriaQuery);

			
			//分页
			typedQuery.setFirstResult((spPage.getCurrentPageNo()-1)*spPage.getPageSize());
			typedQuery.setMaxResults(spPage.getPageSize());
			
			fdList = typedQuery.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fdList;
	}

	
	public int getFeedbackAndDisputeCount(FeedbackAndDispute fd) {
		int count = 0;
		
		try {
			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
			Root<FeedbackAndDispute> root = criteriaQuery.from(FeedbackAndDispute.class);
			Predicate condition = null;
		
			if(fd != null){
				if(fd.getTitle()!= null && !fd.getTitle().equals("")){
					condition =criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), StringUtils.lowerCase("%"+fd.getTitle()+"%"));
				}
				if(fd.getDescription()!= null && !fd.getDescription().equals("")){
					condition =criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), StringUtils.lowerCase("%"+fd.getDescription()+"%"));
				}
			}
			if(condition != null){
				criteriaQuery.where(condition);
			}
			
			criteriaQuery.select(criteriaBuilder.count(root));//查询总数，未去重复
			
			TypedQuery<Long> typedQuery = em.createQuery(criteriaQuery);

			count = new Long(typedQuery.getSingleResult()).intValue();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return count;
	
	}


	public FeedbackAndDispute getFeedbackAndDisputeById(Integer id) {
		FeedbackAndDispute feedbackAndDispute = null;
		
		try {
			feedbackAndDispute =feedbackanddisputemapper.findOne(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return feedbackAndDispute;
	}


	public boolean addFeedbackAndDispute(FeedbackAndDispute psAdd) {
		FeedbackAndDispute feedbackAndDispute = null;
		
		try {
			feedbackAndDispute = feedbackanddisputemapper.save(psAdd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(feedbackAndDispute != null){
			return true;
		}
		
		return false;
	}


	public FeedbackAndDispute updateFeedbackAndDispute(FeedbackAndDispute psUpdate) {
		FeedbackAndDispute feedbackAndDisputeUpdate = null;
		try {
			//根据id，获取共享信息
			feedbackAndDisputeUpdate =feedbackanddisputemapper.findOne(psUpdate.getId());
			
			if(psUpdate.getTitle() != null){
				feedbackAndDisputeUpdate.setTitle(psUpdate.getTitle());
			}
			if(psUpdate.getDescription() != null){
				feedbackAndDisputeUpdate.setDescription(psUpdate.getDescription());
			}

			feedbackAndDisputeUpdate = feedbackanddisputemapper.save(feedbackAndDisputeUpdate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return feedbackAndDisputeUpdate;
	}


	public boolean delFeedbackAndDispute(Integer id) {
		try {
			//根据id，获取共享信息
			feedbackanddisputemapper.delete(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
