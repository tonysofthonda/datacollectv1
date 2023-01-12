package com.honda.hdm.datacollect.model.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.honda.hdm.datacollect.model.entity.base.RecordStatusableEntry;

@Entity
@Table(name = "DC_ORDER_PROCESS")
@XmlRootElement
public class DcOrderProcess extends RecordStatusableEntry implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "seq", sequenceName = "dc_order_process_id_seq", allocationSize = 1)
	@GeneratedValue(generator = "seq", strategy = GenerationType.SEQUENCE)
	@Basic(optional = false)
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "SVC_ORDER_NUM", nullable = false)
	private String orderNumber;

	@Column(name = "VIN")
	private String vin;

	@Column(name = "STATUS", nullable = false)
	private String status;

	@OneToOne(optional = true)
	@JoinColumn(name = "DC_INCOME_FILE_ERROR", referencedColumnName = "ID", nullable = true)
	private DcIncomeFile incomeFileError;

	@OneToOne(optional = true)
	@JoinColumn(name = "DC_INCOME_FILE_CORRECT", referencedColumnName = "ID", nullable = true)
	private DcIncomeFile incomeFileCorrect;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public DcIncomeFile getIncomeFileError() {
		return incomeFileError;
	}

	public void setIncomeFileError(DcIncomeFile incomeFileError) {
		this.incomeFileError = incomeFileError;
	}

	public DcIncomeFile getIncomeFileCorrect() {
		return incomeFileCorrect;
	}

	public void setIncomeFileCorrect(DcIncomeFile incomeFileCorrect) {
		this.incomeFileCorrect = incomeFileCorrect;
	}

	@Override
	public String toString() {
		return "DcOrderProcess [id=" + id + ", orderNumber=" + orderNumber + ", vin=" + vin + ", status=" + status
				+ ", incomeFileError=" + incomeFileError + ", incomeFileCorrect=" + incomeFileCorrect + "]";
	}
}
