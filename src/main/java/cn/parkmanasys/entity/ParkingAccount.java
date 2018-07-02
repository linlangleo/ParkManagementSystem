package cn.parkmanasys.entity;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Parkingaccount entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PARKINGACCOUNT", schema = "PARKING")
public class ParkingAccount implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sequence")
	@SequenceGenerator(name="sequence", sequenceName="ParkingAccount_id_seq")
//	@GeneratedValue(generator="increment")
//	@GenericGenerator(name = "increment", strategy = "increment")
	private Integer id;
	
//	@Column(name="typeId")
//	private Integer typeId;
	@ManyToOne(/*fetch=FetchType.LAZY*/)
	@JoinColumn(name="typeId")
    @NotFound(action=NotFoundAction.IGNORE)
	private AccountType accountType;
	
	
	@Column(name="registrationDate")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date registrationDate;
	
	@Column(name="accountName")
	private String accountName;
	
	@Column(name="accountPassword")
	private String accountPassword;
	
//	@Column(name="parkingId")
//	private Integer parkingId;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parkingId")
    @NotFound(action=NotFoundAction.IGNORE)
	private ParkingInfo parkingInfo;
	
	@OneToMany(mappedBy = "parkingAccount", cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
    @NotFound(action=NotFoundAction.IGNORE)
	private List<SharingPlatformAuthorization> sharingPlatformAuthorizationList;

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

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountPassword() {
		return accountPassword;
	}

	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
	}

	public ParkingInfo getParkingInfo() {
		return parkingInfo;
	}

	public void setParkingInfo(ParkingInfo parkingInfo) {
		this.parkingInfo = parkingInfo;
	}

	public List<SharingPlatformAuthorization> getSharingPlatformAuthorizationList() {
		return sharingPlatformAuthorizationList;
	}

	public void setSharingPlatformAuthorizationList(List<SharingPlatformAuthorization> sharingPlatformAuthorizationList) {
		this.sharingPlatformAuthorizationList = sharingPlatformAuthorizationList;
	}

}