/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.model.entity;

import com.honda.hdm.datacollect.model.entity.base.RecordStatusableEntry;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author VJC80439
 */
@Entity
@Table(name = "DC_OPERATION_CODE", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"CODE"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DcOperationCode.findAll", query = "SELECT d FROM DcOperationCode d")
    , @NamedQuery(name = "DcOperationCode.findById", query = "SELECT d FROM DcOperationCode d WHERE d.id = :id")
    , @NamedQuery(name = "DcOperationCode.findByCode", query = "SELECT d FROM DcOperationCode d WHERE d.code = :code")
    , @NamedQuery(name = "DcOperationCode.findByDescription", query = "SELECT d FROM DcOperationCode d WHERE d.description = :description")
    , @NamedQuery(name = "DcOperationCode.findByCreateTimestamp", query = "SELECT d FROM DcOperationCode d WHERE d.createTimestamp = :createTimestamp")
    , @NamedQuery(name = "DcOperationCode.findByUpdateTimestamp", query = "SELECT d FROM DcOperationCode d WHERE d.updateTimestamp = :updateTimestamp")})
public class DcOperationCode extends RecordStatusableEntry implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @SequenceGenerator(name = "seq", sequenceName = "dopc_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "seq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, precision = 38, scale = 0)
    private BigDecimal id;
    
    @Basic(optional = false)
    @Column(name = "CODE", nullable = false, length = 10)
    private String code;
    
    @Column(name = "DESCRIPTION", length = 255)
    private String description;
    
    @Basic(optional = false)
    @Column(name = "SERVICE_TYPE", nullable = false)
    private String serviceType;

	public DcOperationCode() {
    }

    public DcOperationCode(BigDecimal id) {
        this.id = id;
    }

    public DcOperationCode(BigDecimal id, String code) {
        this.id = id;
        this.code = code;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof DcOperationCode)) {
            return false;
        }
        DcOperationCode other = (DcOperationCode) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.honda.hdm.datacollect.model.entity.DcOperationCode[ id=" + id + " ]";
    }
    
}
