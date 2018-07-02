package cn.parkmanasys.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Carowneruser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CAROWNERUSER", schema = "PARKING")
public class CarOwnerUser implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sequence")
	@SequenceGenerator(name="sequence", sequenceName="CarOwnerUser_id_seq")
//	@GeneratedValue(generator="increment")
//	@GenericGenerator(name = "increment", strategy = "increment")
	private Integer id;
	
	@Column(name="userName")
	private String userName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="boundPlateNumberCount")
	private Integer boundPlateNumberCount;
	
	@Column(name="nickName")
	private String nickName;
	
	@Column(name="sex")
	private String sex;
	
	@Column(name="borndate")
	private Date borndate;
	
	@Column(name="registrationTime")
	private Date registrationTime;
	
	@Column(name="realName")
	private String realName;
	
	@Column(name="identityCard")
	private String identityCard;

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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getBoundPlateNumberCount() {
		return boundPlateNumberCount;
	}

	public void setBoundPlateNumberCount(Integer boundPlateNumberCount) {
		this.boundPlateNumberCount = boundPlateNumberCount;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBorndate() {
		return borndate;
	}

	public void setBorndate(Date borndate) {
		this.borndate = borndate;
	}

	public Date getRegistrationTime() {
		return registrationTime;
	}

	public void setRegistrationTime(Date registrationTime) {
		this.registrationTime = registrationTime;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
	
	
}