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
 * Feedbackanddispute entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "FEEDBACKANDDISPUTE", schema = "PARKING")
public class FeedbackAndDispute implements java.io.Serializable {
	

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sequence")
	@SequenceGenerator(name="sequence", sequenceName="FeedbackAndDispute_id_seq")
//	@GeneratedValue(generator="increment")
//	@GenericGenerator(name = "increment", strategy = "increment")
	private Integer id;
	
//	@Column(name="feedbackCarOwnerId")
//	private Integer feedbackCarOwnerId;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="feedbackCarOwnerId")
    @NotFound(action=NotFoundAction.IGNORE)
	private CarOwnerUser carOwnerUser;
	
	@Column(name="title")
	private String title;
	
	@Column(name="description")
	private String description;
	

	@Column(name="submitDate")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date submitDate;
	
//	@Column(name="sharingPlatformId")
//	private Integer sharingPlatformId;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="sharingPlatformId")
    @NotFound(action=NotFoundAction.IGNORE)
	private SharingPlatform sharingPlatform;

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	public CarOwnerUser getCarOwnerUser() {
		return carOwnerUser;
	}

	public void setCarOwnerUser(CarOwnerUser carOwnerUser) {
		this.carOwnerUser = carOwnerUser;
	}

	public SharingPlatform getSharingPlatform() {
		return sharingPlatform;
	}

	public void setSharingPlatform(SharingPlatform sharingPlatform) {
		this.sharingPlatform = sharingPlatform;
	}
	

}