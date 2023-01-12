/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.model.entity;

import com.honda.hdm.datacollect.model.entity.base.RecordStatusableEntry;
import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author VJC80377
 */
@Entity
@Table(name = "DC_FACILITY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DcFacility.findAll", query = "SELECT d FROM DcFacility d")
    , @NamedQuery(name = "DcFacility.findById", query = "SELECT d FROM DcFacility d WHERE d.id = :id")
    , @NamedQuery(name = "DcFacility.findByConcept", query = "SELECT d FROM DcFacility d WHERE d.concept = :concept")
    , @NamedQuery(name = "DcFacility.findByDescription", query = "SELECT d FROM DcFacility d WHERE d.description = :description")
    , @NamedQuery(name = "DcFacility.findByCreateTimestamp", query = "SELECT d FROM DcFacility d WHERE d.createTimestamp = :createTimestamp")
    , @NamedQuery(name = "DcFacility.findByUpdateTimestamp", query = "SELECT d FROM DcFacility d WHERE d.updateTimestamp = :updateTimestamp")
    , @NamedQuery(name = "DcFacility.findByDcRecordStatusId", query = "SELECT d FROM DcFacility d WHERE d.dcRecordStatusId = :dcRecordStatusId")})
public class DcFacility extends RecordStatusableEntry implements Serializable {

    @Id
    @SequenceGenerator(name = "seq", sequenceName = "dc_fac_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "seq", strategy = GenerationType.SEQUENCE) 
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    
    @NotNull
    @Column(name = "CONCEPT", length = 50)
    private String concept;
    
    @Column(name = "DESCRIPTION", length = 100)
    private String description;
    
    public DcFacility() {
    }

    public DcFacility(Long id) {
        this.id = id;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(object instanceof DcFacility)) {
            return false;
        }
        DcFacility other = (DcFacility) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.honda.hdm.datacollect.model.entity.DcFacility[ id=" + id + " ]";
    }
    
}
