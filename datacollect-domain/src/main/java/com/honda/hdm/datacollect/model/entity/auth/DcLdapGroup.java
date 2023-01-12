/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.model.entity.auth;

import com.honda.hdm.datacollect.model.entity.base.AuditableEntry;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "DC_LDAP_GROUP", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"NAME"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DcLdapGroup.findAll", query = "SELECT d FROM DcLdapGroup d")
    , @NamedQuery(name = "DcLdapGroup.findById", query = "SELECT d FROM DcLdapGroup d WHERE d.id = :id")
    , @NamedQuery(name = "DcLdapGroup.findByName", query = "SELECT d FROM DcLdapGroup d WHERE d.name = :name")
    , @NamedQuery(name = "DcLdapGroup.findByLdapId", query = "SELECT d FROM DcLdapGroup d WHERE d.ldapId = :ldapId")
    , @NamedQuery(name = "DcLdapGroup.findByCreateTimestamp", query = "SELECT d FROM DcLdapGroup d WHERE d.createTimestamp = :createTimestamp")
    , @NamedQuery(name = "DcLdapGroup.findByUpdateTimestamp", query = "SELECT d FROM DcLdapGroup d WHERE d.updateTimestamp = :updateTimestamp")})
public class DcLdapGroup extends AuditableEntry implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "seq", sequenceName = "ldgrp_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "seq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, precision = 38, scale = 0)
    private Long id;
    @Column(name = "NAME", length = 50)
    private String name;
    @Column(name = "LDAP_ID", length = 255)
    private String ldapId;
    @ManyToMany(mappedBy = "dcLdapGroupList")
    private List<DcRole> dcRoleList;

    public DcLdapGroup() {
    }

    public DcLdapGroup(Long id) {
        this.id = id;
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

    public String getLdapId() {
        return ldapId;
    }

    public void setLdapId(String ldapId) {
        this.ldapId = ldapId;
    }

    @XmlTransient
    public List<DcRole> getDcRoleList() {
        return dcRoleList;
    }

    public void setDcRoleList(List<DcRole> dcRoleList) {
        this.dcRoleList = dcRoleList;
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
        if (!(object instanceof DcLdapGroup)) {
            return false;
        }
        DcLdapGroup other = (DcLdapGroup) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.honda.hdm.datacollect.model.entity.DcLdapGroup[ id=" + id + " ]";
    }
    
}
