package cn.parkmanasys.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Transaction entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TRANSACTION", schema = "PARKING")
public class Transaction implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sequence")
	@SequenceGenerator(name="sequence", sequenceName="Transaction_id_seq")
//	@GeneratedValue(generator="increment")
//	@GenericGenerator(name = "increment", strategy = "increment")
	private Integer id;
	
//	@Column(name="parkingId")
//	private Integer parkingId;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="parkingId")
	private ParkingInfo parkingInfo;
	
//	@Column(name="parkingSpaceId")
//	private Integer parkingSpaceId;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parkingSpaceId")
	private ParkingSpace parkingSpace;
	
//	@Column(name="carownerId")
//	private Integer carownerId;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="carownerId")
	private CarOwnerUser carOwnerUser;

//	@Column(name="transactionTypeId")
//	private Integer transactionTypeId;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="transactionTypeId")
	private TransactionType transactionType;

	@Column(name="startDate")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date startDate;

	@Column(name="endDate")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date endDate;
	
	@Column(name="transactionAmount")
	private Double transactionAmount;
	
	@ManyToMany(targetEntity=TransactionStatus.class,cascade=CascadeType.ALL,fetch = FetchType.EAGER)
//  @JoinTable(name = "iyo_memberdesc", joinColumns = { @JoinColumn(name = "clubId") }, inverseJoinColumns = {@JoinColumn(name = "memberId") })
	@JoinTable(name = "TransactionStatusInfo"/*, catalog="iyo"*/, joinColumns = { @JoinColumn(name = "TransactionId",nullable = true, updatable = true)},inverseJoinColumns = {@JoinColumn(name="TransactionStatusId",nullable = true, updatable = true)})
    private List<TransactionStatus> transactionStatusList;

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

	public ParkingInfo getParkingInfo() {
		return parkingInfo;
	}

	public void setParkingInfo(ParkingInfo parkingInfo) {
		this.parkingInfo = parkingInfo;
	}

	public ParkingSpace getParkingSpace() {
		return parkingSpace;
	}

	public void setParkingSpace(ParkingSpace parkingSpace) {
		this.parkingSpace = parkingSpace;
	}

	public CarOwnerUser getCarOwnerUser() {
		return carOwnerUser;
	}

	public void setCarOwnerUser(CarOwnerUser carOwnerUser) {
		this.carOwnerUser = carOwnerUser;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
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

	public Double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public List<TransactionStatus> getTransactionStatusList() {
		return transactionStatusList;
	}

	public void setTransactionStatusList(List<TransactionStatus> transactionStatusList) {
		this.transactionStatusList = transactionStatusList;
	}



}