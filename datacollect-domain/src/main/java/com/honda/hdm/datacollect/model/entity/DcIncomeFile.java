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
@Table(name = "DC_INCOME_FILE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DcIncomeFile.findAll", query = "SELECT d FROM DcIncomeFile d")
    , @NamedQuery(name = "DcIncomeFile.findById", query = "SELECT d FROM DcIncomeFile d WHERE d.id = :id")
    , @NamedQuery(name = "DcIncomeFile.findByFileName", query = "SELECT d FROM DcIncomeFile d WHERE d.fileName = :fileName")
    , @NamedQuery(name = "DcIncomeFile.findByLength", query = "SELECT d FROM DcIncomeFile d WHERE d.length = :length")
    , @NamedQuery(name = "DcIncomeFile.findByCreateTimestamp", query = "SELECT d FROM DcIncomeFile d WHERE d.createTimestamp = :createTimestamp")
    , @NamedQuery(name = "DcIncomeFile.findByUpdateTimestamp", query = "SELECT d FROM DcIncomeFile d WHERE d.updateTimestamp = :updateTimestamp")})
public class DcIncomeFile extends RecordStatusableEntry implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "seq", sequenceName = "dcif_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "seq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, precision = 38, scale = 0)
    private BigDecimal id;
    @Basic(optional = false)
    @Column(name = "FILE_NAME", nullable = false, length = 255)
    private String fileName;
    @Column(name = "LENGTH")
    private BigInteger length;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dcIncomeFileId")
    private List<DcInFileLogError> dcInFileLogErrorList;
    @JoinColumn(name = "DC_DEALER_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private DcDealer dcDealerId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dcIncomeFileId")
    private List<DcSvcOrderVin> dcSvcOrderVinList;

    public DcIncomeFile() {
    }

    public DcIncomeFile(BigDecimal id) {
        this.id = id;
    }

    public DcIncomeFile(BigDecimal id, String fileName) {
        this.id = id;
        this.fileName = fileName;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public BigInteger getLength() {
        return length;
    }

    public void setLength(BigInteger length) {
        this.length = length;
    }

    @XmlTransient
    public List<DcInFileLogError> getDcInFileLogErrorList() {
        return dcInFileLogErrorList;
    }

    public void setDcInFileLogErrorList(List<DcInFileLogError> dcInFileLogErrorList) {
        this.dcInFileLogErrorList = dcInFileLogErrorList;
    }

    public DcDealer getDcDealerId() {
        return dcDealerId;
    }

    public void setDcDealerId(DcDealer dcDealerId) {
        this.dcDealerId = dcDealerId;
    }

    @XmlTransient
    public List<DcSvcOrderVin> getDcSvcOrderVinList() {
        return dcSvcOrderVinList;
    }

    public void setDcSvcOrderVinList(List<DcSvcOrderVin> dcSvcOrderVinList) {
        this.dcSvcOrderVinList = dcSvcOrderVinList;
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
        if (!(object instanceof DcIncomeFile)) {
            return false;
        }
        DcIncomeFile other = (DcIncomeFile) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.honda.hdm.datacollect.model.entity.DcIncomeFile[ id=" + id + " ]";
    }
    
}
