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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author VJC80439
 */
@Entity
@Table(name = "DC_SVC_TOTAL", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"DC_SVC_ORDER_VIN_ID"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DcSvcTotal.findAll", query = "SELECT d FROM DcSvcTotal d")
    , @NamedQuery(name = "DcSvcTotal.findByDstoId", query = "SELECT d FROM DcSvcTotal d WHERE d.dstoId = :dstoId")
    , @NamedQuery(name = "DcSvcTotal.findByLaborTotal", query = "SELECT d FROM DcSvcTotal d WHERE d.laborTotal = :laborTotal")
    , @NamedQuery(name = "DcSvcTotal.findByPartTotal", query = "SELECT d FROM DcSvcTotal d WHERE d.partTotal = :partTotal")
    , @NamedQuery(name = "DcSvcTotal.findByDiscountTotal", query = "SELECT d FROM DcSvcTotal d WHERE d.discountTotal = :discountTotal")
    , @NamedQuery(name = "DcSvcTotal.findBySubtotal", query = "SELECT d FROM DcSvcTotal d WHERE d.subtotal = :subtotal")
    , @NamedQuery(name = "DcSvcTotal.findByTaxTotal", query = "SELECT d FROM DcSvcTotal d WHERE d.taxTotal = :taxTotal")
    , @NamedQuery(name = "DcSvcTotal.findByNetTotal", query = "SELECT d FROM DcSvcTotal d WHERE d.netTotal = :netTotal")
    , @NamedQuery(name = "DcSvcTotal.findByCreateTimestamp", query = "SELECT d FROM DcSvcTotal d WHERE d.createTimestamp = :createTimestamp")
    , @NamedQuery(name = "DcSvcTotal.findByUpdateTimestamp", query = "SELECT d FROM DcSvcTotal d WHERE d.updateTimestamp = :updateTimestamp")})
public class DcSvcTotal extends RecordStatusableEntry implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "seq", sequenceName = "dsto_dsto_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "seq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "DSTO_ID", nullable = false, precision = 38, scale = 0)
    private BigDecimal dstoId;
    @Column(name = "LABOR_TOTAL", precision = 13, scale = 4)
    private BigDecimal laborTotal;
    @Column(name = "PART_TOTAL", precision = 13, scale = 4)
    private BigDecimal partTotal;
    @Column(name = "DISCOUNT_TOTAL", precision = 13, scale = 4)
    private BigDecimal discountTotal;
    @Column(name = "SUBTOTAL", precision = 13, scale = 4)
    private BigDecimal subtotal;
    @Column(name = "TAX_TOTAL", precision = 13, scale = 4)
    private BigDecimal taxTotal;
    @Column(name = "NET_TOTAL", precision = 13, scale = 4)
    private BigDecimal netTotal;
    @JoinColumn(name = "DC_SVC_ORDER_VIN_ID", referencedColumnName = "ID", nullable = false)
    @OneToOne(optional = false)
    private DcSvcOrderVin dcSvcOrderVinId;

    public DcSvcTotal() {
    }

    public DcSvcTotal(BigDecimal dstoId) {
        this.dstoId = dstoId;
    }

    public BigDecimal getDstoId() {
        return dstoId;
    }

    public void setDstoId(BigDecimal dstoId) {
        this.dstoId = dstoId;
    }

    public BigDecimal getLaborTotal() {
        return laborTotal;
    }

    public void setLaborTotal(BigDecimal laborTotal) {
        this.laborTotal = laborTotal;
    }

    public BigDecimal getPartTotal() {
        return partTotal;
    }

    public void setPartTotal(BigDecimal partTotal) {
        this.partTotal = partTotal;
    }

    public BigDecimal getDiscountTotal() {
        return discountTotal;
    }

    public void setDiscountTotal(BigDecimal discountTotal) {
        this.discountTotal = discountTotal;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTaxTotal() {
        return taxTotal;
    }

    public void setTaxTotal(BigDecimal taxTotal) {
        this.taxTotal = taxTotal;
    }

    public BigDecimal getNetTotal() {
        return netTotal;
    }

    public void setNetTotal(BigDecimal netTotal) {
        this.netTotal = netTotal;
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
        hash += (dstoId != null ? dstoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DcSvcTotal)) {
            return false;
        }
        DcSvcTotal other = (DcSvcTotal) object;
        if ((this.dstoId == null && other.dstoId != null) || (this.dstoId != null && !this.dstoId.equals(other.dstoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.honda.hdm.datacollect.model.entity.DcSvcTotal[ dstoId=" + dstoId + " ]";
    }
    
}
