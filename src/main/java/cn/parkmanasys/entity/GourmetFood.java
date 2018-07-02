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
 * Gourmetfood entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "GOURMETFOOD", schema = "PARKING")
public class GourmetFood implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sequence")
	@SequenceGenerator(name="sequence", sequenceName="GourmetDood_id_seq")
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
	
	@Column(name="gourmetFoodName")
	private String gourmetFoodName;
	
	@Column(name="gourmetFoodAddress")
	private String gourmetFoodAddress;
	
	@Column(name="featuresOfGourmetFood")
	private String featuresOfGourmetFood;

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

	public String getGourmetFoodName() {
		return gourmetFoodName;
	}

	public void setGourmetFoodName(String gourmetFoodName) {
		this.gourmetFoodName = gourmetFoodName;
	}

	public String getGourmetFoodAddress() {
		return gourmetFoodAddress;
	}

	public void setGourmetFoodAddress(String gourmetFoodAddress) {
		this.gourmetFoodAddress = gourmetFoodAddress;
	}

	public String getFeaturesOfGourmetFood() {
		return featuresOfGourmetFood;
	}

	public void setFeaturesOfGourmetFood(String featuresOfGourmetFood) {
		this.featuresOfGourmetFood = featuresOfGourmetFood;
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