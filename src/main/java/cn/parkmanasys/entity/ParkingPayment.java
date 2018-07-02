package cn.parkmanasys.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Parkingpayment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PARKINGPAYMENT", schema = "PARKING")
public class ParkingPayment implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sequence")
	@SequenceGenerator(name="sequence", sequenceName="ParkingPayment_id_seq")
//	@GeneratedValue(generator="increment")
//	@GenericGenerator(name = "increment", strategy = "increment")
	private Integer id;
	
//	@Column(name="carownerId")
//	private Integer carownerId;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="carownerId")
    @NotFound(action=NotFoundAction.IGNORE)
	private CarOwnerUser carOwnerUser;
	
//	@Column(name="typeId")
//	private Integer typeId;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="typeId")
    @NotFound(action=NotFoundAction.IGNORE)
	private StopType stopType;
	
	@Column(name="orderTime")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date orderTime;
	
	@Column(name="startDate")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date startDate;
	
	@Column(name="endDate")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date endDate;
	
	@Column(name="totalTime")
	private String totalTime;
	
	@Column(name="totalCost")
	private Integer totalCost;
	
//	@Column(name="parkingId")
//	private Integer parkingId;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parkingId")
    @NotFound(action=NotFoundAction.IGNORE)
	private ParkingInfo parkingInfo;
	
//	@Column(name="statusId")
//	private Integer statusId;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="statusId")
    @NotFound(action=NotFoundAction.IGNORE)
	private StopStatus stopStatus;
	
//	@Column(name="parkingSpaceId")
//	private Integer parkingSpaceId;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parkingSpaceId")
    @NotFound(action=NotFoundAction.IGNORE)
	private ParkingSpace parkingSpace;
	
//	@Column(name="orderId")
//	private Integer orderId;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="orderId")
    @NotFound(action=NotFoundAction.IGNORE)
	private OrderList orderList;

	@Column(name="creationDate")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date creationDate;

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}

	public Integer getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Integer totalCost) {
		this.totalCost = totalCost;
	}

	public CarOwnerUser getCarOwnerUser() {
		return carOwnerUser;
	}

	public void setCarOwnerUser(CarOwnerUser carOwnerUser) {
		this.carOwnerUser = carOwnerUser;
	}

	public StopType getStopType() {
		return stopType;
	}

	public void setStopType(StopType stopType) {
		this.stopType = stopType;
	}

	public ParkingInfo getParkingInfo() {
		return parkingInfo;
	}

	public void setParkingInfo(ParkingInfo parkingInfo) {
		this.parkingInfo = parkingInfo;
	}

	public StopStatus getStopStatus() {
		return stopStatus;
	}

	public void setStopStatus(StopStatus stopStatus) {
		this.stopStatus = stopStatus;
	}

	public ParkingSpace getParkingSpace() {
		return parkingSpace;
	}

	public void setParkingSpace(ParkingSpace parkingSpace) {
		this.parkingSpace = parkingSpace;
	}

	public OrderList getOrderList() {
		return orderList;
	}

	public void setOrderList(OrderList orderList) {
		this.orderList = orderList;
	}

}