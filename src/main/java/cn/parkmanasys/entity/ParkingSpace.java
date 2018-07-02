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

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Parkingspace entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PARKINGSPACE", schema = "PARKING")
@DynamicUpdate
public class ParkingSpace implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sequence")
	@SequenceGenerator(name="sequence", sequenceName="ParkingSpace_id_seq")
//	@GeneratedValue(generator="increment")
//	@GenericGenerator(name = "increment", strategy = "increment")
	private Integer id;
	
	@Column(name="parkingSpaceNumber")
	private String parkingSpaceNumber;
	
//	@Column(name="areaNumberId")
//	private Integer areaNumberId;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="areaNumberId")
    @NotFound(action=NotFoundAction.IGNORE)
	private ParkingDictionary parkingDictionary;
	
//	@Column(name="parkingSpaceStatusId")
//	private Integer parkingSpaceStatusId;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parkingSpaceStatusId")
    @NotFound(action=NotFoundAction.IGNORE)
	private ParkingSpaceStatus parkingSpaceStatus;
	
//	@Column(name="parkingSpaceOwnerId")
//	private Integer parkingSpaceOwnerId;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parkingSpaceOwnerId")
    @NotFound(action=NotFoundAction.IGNORE)
	private CarOwnerUser carOwnerUser;

//	@Column(name="parkingId")
//	private Integer parkingId;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parkingId")
    @NotFound(action=NotFoundAction.IGNORE)
	private ParkingInfo parkingInfo;

	@Column(name="ifCreateModel")
	private String ifCreateModel;

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

	public String getParkingSpaceNumber() {
		return parkingSpaceNumber;
	}

	public void setParkingSpaceNumber(String parkingSpaceNumber) {
		this.parkingSpaceNumber = parkingSpaceNumber;
	}

	public ParkingDictionary getParkingDictionary() {
		return parkingDictionary;
	}

	public void setParkingDictionary(ParkingDictionary parkingDictionary) {
		this.parkingDictionary = parkingDictionary;
	}

	public ParkingSpaceStatus getParkingSpaceStatus() {
		return parkingSpaceStatus;
	}

	public void setParkingSpaceStatus(ParkingSpaceStatus parkingSpaceStatus) {
		this.parkingSpaceStatus = parkingSpaceStatus;
	}

	public CarOwnerUser getCarOwnerUser() {
		return carOwnerUser;
	}

	public void setCarOwnerUser(CarOwnerUser carOwnerUser) {
		this.carOwnerUser = carOwnerUser;
	}

	public ParkingInfo getParkingInfo() {
		return parkingInfo;
	}

	public void setParkingInfo(ParkingInfo parkingInfo) {
		this.parkingInfo = parkingInfo;
	}

	public String getIfCreateModel() {
		return ifCreateModel;
	}

	public void setIfCreateModel(String ifCreateModel) {
		this.ifCreateModel = ifCreateModel;
	}


}