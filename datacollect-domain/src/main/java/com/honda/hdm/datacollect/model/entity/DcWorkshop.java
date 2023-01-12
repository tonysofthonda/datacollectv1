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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author VJC80377
 */
@Entity
@Table(name = "DC_WORKSHOP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DcWorkshop.findAll", query = "SELECT d FROM DcWorkshop d")
    , @NamedQuery(name = "DcWorkshop.findById", query = "SELECT d FROM DcWorkshop d WHERE d.id = :id")
    , @NamedQuery(name = "DcWorkshop.findByName", query = "SELECT d FROM DcWorkshop d WHERE d.name = :name")
    , @NamedQuery(name = "DcWorkshop.findByCreateTimestamp", query = "SELECT d FROM DcWorkshop d WHERE d.createTimestamp = :createTimestamp")
    , @NamedQuery(name = "DcWorkshop.findByUpdateTimestamp", query = "SELECT d FROM DcWorkshop d WHERE d.updateTimestamp = :updateTimestamp")})
public class DcWorkshop extends RecordStatusableEntry implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NAME")
    private String name;

    public DcWorkshop() {
    }

    public DcWorkshop(Long id) {
        this.id = id;
    }

    public DcWorkshop(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (!(object instanceof DcWorkshop)) {
            return false;
        }
        DcWorkshop other = (DcWorkshop) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.honda.hdm.datacollect.model.entity.DcWorkshop[ id=" + id + " ]";
    }
    
}
