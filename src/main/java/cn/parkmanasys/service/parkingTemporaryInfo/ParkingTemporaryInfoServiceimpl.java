package cn.parkmanasys.service.parkingTemporaryInfo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;
/**
 * 停车场信息修改业务层 
 */

import cn.parkmanasys.dao.ParkingTemporaryInfoMapper;
import cn.parkmanasys.entity.ExamineStatusInfo;
import cn.parkmanasys.entity.ParkingInfo;
import cn.parkmanasys.entity.ParkingSpace;
import cn.parkmanasys.entity.ParkingTemporaryInfo;
import cn.parkmanasys.util.PageSupport;
@Service
public class ParkingTemporaryInfoServiceimpl implements ParkingTemporaryInfoService{
	
	//实体管理器
	@PersistenceContext
	private EntityManager em;
	
	@Resource
	private ParkingTemporaryInfoMapper parkingTemporaryinfomapper;

	//修改新增至停车场临时表
	@Override
	public boolean addAmendtheapplication(ParkingTemporaryInfo parkingTemporaryInfo) {
		ParkingTemporaryInfo park = null;
		try{
			park=parkingTemporaryinfomapper.save(parkingTemporaryInfo);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if(park!=null)
		{
			return true;
		}
		return false;
	}
	/**
	 * 查询修改提交的数据
	 */
	@Override
	public List<ParkingTemporaryInfo> getUpdateParkingInfo(PageSupport page) {
		// TODO Auto-generated method stub
		List<ParkingTemporaryInfo> psList = null;
		try {
			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<ParkingTemporaryInfo> criteriaQuery = criteriaBuilder.createQuery(ParkingTemporaryInfo.class);
			Root<ParkingTemporaryInfo> root = criteriaQuery.from(ParkingTemporaryInfo.class);
			List<Predicate> conditions = new ArrayList<Predicate>();
			
			conditions.add(criteriaBuilder.equal(root.get("examineStatusInfo").get("id"), 1));
			
			//对日期降序
			criteriaQuery.orderBy(criteriaBuilder.desc(root.get("creationDate")));
			
			criteriaQuery.where(conditions.toArray(new Predicate[0]));
			TypedQuery<ParkingTemporaryInfo> typedQuery = em.createQuery(criteriaQuery);
			typedQuery.setFirstResult((page.getCurrentPageNo()-1)*page.getPageSize());
			typedQuery.setMaxResults(page.getPageSize());
			psList = typedQuery.getResultList();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return psList;
	}
	@Override
	public Integer getUpdateCount() {
		// TODO Auto-generated method stub
		int count=0;
		try {
			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
			Root<ParkingTemporaryInfo> root = criteriaQuery.from(ParkingTemporaryInfo.class);
			List<Predicate> conditions = new ArrayList<Predicate>();
			//对日期降序
			criteriaQuery.select(criteriaBuilder.count(root));
			conditions.add(criteriaBuilder.equal(root.get("examineStatusInfo").get("id"), 1));
			criteriaQuery.where(conditions.toArray(new Predicate[0]));
			TypedQuery<Long> typedQuery = em.createQuery(criteriaQuery);
			
			count = new Long(typedQuery.getSingleResult()).intValue();
			/*psList = typedQuery.getResultList();*/
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return count;
	}
	
	/**
	 * 获取修改审批单个数据 
	 */
	@Override
	public ParkingTemporaryInfo getParkingInfoUpdateByid(Integer id) {
		ParkingTemporaryInfo parkinfo=null;
				try{
					parkinfo=parkingTemporaryinfomapper.findOne(id);
				}catch (Exception e) {
					// TODO: handle exception
				}
		return parkinfo;
	}
	/**
	 * 修改审批状态
	 */
	@Override
	public ParkingTemporaryInfo updateParkingTemporaryInfo(ParkingTemporaryInfo ParkingTeporaryInfo) {
		// TODO Auto-generated method stub
		ParkingTemporaryInfo park=null;
		try{
			park=parkingTemporaryinfomapper.findOne(ParkingTeporaryInfo.getId());
			if(ParkingTeporaryInfo.getExamineStatusInfo()!=null)
			{
				park.setExamineStatusInfo(ParkingTeporaryInfo.getExamineStatusInfo());
			}
			park=parkingTemporaryinfomapper.saveAndFlush(park);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return park;
	}
	
	/**
	 * 查询停车场提交的待审批
	 * @author Dickson 
	 */
	@Override
	public ParkingTemporaryInfo getParkingByParkingInfo(Integer parkinginfoid) {
		// TODO Auto-generated method stub
		ParkingTemporaryInfo psList = null;
		try {
			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<ParkingTemporaryInfo> criteriaQuery = criteriaBuilder.createQuery(ParkingTemporaryInfo.class);
			Root<ParkingTemporaryInfo> root = criteriaQuery.from(ParkingTemporaryInfo.class);
			List<Predicate> conditions = new ArrayList<Predicate>();
			
			conditions.add(criteriaBuilder.equal(root.get("parkingInfo").get("id"),parkinginfoid));
			conditions.add(criteriaBuilder.equal(root.get("examineStatusInfo").get("id"), 1));
		/*	//对日期降序
			criteriaQuery.orderBy(criteriaBuilder.desc(root.get("creationDate")));*/
			
			criteriaQuery.where(conditions.toArray(new Predicate[1]));
			TypedQuery<ParkingTemporaryInfo> typedQuery = em.createQuery(criteriaQuery);
			
			psList=typedQuery.getSingleResult();
					
		}catch (Exception e) {
			// TODO: handle exception
			/*e.printStackTrace();*/
		}
		return psList;
	}
	/**
	 *  查询停车场提交的修改申请记录
	 */
	@Override
	public List<ParkingTemporaryInfo> getParkingInfoUp(Integer parkingInfoid,PageSupport page) {
		// TODO Auto-generated method stub
		List<ParkingTemporaryInfo> psList = null;
		try {
			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<ParkingTemporaryInfo> criteriaQuery = criteriaBuilder.createQuery(ParkingTemporaryInfo.class);
			Root<ParkingTemporaryInfo> root = criteriaQuery.from(ParkingTemporaryInfo.class);
			List<Predicate> conditions = new ArrayList<Predicate>();
			conditions.add(criteriaBuilder.equal(root.get("parkingInfo").get("id"),parkingInfoid));
			
			//对日期降序
			criteriaQuery.orderBy(criteriaBuilder.desc(root.get("creationDate")));
			
			criteriaQuery.where(conditions.toArray(new Predicate[0]));
			TypedQuery<ParkingTemporaryInfo> typedQuery = em.createQuery(criteriaQuery);
			typedQuery.setFirstResult((page.getCurrentPageNo()-1)*page.getPageSize());
			typedQuery.setMaxResults(page.getPageSize());
			psList = typedQuery.getResultList();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return psList;
	}
	/**
	 * 查询停车场提交的修改申请记录 
	 */
	@Override
	public Integer findByParkingInfoUpCount(Integer parkingInfoid) {
		int count=0;
		try {
			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
			Root<ParkingTemporaryInfo> root = criteriaQuery.from(ParkingTemporaryInfo.class);
			List<Predicate> conditions = new ArrayList<Predicate>();
			
			//对日期降序
			criteriaQuery.select(criteriaBuilder.count(root));
			
			conditions.add(criteriaBuilder.equal(root.get("parkingInfo").get("id"),parkingInfoid));
			
			criteriaQuery.where(conditions.toArray(new Predicate[0]));
			
			TypedQuery<Long> typedQuery = em.createQuery(criteriaQuery);
			
			count = new Long(typedQuery.getSingleResult()).intValue();
			/*psList = typedQuery.getResultList();*/
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return count;
	}
	/**
	 * 撤销修改申请 
	 */
	@Override
	public ParkingTemporaryInfo RevokeParkingInfoUp(Integer parkingInfoid) {
		ParkingTemporaryInfo park=null;
		try{
			if(parkingInfoid!=null)
			{
				park=parkingTemporaryinfomapper.findOne(parkingInfoid);
				
				ExamineStatusInfo ex=new ExamineStatusInfo();
				ex.setId(4);
				park.setExamineStatusInfo(ex);
			}
			park=parkingTemporaryinfomapper.saveAndFlush(park);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			park=null;
		}
		return park;
	}
	/**
	 * 更新页面 
	 */
	@Override
	public ParkingTemporaryInfo UpdateJspPark(Integer id) {
		// TODO Auto-generated method stub
		ParkingTemporaryInfo park=null;
		try{
			if(id!=null)
			{
				park=parkingTemporaryinfomapper.findOne(id);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}	
		return park;
	}
	/**
	 * 查询所有待审批的数据信息 
	 */
	@Override
	public List<ParkingTemporaryInfo> UnrevocableinfoList() {
		List<ParkingTemporaryInfo> psList = null;
		try {
			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			CriteriaQuery<ParkingTemporaryInfo> criteriaQuery = criteriaBuilder.createQuery(ParkingTemporaryInfo.class);
			Root<ParkingTemporaryInfo> root = criteriaQuery.from(ParkingTemporaryInfo.class);
			List<Predicate> conditions = new ArrayList<Predicate>();
			
			conditions.add(criteriaBuilder.equal(root.get("examineStatusInfo").get("id"), 1));
			//对日期降序
			criteriaQuery.orderBy(criteriaBuilder.desc(root.get("creationDate")));
			
			criteriaQuery.where(conditions.toArray(new Predicate[0]));
			TypedQuery<ParkingTemporaryInfo> typedQuery = em.createQuery(criteriaQuery);
			psList = typedQuery.getResultList();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return psList;
	}
	
	
}
