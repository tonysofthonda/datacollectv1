/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.model.entity.auth;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.honda.hdm.datacollect.model.entity.DcPosition;
import com.honda.hdm.datacollect.model.entity.DcViewAction;
import com.honda.hdm.datacollect.model.entity.base.AuditableEntry;

/**
 * @author VJC80439
 */
@Entity
@Table(name = "DC_ROLE", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"NAME"})})
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "DcRole.findAll", query = "SELECT d FROM DcRole d")
        , @NamedQuery(name = "DcRole.findById", query = "SELECT d FROM DcRole d WHERE d.id = :id")
        , @NamedQuery(name = "DcRole.findByName", query = "SELECT d FROM DcRole d WHERE d.name = :name")
        , @NamedQuery(name = "DcRole.findByDescription", query = "SELECT d FROM DcRole d WHERE d.description = :description")
        , @NamedQuery(name = "DcRole.findByCreateTimestamp", query = "SELECT d FROM DcRole d WHERE d.createTimestamp = :createTimestamp")
        , @NamedQuery(name = "DcRole.findByUpdateTimestamp", query = "SELECT d FROM DcRole d WHERE d.updateTimestamp = :updateTimestamp")})
public class DcRole extends AuditableEntry implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "seq", sequenceName = "dcrole_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "seq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, precision = 38, scale = 0)
    private Long id;

    @Basic(optional = false)
    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    @Column(name = "DESCRIPTION", length = 255)
    private String description;

    @JoinTable(name = "DC_POSITION_X_ROLE",
            joinColumns = {
                    @JoinColumn(name = "DC_ROLE_ID", referencedColumnName = "ID", nullable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "DC_POSITION_ID", referencedColumnName = "ID", nullable = false)})
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<DcPosition> positions;

    @JoinTable(name = "DC_LDAPG_X_ROLE", joinColumns = {
            @JoinColumn(name = "DC_ROLE_ID", referencedColumnName = "ID", nullable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "DC_LDAP_GROUP_ID", referencedColumnName = "ID", nullable = false)})
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<DcLdapGroup> dcLdapGroupList;

    @JoinTable(name = "DC_VACTION_X_ROLE", joinColumns = {
            @JoinColumn(name = "DC_ROLE_ID", referencedColumnName = "ID", nullable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "DC_VIEW_ACTION_ID", referencedColumnName = "ID", nullable = false)})
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<DcViewAction> permissionList;

    public DcRole() {
    }

    public DcRole(Long id) {
        this.id = id;
    }

    public DcRole(Long id, String name) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public List<DcPosition> getPositions() {
        return positions;
    }

    public void setPositions(List<DcPosition> positions) {
        this.positions = positions;
    }

    @XmlTransient
    public List<DcLdapGroup> getDcLdapGroupList() {
        return dcLdapGroupList;
    }

    public void setDcLdapGroupList(List<DcLdapGroup> dcLdapGroupList) {
        this.dcLdapGroupList = dcLdapGroupList;
    }

    @XmlTransient
    public List<DcViewAction> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<DcViewAction> permissionList) {
        this.permissionList = permissionList;
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
        if (!(object instanceof DcRole)) {
            return false;
        }
        DcRole other = (DcRole) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DcRole [id=" + id + ", name=" + name + ", description=" + description + ", dcLdapGroupList="
                + dcLdapGroupList + "]";
    }

}
