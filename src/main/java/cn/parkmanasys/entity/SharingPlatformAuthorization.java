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

@Entity
@Table(name = "SharingPlatformAuthorization", schema = "PARKING")
public class SharingPlatformAuthorization implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sequence")
	@SequenceGenerator(name="sequence", sequenceName="spAuthorization_id_seq")
//	@GeneratedValue(generator="increment")
//	@GenericGenerator(name = "increment", strategy = "increment")
	private Integer id;
	
	@Column(name="authorizationNumber")
	private String authorizationNumber;
	
	@Column(name="ifAvailable")
	private String ifAvailable;
	
//	@Column(name="parkingAccountUserId")
//	private Integer parkingAccountUserId;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parkingAccountUserId")
    @NotFound(action=NotFoundAction.IGNORE)
	private ParkingAccount parkingAccount;
	
	@Column(name="openDate")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date openDate;
	
	@Column(name="ifClosed")
	private String ifClosed;

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

	public String getAuthorizationNumber() {
		return authorizationNumber;
	}

	public void setAuthorizationNumber(String authorizationNumber) {
		this.authorizationNumber = authorizationNumber;
	}

	public String getIfAvailable() {
		return ifAvailable;
	}

	public void setIfAvailable(String ifAvailable) {
		this.ifAvailable = ifAvailable;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public String getIfClosed() {
		return ifClosed;
	}

	public void setIfClosed(String ifClosed) {
		this.ifClosed = ifClosed;
	}

	public ParkingAccount getParkingAccount() {
		return parkingAccount;
	}

	public void setParkingAccount(ParkingAccount parkingAccount) {
		this.parkingAccount = parkingAccount;
	}

}