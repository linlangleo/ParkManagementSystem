package cn.parkmanasys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.parkmanasys.entity.FeedbackAndDispute;
import cn.parkmanasys.entity.ParkingAccount;
import cn.parkmanasys.entity.ParkingSpace;
import cn.parkmanasys.entity.SharingPlatform;


@Repository
public interface FeedbackAndDisputeMapper extends JpaRepository<FeedbackAndDispute, Integer>{
	//根据标题获取反馈信息
	public List<FeedbackAndDispute> findBytitle(String title);
	
	//根据描述获取反馈信息
	public List<FeedbackAndDispute> findBydescription(String description);
	
	//根据id，获取停车位信息
	@Query("from #{#entityName} p where p.id = :id")
	public FeedbackAndDispute getFeedbackAndDisputeById(@Param("id") Integer id);
}
