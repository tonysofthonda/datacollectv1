/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.model.entity;

import com.honda.hdm.datacollect.model.entity.base.RecordStatusableEntry;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author VJC80439
 */
@Entity
@Table(name = "DC_ORDER_TYPE", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"CODE"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DcOrderType.findAll", query = "SELECT d FROM DcOrderType d"),
    @NamedQuery(name = "DcOrderType.findById", query = "SELECT d FROM DcOrderType d WHERE d.id = :id"),
    @NamedQuery(name = "DcOrderType.findByCode", query = "SELECT d FROM DcOrderType d WHERE d.code = :code"),
    @NamedQuery(name = "DcOrderType.findByDescription", query = "SELECT d FROM DcOrderType d WHERE d.description = :description"),
    @NamedQuery(name = "DcOrderType.findByCreateTimestamp", query = "SELECT d FROM DcOrderType d WHERE d.createTimestamp = :createTimestamp"),
    @NamedQuery(name = "DcOrderType.findByUpdateTimestamp", query = "SELECT d FROM DcOrderType d WHERE d.updateTimestamp = :updateTimestamp")})
public class DcOrderType extends RecordStatusableEntry implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "seq", sequenceName = "dcordt_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "seq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, precision = 38, scale = 0)
    private BigDecimal id;

    @Basic(optional = false)
    @Column(name = "CODE", nullable = false, length = 5)
    private String code;

    @Column(name = "DESCRIPTION", length = 50)
    private String description;

    @Column(name = "SERVICE_TYPE", length = 50)
    private String serviceType;
    
    public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "dcOrderTypeId")
    private List<DcSvcOrderVin> dcSvcOrderVinList;

    @JoinTable(name = "DC_SERVICE_TYPE_X_ORDER_TYPE",
            joinColumns = {
                @JoinColumn(name = "DC_ORDER_TYPE_ID", referencedColumnName = "ID", nullable = false)},
            inverseJoinColumns = {
                @JoinColumn(name = "DC_SERVICE_TYPE_ID", referencedColumnName = "ID", nullable = false)})
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<DcServiceType> dcServicesTypes;

    public DcOrderType() {
    }

    public DcOrderType(BigDecimal id) {
        this.id = id;
    }

    public DcOrderType(BigDecimal id, String code) {
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

    @XmlTransient
    public List<DcSvcOrderVin> getDcSvcOrderVinList() {
        return dcSvcOrderVinList;
    }

    public void setDcSvcOrderVinList(List<DcSvcOrderVin> dcSvcOrderVinList) {
        this.dcSvcOrderVinList = dcSvcOrderVinList;
    }

    @XmlTransient
    public List<DcServiceType> getDcServicesTypes() {
        return dcServicesTypes;
    }

    public void setDcServicesTypes(List<DcServiceType> dcServicesTypes) {
        this.dcServicesTypes = dcServicesTypes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DcOrderType)) {
            return false;
        }
        DcOrderType other = (DcOrderType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.honda.hdm.datacollect.model.entity.DcOrderType[ id=" + id + " ]";
    }

}
