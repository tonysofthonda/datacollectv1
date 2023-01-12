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
import javax.persistence.ManyToOne;
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
@Table(name = "DC_CITY", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"NAME"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DcCity.findAll", query = "SELECT d FROM DcCity d")
    , @NamedQuery(name = "DcCity.findById", query = "SELECT d FROM DcCity d WHERE d.id = :id")
    , @NamedQuery(name = "DcCity.findByName", query = "SELECT d FROM DcCity d WHERE d.name = :name")
    , @NamedQuery(name = "DcCity.findByDescription", query = "SELECT d FROM DcCity d WHERE d.description = :description")
    , @NamedQuery(name = "DcCity.findByCreateTimestamp", query = "SELECT d FROM DcCity d WHERE d.createTimestamp = :createTimestamp")
    , @NamedQuery(name = "DcCity.findByUpdateTimestamp", query = "SELECT d FROM DcCity d WHERE d.updateTimestamp = :updateTimestamp")})
public class DcCity extends RecordStatusableEntry implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "seq", sequenceName = "dccity_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "seq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, precision = 38, scale = 0)
    private BigDecimal id;
    @Basic(optional = false)
    @Column(name = "NAME", nullable = false, length = 50)
    private String name;
    @Column(name = "DESCRIPTION", length = 255)
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dcCityId")
    private List<DcDealer> dcDealerList;
    @JoinColumn(name = "DC_STATE_ID", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false)
    private DcState dcStateId;

    public DcCity() {
    }

    public DcCity(BigDecimal id) {
        this.id = id;
    }

    public DcCity(BigDecimal id, String name) {
        this.id = id;
        this.name = name;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public List<DcDealer> getDcDealerList() {
        return dcDealerList;
    }

    public void setDcDealerList(List<DcDealer> dcDealerList) {
        this.dcDealerList = dcDealerList;
    }

    public DcState getDcStateId() {
        return dcStateId;
    }

    public void setDcStateId(DcState dcStateId) {
        this.dcStateId = dcStateId;
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
        if (!(object instanceof DcCity)) {
            return false;
        }
        DcCity other = (DcCity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.honda.hdm.datacollect.model.entity.DcCity[ id=" + id + " ]";
    }
    
}
