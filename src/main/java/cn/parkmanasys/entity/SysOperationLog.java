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
@Table(name = "SysOperationLog", schema = "PARKING")
public class SysOperationLog implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sequence")
	@SequenceGenerator(name="sequence", sequenceName="SYSOPERATIONLOG_ID_seq")
//	@GeneratedValue(generator="increment")
//	@GenericGenerator(name = "increment", strategy = "increment")
	private Integer id;
	
//	@Column(name="USER_ID")
//	private Integer userId;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USER_ID")
    @NotFound(action=NotFoundAction.IGNORE)
	private ParkingAccount user;
	
	@Column(name="OPERATE_TIME")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JSONField(format="yyyy-MM-dd")
	private Date operationTime;
	
	@Column(name="OPERATION_PASS")
	private String operationPass;
	
	@Column(name="OPERATION_TYPE")
	private String operationType;
	
	@Column(name="OPERATION_DESC")
	private String operationDesc;
	
	@Column(name="OPERATION_CONTENT")
	private String operationContent;

	@Column(name="CREATE_TIME")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date creationTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ParkingAccount getUser() {
		return user;
	}

	public void setUser(ParkingAccount user) {
		this.user = user;
	}

	public Date getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}

	public String getOperationPass() {
		return operationPass;
	}

	public void setOperationPass(String operationPass) {
		this.operationPass = operationPass;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getOperationDesc() {
		return operationDesc;
	}

	public void setOperationDesc(String operationDesc) {
		this.operationDesc = operationDesc;
	}

	public String getOperationContent() {
		return operationContent;
	}

	public void setOperationContent(String operationContent) {
		this.operationContent = operationContent;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

}
