/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.honda.hdm.datacollect.model.entity.base.RecordStatusableEntry;
//import com.honda.hdm.datacollect.model.entity.comm.DcContactGroup;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author VJC80439
 */
@Entity
@Table(name = "DC_TERCHIEF")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DcTerchief.findAll", query = "SELECT d FROM DcTerchief d"),
    @NamedQuery(name = "DcTerchief.findById", query = "SELECT d FROM DcTerchief d WHERE d.id = :id"),
    @NamedQuery(name = "DcTerchief.findByFirstName", query = "SELECT d FROM DcTerchief d WHERE d.firstName = :firstName"),
    @NamedQuery(name = "DcTerchief.findByLastName", query = "SELECT d FROM DcTerchief d WHERE d.lastName = :lastName"),
    @NamedQuery(name = "DcTerchief.findByNotes", query = "SELECT d FROM DcTerchief d WHERE d.notes = :notes"),
    @NamedQuery(name = "DcTerchief.findByDmsName", query = "SELECT d FROM DcTerchief d WHERE d.dmsName = :dmsName"),
    @NamedQuery(name = "DcTerchief.findByCreateTimestamp", query = "SELECT d FROM DcTerchief d WHERE d.createTimestamp = :createTimestamp"),
    @NamedQuery(name = "DcTerchief.findByUpdateTimestamp", query = "SELECT d FROM DcTerchief d WHERE d.updateTimestamp = :updateTimestamp")})
public class DcTerchief extends RecordStatusableEntry implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "seq", sequenceName = "dcterc_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "seq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, precision = 38, scale = 0)
    private Long id;

    @Basic(optional = false)
    @Column(name = "FIRST_NAME", nullable = false, length = 50)
    @NotEmpty
    private String firstName;

    @Column(name = "LAST_NAME", length = 50)
    private String lastName;

    @Column(name = "MOTHER_LAST_NAME", length = 50)
    private String motherLast;

    @Column(name = "NOTES", length = 50)
    private String notes;

    @Column(name = "DMS_NAME", length = 25)
    private String dmsName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dcTerchief")
    private List<DcDealer> dcDealerList;

    @JoinTable(name = "DC_CONTACT_X_TERCHIEF",
            joinColumns = {
                @JoinColumn(name = "DC_TERCHIEF_ID", referencedColumnName = "ID", nullable = false)},
            inverseJoinColumns = {
                @JoinColumn(name = "DC_CONTACT_ID", referencedColumnName = "ID", nullable = false)})
    @ManyToMany(fetch = FetchType.EAGER)
    private List<DcContact> dcContactList;

    public DcTerchief() {
    }

    public DcTerchief(Long id) {
        this.id = id;
    }

    public DcTerchief(Long id, String firstName) {
        this.id = id;
        this.firstName = firstName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDmsName() {
        return dmsName;
    }

    public void setDmsName(String dmsName) {
        this.dmsName = dmsName;
    }

    @XmlTransient
    @JsonIgnore
    public List<DcDealer> getDcDealerList() {
        return dcDealerList;
    }

    public void setDcDealerList1(List<DcDealer> dcDealerList) {
        this.dcDealerList = dcDealerList;
    }

    @XmlTransient
    @JsonIgnore
    public List<DcContact> getDcContactList() {
        return dcContactList;
    }

    public void setDcContactList(List<DcContact> dcContactList) {
        this.dcContactList = dcContactList;
    }

    public String getMotherLast() {
        return motherLast;
    }

    public void setMotherLast(String motherLast) {
        this.motherLast = motherLast;
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
        if (!(object instanceof DcTerchief)) {
            return false;
        }
        DcTerchief other = (DcTerchief) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.honda.hdm.datacollect.model.entity.DcTerchief[ id=" + id + " ]";
    }

}
