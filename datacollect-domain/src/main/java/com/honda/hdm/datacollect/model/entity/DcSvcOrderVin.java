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
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author VJC80439
 */
@Entity
@Table(name = "DC_SVC_ORDER_VIN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DcSvcOrderVin.findAll", query = "SELECT d FROM DcSvcOrderVin d")
    , @NamedQuery(name = "DcSvcOrderVin.findById", query = "SELECT d FROM DcSvcOrderVin d WHERE d.id = :id")
    , @NamedQuery(name = "DcSvcOrderVin.findBySvcOrderNum", query = "SELECT d FROM DcSvcOrderVin d WHERE d.svcOrderNum = :svcOrderNum")
    , @NamedQuery(name = "DcSvcOrderVin.findByMileage", query = "SELECT d FROM DcSvcOrderVin d WHERE d.mileage = :mileage")
    , @NamedQuery(name = "DcSvcOrderVin.findByVin", query = "SELECT d FROM DcSvcOrderVin d WHERE d.vin = :vin")
    , @NamedQuery(name = "DcSvcOrderVin.findByLicensePlate", query = "SELECT d FROM DcSvcOrderVin d WHERE d.licensePlate = :licensePlate")
    , @NamedQuery(name = "DcSvcOrderVin.findByEin", query = "SELECT d FROM DcSvcOrderVin d WHERE d.ein = :ein")
    , @NamedQuery(name = "DcSvcOrderVin.findByDptsAssessor", query = "SELECT d FROM DcSvcOrderVin d WHERE d.dptsAssessor = :dptsAssessor")
    , @NamedQuery(name = "DcSvcOrderVin.findByDateOpen", query = "SELECT d FROM DcSvcOrderVin d WHERE d.dateOpen = :dateOpen")
    , @NamedQuery(name = "DcSvcOrderVin.findByDateClose", query = "SELECT d FROM DcSvcOrderVin d WHERE d.dateClose = :dateClose")
    , @NamedQuery(name = "DcSvcOrderVin.findByCreateTimestamp", query = "SELECT d FROM DcSvcOrderVin d WHERE d.createTimestamp = :createTimestamp")
    , @NamedQuery(name = "DcSvcOrderVin.findByUpdateTimestamp", query = "SELECT d FROM DcSvcOrderVin d WHERE d.updateTimestamp = :updateTimestamp")})
public class DcSvcOrderVin extends RecordStatusableEntry implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "seq", sequenceName = "dcsov_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "seq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, precision = 38, scale = 0)
    private BigDecimal id;
    @Column(name = "SVC_ORDER_NUM", length = 20)
    private String svcOrderNum;
    @Column(name = "MILEAGE")
    private BigInteger mileage;
    @Column(name = "VIN", length = 17)
    private String vin;
    @Column(name = "LICENSE_PLATE", length = 15)
    private String licensePlate;
    @Column(name = "EIN", length = 15)
    private String ein;
    @Column(name = "DPTS_ASSESSOR", length = 7)
    private String dptsAssessor;
    @Column(name = "DATE_OPEN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOpen;
    @Column(name = "DATE_CLOSE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateClose;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dcSvcOrderVinId")
    private List<DcSvcInvoice> dcSvcInvoiceList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dcSvcOrderVinId")
    private List<DcSvcLabor> dcSvcLaborList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "dcSvcOrderVinId")
    private DcSvcTotal dcSvcTotal;
    @JoinColumn(name = "DC_CLIENT_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private DcClient dcClientId;
    @JoinColumn(name = "DC_DEALER_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private DcDealer dcDealerId;
    @JoinColumn(name = "DC_INCOME_FILE_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private DcIncomeFile dcIncomeFileId;
    @JoinColumn(name = "DC_MODEL_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private DcModel dcModelId;
    @JoinColumn(name = "DC_ORDER_STATUS_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private DcOrderStatus dcOrderStatusId;
    @JoinColumn(name = "DC_ORDER_TYPE_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private DcOrderType dcOrderTypeId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dcSvcOrderVinId")
    private List<DcSvcPart> dcSvcPartList;

    public DcSvcOrderVin() {
    }

    public DcSvcOrderVin(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getSvcOrderNum() {
        return svcOrderNum;
    }

    public void setSvcOrderNum(String svcOrderNum) {
        this.svcOrderNum = svcOrderNum;
    }

    public BigInteger getMileage() {
        return mileage;
    }

    public void setMileage(BigInteger mileage) {
        this.mileage = mileage;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getEin() {
        return ein;
    }

    public void setEin(String ein) {
        this.ein = ein;
    }

    public String getDptsAssessor() {
        return dptsAssessor;
    }

    public void setDptsAssessor(String dptsAssessor) {
        this.dptsAssessor = dptsAssessor;
    }

    public Date getDateOpen() {
        return dateOpen;
    }

    public void setDateOpen(Date dateOpen) {
        this.dateOpen = dateOpen;
    }

    public Date getDateClose() {
        return dateClose;
    }

    public void setDateClose(Date dateClose) {
        this.dateClose = dateClose;
    }

    @XmlTransient
    public List<DcSvcInvoice> getDcSvcInvoiceList() {
        return dcSvcInvoiceList;
    }

    public void setDcSvcInvoiceList(List<DcSvcInvoice> dcSvcInvoiceList) {
        this.dcSvcInvoiceList = dcSvcInvoiceList;
    }

    @XmlTransient
    public List<DcSvcLabor> getDcSvcLaborList() {
        return dcSvcLaborList;
    }

    public void setDcSvcLaborList(List<DcSvcLabor> dcSvcLaborList) {
        this.dcSvcLaborList = dcSvcLaborList;
    }

    public DcSvcTotal getDcSvcTotal() {
        return dcSvcTotal;
    }

    public void setDcSvcTotal(DcSvcTotal dcSvcTotal) {
        this.dcSvcTotal = dcSvcTotal;
    }

    public DcClient getDcClientId() {
        return dcClientId;
    }

    public void setDcClientId(DcClient dcClientId) {
        this.dcClientId = dcClientId;
    }

    public DcDealer getDcDealerId() {
        return dcDealerId;
    }

    public void setDcDealerId(DcDealer dcDealerId) {
        this.dcDealerId = dcDealerId;
    }

    public DcIncomeFile getDcIncomeFileId() {
        return dcIncomeFileId;
    }

    public void setDcIncomeFileId(DcIncomeFile dcIncomeFileId) {
        this.dcIncomeFileId = dcIncomeFileId;
    }

    public DcModel getDcModelId() {
        return dcModelId;
    }

    public void setDcModelId(DcModel dcModelId) {
        this.dcModelId = dcModelId;
    }

    public DcOrderStatus getDcOrderStatusId() {
        return dcOrderStatusId;
    }

    public void setDcOrderStatusId(DcOrderStatus dcOrderStatusId) {
        this.dcOrderStatusId = dcOrderStatusId;
    }

    public DcOrderType getDcOrderTypeId() {
        return dcOrderTypeId;
    }

    public void setDcOrderTypeId(DcOrderType dcOrderTypeId) {
        this.dcOrderTypeId = dcOrderTypeId;
    }

    @XmlTransient
    public List<DcSvcPart> getDcSvcPartList() {
        return dcSvcPartList;
    }

    public void setDcSvcPartList(List<DcSvcPart> dcSvcPartList) {
        this.dcSvcPartList = dcSvcPartList;
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
        if (!(object instanceof DcSvcOrderVin)) {
            return false;
        }
        DcSvcOrderVin other = (DcSvcOrderVin) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.honda.hdm.datacollect.model.entity.DcSvcOrderVin[ id=" + id + " ]";
    }
    
}
