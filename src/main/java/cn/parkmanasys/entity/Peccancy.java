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
 * Peccancy entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PECCANCY", schema = "PARKING")
public class Peccancy implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sequence")
	@SequenceGenerator(name="sequence", sequenceName="Peccancy_id_seq")
//	@GeneratedValue(generator="increment")
//	@GenericGenerator(name = "increment", strategy = "increment")
	private Integer id;
	
//	@Column(name="plateNumberId")
//	private Integer plateNumberId;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="plateNumberId")
    @NotFound(action=NotFoundAction.IGNORE)
	private PlateNumber plateNumber;
	
	@Column(name="peccancyInfo")
	private String peccancyInfo;
	
	@Column(name="deductionScore")
	private Integer deductionScore;
	
	@Column(name="peccancyLocation")
	private String peccancyLocation;

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

	public String getPeccancyInfo() {
		return peccancyInfo;
	}

	public void setPeccancyInfo(String peccancyInfo) {
		this.peccancyInfo = peccancyInfo;
	}

	public Integer getDeductionScore() {
		return deductionScore;
	}

	public void setDeductionScore(Integer deductionScore) {
		this.deductionScore = deductionScore;
	}

	public String getPeccancyLocation() {
		return peccancyLocation;
	}

	public void setPeccancyLocation(String peccancyLocation) {
		this.peccancyLocation = peccancyLocation;
	}

	public PlateNumber getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(PlateNumber plateNumber) {
		this.plateNumber = plateNumber;
	}

}