/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.model.entity;

import com.honda.hdm.datacollect.model.entity.base.RecordStatusableEntry;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
@Table(name = "DC_SVC_PART")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DcSvcPart.findAll", query = "SELECT d FROM DcSvcPart d")
    , @NamedQuery(name = "DcSvcPart.findById", query = "SELECT d FROM DcSvcPart d WHERE d.id = :id")
    , @NamedQuery(name = "DcSvcPart.findByPartNumber", query = "SELECT d FROM DcSvcPart d WHERE d.partNumber = :partNumber")
    , @NamedQuery(name = "DcSvcPart.findByDescription", query = "SELECT d FROM DcSvcPart d WHERE d.description = :description")
    , @NamedQuery(name = "DcSvcPart.findByQuantity", query = "SELECT d FROM DcSvcPart d WHERE d.quantity = :quantity")
    , @NamedQuery(name = "DcSvcPart.findByListPrice", query = "SELECT d FROM DcSvcPart d WHERE d.listPrice = :listPrice")
    , @NamedQuery(name = "DcSvcPart.findBySubtotal", query = "SELECT d FROM DcSvcPart d WHERE d.subtotal = :subtotal")
    , @NamedQuery(name = "DcSvcPart.findByDiscountPercent", query = "SELECT d FROM DcSvcPart d WHERE d.discountPercent = :discountPercent")
    , @NamedQuery(name = "DcSvcPart.findByDiscountAmount", query = "SELECT d FROM DcSvcPart d WHERE d.discountAmount = :discountAmount")
    , @NamedQuery(name = "DcSvcPart.findByNetTotal", query = "SELECT d FROM DcSvcPart d WHERE d.netTotal = :netTotal")
    , @NamedQuery(name = "DcSvcPart.findByCreateTimestamp", query = "SELECT d FROM DcSvcPart d WHERE d.createTimestamp = :createTimestamp")
    , @NamedQuery(name = "DcSvcPart.findByUpdateTimestamp", query = "SELECT d FROM DcSvcPart d WHERE d.updateTimestamp = :updateTimestamp")})
public class DcSvcPart extends RecordStatusableEntry implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "seq", sequenceName = "dspr_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "seq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, precision = 38, scale = 0)
    private BigDecimal id;
    @Basic(optional = false)
    @Column(name = "PART_NUMBER", nullable = false, length = 20)
    private String partNumber;
    @Column(name = "DESCRIPTION", length = 255)
    private String description;
    @Column(name = "QUANTITY")
    private BigInteger quantity;
    @Column(name = "LIST_PRICE", precision = 13, scale = 4)
    private BigDecimal listPrice;
    @Column(name = "SUBTOTAL", precision = 13, scale = 4)
    private BigDecimal subtotal;
    @Column(name = "DISCOUNT_PERCENT", precision = 5, scale = 2)
    private BigDecimal discountPercent;
    @Column(name = "DISCOUNT_AMOUNT", precision = 13, scale = 4)
    private BigDecimal discountAmount;
    @Column(name = "NET_TOTAL", precision = 13, scale = 4)
    private BigDecimal netTotal;
    @JoinColumn(name = "DC_SVC_ORDER_VIN_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private DcSvcOrderVin dcSvcOrderVinId;

    public DcSvcPart() {
    }

    public DcSvcPart(BigDecimal id) {
        this.id = id;
    }

    public DcSvcPart(BigDecimal id, String partNumber) {
        this.id = id;
        this.partNumber = partNumber;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigInteger getQuantity() {
        return quantity;
    }

    public void setQuantity(BigInteger quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
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
        if (!(object instanceof DcSvcPart)) {
            return false;
        }
        DcSvcPart other = (DcSvcPart) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.honda.hdm.datacollect.model.entity.DcSvcPart[ id=" + id + " ]";
    }
    
}
