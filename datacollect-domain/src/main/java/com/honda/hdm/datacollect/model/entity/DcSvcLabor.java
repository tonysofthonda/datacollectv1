/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.model.entity;

import com.honda.hdm.datacollect.model.entity.base.RecordStatusableEntry;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author VJC80439
 */
@Entity
@Table(name = "DC_SVC_LABOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DcSvcLabor.findAll", query = "SELECT d FROM DcSvcLabor d")
    , @NamedQuery(name = "DcSvcLabor.findById", query = "SELECT d FROM DcSvcLabor d WHERE d.id = :id")
    , @NamedQuery(name = "DcSvcLabor.findByDescription", query = "SELECT d FROM DcSvcLabor d WHERE d.description = :description")
    , @NamedQuery(name = "DcSvcLabor.findByHours", query = "SELECT d FROM DcSvcLabor d WHERE d.hours = :hours")
    , @NamedQuery(name = "DcSvcLabor.findByUnitPrice", query = "SELECT d FROM DcSvcLabor d WHERE d.unitPrice = :unitPrice")
    , @NamedQuery(name = "DcSvcLabor.findBySubtotal", query = "SELECT d FROM DcSvcLabor d WHERE d.subtotal = :subtotal")
    , @NamedQuery(name = "DcSvcLabor.findByDiscountPercent", query = "SELECT d FROM DcSvcLabor d WHERE d.discountPercent = :discountPercent")
    , @NamedQuery(name = "DcSvcLabor.findByDiscountAmount", query = "SELECT d FROM DcSvcLabor d WHERE d.discountAmount = :discountAmount")
    , @NamedQuery(name = "DcSvcLabor.findByNetTotal", query = "SELECT d FROM DcSvcLabor d WHERE d.netTotal = :netTotal")
    , @NamedQuery(name = "DcSvcLabor.findByLaborCost", query = "SELECT d FROM DcSvcLabor d WHERE d.laborCost = :laborCost")
    , @NamedQuery(name = "DcSvcLabor.findByDptsTechnician", query = "SELECT d FROM DcSvcLabor d WHERE d.dptsTechnician = :dptsTechnician")
    , @NamedQuery(name = "DcSvcLabor.findByCreateTimestamp", query = "SELECT d FROM DcSvcLabor d WHERE d.createTimestamp = :createTimestamp")
    , @NamedQuery(name = "DcSvcLabor.findByUpdateTimestamp", query = "SELECT d FROM DcSvcLabor d WHERE d.updateTimestamp = :updateTimestamp")})
public class DcSvcLabor extends RecordStatusableEntry implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "seq", sequenceName = "dclab_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "seq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, precision = 38, scale = 0)
    private BigDecimal id;
    @Column(name = "DESCRIPTION", length = 255)
    private String description;
    @Column(name = "HOURS", precision = 5, scale = 2)
    private BigDecimal hours;
    @Column(name = "UNIT_PRICE", precision = 13, scale = 4)
    private BigDecimal unitPrice;
    @Column(name = "SUBTOTAL", precision = 13, scale = 4)
    private BigDecimal subtotal;
    @Column(name = "DISCOUNT_PERCENT", precision = 5, scale = 2)
    private BigDecimal discountPercent;
    @Column(name = "DISCOUNT_AMOUNT", precision = 13, scale = 4)
    private BigDecimal discountAmount;
    @Column(name = "NET_TOTAL", precision = 13, scale = 4)
    private BigDecimal netTotal;
    @Column(name = "LABOR_COST", precision = 13, scale = 4)
    private BigDecimal laborCost;
    @Column(name = "DPTS_TECHNICIAN", length = 7)
    private String dptsTechnician;
    @JoinColumn(name = "DC_OPERATION_CODE_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private DcOperationCode dcOperationCodeId;
    @JoinColumn(name = "DC_SVC_ORDER_VIN_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private DcSvcOrderVin dcSvcOrderVinId;

    public DcSvcLabor() {
    }

    public DcSvcLabor(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getHours() {
        return hours;
    }

    public void setHours(BigDecimal hours) {
        this.hours = hours;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(BigDecimal discountPercent) {
        this.discountPercent = discountPercent;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getNetTotal() {
        return netTotal;
    }

    public void setNetTotal(BigDecimal netTotal) {
        this.netTotal = netTotal;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

    public String getDptsTechnician() {
        return dptsTechnician;
    }

    public void setDptsTechnician(String dptsTechnician) {
        this.dptsTechnician = dptsTechnician;
    }

    public DcOperationCode getDcOperationCodeId() {
        return dcOperationCodeId;
    }

    public void setDcOperationCodeId(DcOperationCode dcOperationCodeId) {
        this.dcOperationCodeId = dcOperationCodeId;
    }

    public DcSvcOrderVin getDcSvcOrderVinId() {
        return dcSvcOrderVinId;
    }

    public void setDcSvcOrderVinId(DcSvcOrderVin dcSvcOrderVinId) {
        this.dcSvcOrderVinId = dcSvcOrderVinId;
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
        if (!(object instanceof DcSvcLabor)) {
            return false;
        }
        DcSvcLabor other = (DcSvcLabor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.honda.hdm.datacollect.model.entity.DcSvcLabor[ id=" + id + " ]";
    }
    
}
