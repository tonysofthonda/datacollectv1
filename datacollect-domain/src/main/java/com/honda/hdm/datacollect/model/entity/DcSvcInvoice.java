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
@Table(name = "DC_SVC_INVOICE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DcSvcInvoice.findAll", query = "SELECT d FROM DcSvcInvoice d")
    , @NamedQuery(name = "DcSvcInvoice.findById", query = "SELECT d FROM DcSvcInvoice d WHERE d.id = :id")
    , @NamedQuery(name = "DcSvcInvoice.findByFolio", query = "SELECT d FROM DcSvcInvoice d WHERE d.folio = :folio")
    , @NamedQuery(name = "DcSvcInvoice.findByDate", query = "SELECT d FROM DcSvcInvoice d WHERE d.date = :date")
    , @NamedQuery(name = "DcSvcInvoice.findByCustomerName", query = "SELECT d FROM DcSvcInvoice d WHERE d.customerName = :customerName")
    , @NamedQuery(name = "DcSvcInvoice.findByAddrPostcode", query = "SELECT d FROM DcSvcInvoice d WHERE d.addrPostcode = :addrPostcode")
    , @NamedQuery(name = "DcSvcInvoice.findByTotal", query = "SELECT d FROM DcSvcInvoice d WHERE d.total = :total")
    , @NamedQuery(name = "DcSvcInvoice.findByCustomerRfc", query = "SELECT d FROM DcSvcInvoice d WHERE d.customerRfc = :customerRfc")
    , @NamedQuery(name = "DcSvcInvoice.findByInvoiceUuid", query = "SELECT d FROM DcSvcInvoice d WHERE d.invoiceUuid = :invoiceUuid")
    , @NamedQuery(name = "DcSvcInvoice.findByCreateTimestamp", query = "SELECT d FROM DcSvcInvoice d WHERE d.createTimestamp = :createTimestamp")
    , @NamedQuery(name = "DcSvcInvoice.findByUpdateTimestamp", query = "SELECT d FROM DcSvcInvoice d WHERE d.updateTimestamp = :updateTimestamp")})
public class DcSvcInvoice extends RecordStatusableEntry implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "seq", sequenceName = "dsvi_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "seq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, precision = 38, scale = 0)
    private BigDecimal id;
    @Column(name = "FOLIO", length = 10)
    private String folio;
    @Column(name = "\"DATE\"")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Column(name = "CUSTOMER_NAME", length = 100)
    private String customerName;
    @Column(name = "ADDR_POSTCODE", length = 6)
    private String addrPostcode;
    @Column(name = "TOTAL", precision = 13, scale = 4)
    private BigDecimal total;
    @Column(name = "CUSTOMER_RFC", length = 15)
    private String customerRfc;
    @Column(name = "INVOICE_UUID", length = 32)
    private String invoiceUuid;
    @JoinColumn(name = "DC_INVOICE_STATUS_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private DcInvoiceStatus dcInvoiceStatusId;
    @JoinColumn(name = "DC_SVC_ORDER_VIN_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private DcSvcOrderVin dcSvcOrderVinId;

    public DcSvcInvoice() {
    }

    public DcSvcInvoice(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddrPostcode() {
        return addrPostcode;
    }

    public void setAddrPostcode(String addrPostcode) {
        this.addrPostcode = addrPostcode;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getCustomerRfc() {
        return customerRfc;
    }

    public void setCustomerRfc(String customerRfc) {
        this.customerRfc = customerRfc;
    }

    public String getInvoiceUuid() {
        return invoiceUuid;
    }

    public void setInvoiceUuid(String invoiceUuid) {
        this.invoiceUuid = invoiceUuid;
    }

    public DcInvoiceStatus getDcInvoiceStatusId() {
        return dcInvoiceStatusId;
    }

    public void setDcInvoiceStatusId(DcInvoiceStatus dcInvoiceStatusId) {
        this.dcInvoiceStatusId = dcInvoiceStatusId;
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
        if (!(object instanceof DcSvcInvoice)) {
            return false;
        }
        DcSvcInvoice other = (DcSvcInvoice) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.honda.hdm.datacollect.model.entity.DcSvcInvoice[ id=" + id + " ]";
    }
    
}
