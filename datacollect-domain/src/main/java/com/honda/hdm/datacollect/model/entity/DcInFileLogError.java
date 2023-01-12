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
@Table(name = "DC_IN_FILE_LOG_ERROR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DcInFileLogError.findAll", query = "SELECT d FROM DcInFileLogError d")
    , @NamedQuery(name = "DcInFileLogError.findById", query = "SELECT d FROM DcInFileLogError d WHERE d.id = :id")
    , @NamedQuery(name = "DcInFileLogError.findByDescription", query = "SELECT d FROM DcInFileLogError d WHERE d.description = :description")
    , @NamedQuery(name = "DcInFileLogError.findByCreateTimestamp", query = "SELECT d FROM DcInFileLogError d WHERE d.createTimestamp = :createTimestamp")
    , @NamedQuery(name = "DcInFileLogError.findByUpdateTimestamp", query = "SELECT d FROM DcInFileLogError d WHERE d.updateTimestamp = :updateTimestamp")})
public class DcInFileLogError extends RecordStatusableEntry implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "seq", sequenceName = "dferr_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "seq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, precision = 38, scale = 0)
    private BigDecimal id;
    @Column(name = "DESCRIPTION", length = 255)
    private String description;
    /*@JoinColumn(name = "DC_ERROR_CODE_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private DcErrorCode dcErrorCodeId;*/
    @JoinColumn(name = "DC_INCOME_FILE_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private DcIncomeFile dcIncomeFileId;

    public DcInFileLogError() {
    }

    public DcInFileLogError(BigDecimal id) {
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

/*
    public DcErrorCode getDcErrorCodeId() {
        return dcErrorCodeId;
    }

    public void setDcErrorCodeId(DcErrorCode dcErrorCodeId) {
        this.dcErrorCodeId = dcErrorCodeId;
    }
*/
    public DcIncomeFile getDcIncomeFileId() {
        return dcIncomeFileId;
    }

    public void setDcIncomeFileId(DcIncomeFile dcIncomeFileId) {
        this.dcIncomeFileId = dcIncomeFileId;
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
        if (!(object instanceof DcInFileLogError)) {
            return false;
        }
        DcInFileLogError other = (DcInFileLogError) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.honda.hdm.datacollect.model.entity.DcInFileLogError[ id=" + id + " ]";
    }
    
}
