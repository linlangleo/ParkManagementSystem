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
@Table(name = "SYS_INTERFACE_MANAGER_DETAILS", schema = "PARKING")
public class SysInterfaceManagerDetails implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sequence")
	@SequenceGenerator(name="sequence", sequenceName="SYSINTERFACEMANADET_ID_seq")
//	@GeneratedValue(generator="increment")
//	@GenericGenerator(name = "increment", strategy = "increment")
    private Integer id;

	@Column(name="manager_Id")
    private Integer managerId;

	@Column(name="time")
    private String time;

	@Column(name="ip")
    private String ip;

	@Column(name="status")
    private String status;

	@Column(name="inface_Url")
    private String infaceUrl;

	@Column(name="request")
    private String request;

	@Column(name="response")
    private String response;

	@Column(name="start_Time")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date startTime;

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

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInfaceUrl() {
		return infaceUrl;
	}

	public void setInfaceUrl(String infaceUrl) {
		this.infaceUrl = infaceUrl;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
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