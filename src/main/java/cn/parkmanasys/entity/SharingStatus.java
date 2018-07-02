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

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Sharingstatus entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SHARINGSTATUS", schema = "PARKING")
public class SharingStatus implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sequence")
	@SequenceGenerator(name="sequence", sequenceName="SharingStatus_id_seq")
//	@GeneratedValue(generator="increment")
//	@GenericGenerator(name = "increment", strategy = "increment")
	private Integer id;
	
	@Column(name="statuSname")
	private String statuSname;

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

	public String getStatuSname() {
		return statuSname;
	}

	public void setStatuSname(String statuSname) {
		this.statuSname = statuSname;
	}

}