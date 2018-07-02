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

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Sharingplatform entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SHARINGPLATFORM", schema = "PARKING")
public class SharingPlatform implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sequence")
	@SequenceGenerator(name="sequence", sequenceName="SharingPlatform_id_seq")
//	@GeneratedValue(generator="increment")
//	@GenericGenerator(name = "increment", strategy = "increment")
	private Integer id;
	
//	@Column(name="parkingId")
//	private Integer parkingId;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parkingId")
    @NotFound(action=NotFoundAction.IGNORE)
	private ParkingInfo parkingInfo;
	
//	@Column(name="parkingSpaceId")
//	private Integer parkingSpaceId;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parkingSpaceId")
    @NotFound(action=NotFoundAction.IGNORE)
	private ParkingSpace parkingSpace;
	
//	@Column(name="sharingStatusId")
//	private Integer sharingStatusId;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sharingStatusId")
    @NotFound(action=NotFoundAction.IGNORE)
	private SharingStatus sharingStatus;
	
//	@Column(name="sharerId")
//	private Integer sharerId;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sharerId")
    @NotFound(action=NotFoundAction.IGNORE)
	private CarOwnerUser sharer;
	
//	@Column(name="userId")
//	private Integer userId;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userId")
    @NotFound(action=NotFoundAction.IGNORE)
	private CarOwnerUser user;

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

	public SharingStatus getSharingStatus() {
		return sharingStatus;
	}

	public void setSharingStatus(SharingStatus sharingStatus) {
		this.sharingStatus = sharingStatus;
	}

	public CarOwnerUser getSharer() {
		return sharer;
	}

	public void setSharer(CarOwnerUser sharer) {
		this.sharer = sharer;
	}

	public CarOwnerUser getUser() {
		return user;
	}

	public void setUser(CarOwnerUser user) {
		this.user = user;
	}

}