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
 * Parkinginfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ParkingTemporaryInfo", schema = "PARKING")
public class ParkingTemporaryInfo implements java.io.Serializable {


	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sequence")
	@SequenceGenerator(name="sequence", sequenceName="ParkingTemporaryInfo_id_seq")
//	@GeneratedValue(generator="increment")
//	@GenericGenerator(name = "increment", strategy = "increment")
	private Integer id;
	
	@Column(name="parkingName")
	private String parkingName;
	
	@Column(name="parkingAddress")
	private String parkingAddress;
	
	@Column(name="charge")
	private Integer charge;
	
	@Column(name="parkingSpaceCount")
	private Integer parkingSpaceCount;

//	@Column(name="parkingId")
//	private Integer parkingId;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parkingId")
    @NotFound(action=NotFoundAction.IGNORE)
	private ParkingInfo parkingInfo;
	
//	@Column(name="districtOrCountyId")
//	private Integer districtOrCountyId;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="districtOrCountyid")
    @NotFound(action=NotFoundAction.IGNORE)
	private DistrictOrCounty districtOrCounty;
	
//	@Column(name="streetId")
//	private Integer streetId;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="streetId")
    @NotFound(action=NotFoundAction.IGNORE)
	private Street street;

//	@Column(name="examineStatusId")
//	private Integer examineStatusId;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="examineStatusId")
    @NotFound(action=NotFoundAction.IGNORE)
	private ExamineStatusInfo examineStatusInfo;

	@Column(name="creationDate")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date creationDate;
	
	public ParkingInfo getParkingInfo() {
		return parkingInfo;
	}

	public void setParkingInfo(ParkingInfo parkingInfo) {
		this.parkingInfo = parkingInfo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getParkingName() {
		return parkingName;
	}

	public void setParkingName(String parkingName) {
		this.parkingName = parkingName;
	}

	public String getParkingAddress() {
		return parkingAddress;
	}

	public void setParkingAddress(String parkingAddress) {
		this.parkingAddress = parkingAddress;
	}

	public Integer getCharge() {
		return charge;
	}

	public void setCharge(Integer charge) {
		this.charge = charge;
	}

	public Integer getParkingSpaceCount() {
		return parkingSpaceCount;
	}

	public void setParkingSpaceCount(Integer parkingSpaceCount) {
		this.parkingSpaceCount = parkingSpaceCount;
	}

	public DistrictOrCounty getDistrictOrCounty() {
		return districtOrCounty;
	}

	public void setDistrictOrCounty(DistrictOrCounty districtOrCounty) {
		this.districtOrCounty = districtOrCounty;
	}

	public Street getStreet() {
		return street;
	}

	public void setStreet(Street street) {
		this.street = street;
	}
	
	public ExamineStatusInfo getExamineStatusInfo() {
		return examineStatusInfo;
	}

	public void setExamineStatusInfo(ExamineStatusInfo examineStatusInfo) {
		this.examineStatusInfo = examineStatusInfo;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}


}