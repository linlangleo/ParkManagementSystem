package cn.parkmanasys.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

@Entity
@Table(name = "SYS_INTERFACE_MANAGER", schema = "PARKING")
public class SysInterfaceManager implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sequence")
	@SequenceGenerator(name="sequence", sequenceName="SYSINTERFACEMANAGER_id_seq")
//	@GeneratedValue(generator="increment")
//	@GenericGenerator(name = "increment", strategy = "increment")
	private Integer id;

	@Column(name="interface_Name")
    private String interfaceName;

	@Column(name="method_Name")
    private String methodName;

	@Column(name="type")
    private String type;

	@Column(name="status")
    private String status;

	@Column(name="create_Time")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

	@Column(name="update_Time")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

	@Column(name="create_By")
    private String createBy;

	@Column(name="update_By")
    private String updateBy;

	@Column(name="remark")
    private String remark;

	@Column(name="mark")
    private String mark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}
    
}