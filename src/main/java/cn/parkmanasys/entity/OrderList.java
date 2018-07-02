package cn.parkmanasys.entity;

import java.math.BigDecimal;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Orderlist entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ORDERLIST", schema = "PARKING")
public class OrderList implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sequence")
	@SequenceGenerator(name="sequence", sequenceName="OrderList_id_seq")
//	@GeneratedValue(generator="increment")
//	@GenericGenerator(name = "increment", strategy = "increment")
	private Integer id;
	
//	@Column(name="carOwnerId")
//	private Integer carOwnerId;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="carownerId")
    @NotFound(action=NotFoundAction.IGNORE)
	private CarOwnerUser carOwnerUser;
	
//	@Column(name="plateNumberId")
//	private Integer plateNumberId;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="plateNumberId")
    @NotFound(action=NotFoundAction.IGNORE)
	private PlateNumber plateNumber;
	
	@Column(name="orderDate")
	private Date orderDate;
	
	@Column(name="startDate")
	private Date startDate;
	
//	@Column(name="parkingId")
//	private BigDecimal parkingId;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parkingId")
    @NotFound(action=NotFoundAction.IGNORE)
	private ParkingInfo parkingInfo;
	
//	@Column(name="statusId")
//	private BigDecimal statusId;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="statusId")
    @NotFound(action=NotFoundAction.IGNORE)
	private OrderStatus orderStatus;
	
//	@Column(name="parkingSpaceId")
//	private BigDecimal parkingSpaceId;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parkingSpaceId")
    @NotFound(action=NotFoundAction.IGNORE)
	private ParkingSpace parkingSpace;

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

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public CarOwnerUser getCarOwnerUser() {
		return carOwnerUser;
	}

	public void setCarOwnerUser(CarOwnerUser carOwnerUser) {
		this.carOwnerUser = carOwnerUser;
	}

	public PlateNumber getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(PlateNumber plateNumber) {
		this.plateNumber = plateNumber;
	}

	public ParkingInfo getParkingInfo() {
		return parkingInfo;
	}

	public void setParkingInfo(ParkingInfo parkingInfo) {
		this.parkingInfo = parkingInfo;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public ParkingSpace getParkingSpace() {
		return parkingSpace;
	}

	public void setParkingSpace(ParkingSpace parkingSpace) {
		this.parkingSpace = parkingSpace;
	}

}