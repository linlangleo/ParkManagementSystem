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
 * Car entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CAR", schema = "PARKING")
public class Car implements java.io.Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sequence")
	@SequenceGenerator(name="sequence", sequenceName="Car_id_seq")
//	@GeneratedValue(generator="increment")
//	@GenericGenerator(name = "increment", strategy = "increment")
	private Integer id;
	
//	@Column(name="carownerId")
//	private Integer carownerId;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="carownerId")
    @NotFound(action=NotFoundAction.IGNORE)
	private CarOwnerUser carOwnerUser;
	
	@Column(name="vehicleType")
	private String vehicleType;
	
//	@Column(name="plateNumberId")
//	private Integer plateNumberId;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="plateNumberId")
    @NotFound(action=NotFoundAction.IGNORE)
	private PlateNumber plateNumber;

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

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
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


}