package cn.parkmanasys.entity;

import java.math.BigDecimal;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Transactionstatus entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "TRANSACTIONSTATUS", schema = "PARKING")
public class TransactionStatus implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sequence")
	@SequenceGenerator(name="sequence", sequenceName="TransactionStatus_id_seq")
//	@GeneratedValue(generator="increment")
//	@GenericGenerator(name = "increment", strategy = "increment")
	private Integer id;
	
	@Column(name="transactionStatusName")
	private String transactionStatusName;

	@ManyToMany(targetEntity=Transaction.class,cascade=CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinTable(name = "TransactionStatusInfo", joinColumns = { @JoinColumn(name = "TransactionStatusId",nullable = true, updatable = true)},inverseJoinColumns = {@JoinColumn(name="TransactionId",nullable = true, updatable = true)})
    private List<Transaction> transactionList;

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

	public String getTransactionStatusName() {
		return transactionStatusName;
	}

	public void setTransactionStatusName(String transactionStatusName) {
		this.transactionStatusName = transactionStatusName;
	}


}