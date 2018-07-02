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
 * Touristattractions entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TOURISTATTRACTIONS", schema = "PARKING")
public class TouristAttractions implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sequence")
	@SequenceGenerator(name="sequence", sequenceName="TouristAttractions_id_seq")
//	@GeneratedValue(generator="increment")
//	@GenericGenerator(name = "increment", strategy = "increment")
	private Integer id;
	
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
	
	@Column(name="touristAttractionName")
	private String touristAttractionName;
	
	@Column(name="touristAttractionAddress")
	private String touristAttractionAddress;
	
	@Column(name="featuresOfTouristAttraction")
	private String featuresOfTouristAttraction;

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

	public String getTouristAttractionName() {
		return touristAttractionName;
	}

	public void setTouristAttractionName(String touristAttractionName) {
		this.touristAttractionName = touristAttractionName;
	}

	public String getTouristAttractionAddress() {
		return touristAttractionAddress;
	}

	public void setTouristAttractionAddress(String touristAttractionAddress) {
		this.touristAttractionAddress = touristAttractionAddress;
	}

	public String getFeaturesOfTouristAttraction() {
		return featuresOfTouristAttraction;
	}

	public void setFeaturesOfTouristAttraction(String featuresOfTouristAttraction) {
		this.featuresOfTouristAttraction = featuresOfTouristAttraction;
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

}