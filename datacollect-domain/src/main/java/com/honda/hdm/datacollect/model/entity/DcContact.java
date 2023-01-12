/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.honda.hdm.datacollect.model.entity.DcDealer;
import com.honda.hdm.datacollect.model.entity.DcTerchief;
import com.honda.hdm.datacollect.model.entity.base.RecordStatusableEntry;
import java.io.Serializable;
import java.util.List;
import java.util.function.Predicate;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author VJC80439
 */
@Entity
@Table(name = "DC_CONTACT", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"EMAIL"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DcContact.findAll", query = "SELECT d FROM DcContact d"),
    @NamedQuery(name = "DcContact.findById", query = "SELECT d FROM DcContact d WHERE d.id = :id"),
    @NamedQuery(name = "DcContact.findByPhoneNumber", query = "SELECT d FROM DcContact d WHERE d.phoneNumber = :phoneNumber"),
    @NamedQuery(name = "DcContact.findByFirstName", query = "SELECT d FROM DcContact d WHERE d.firstName = :firstName"),
    @NamedQuery(name = "DcContact.findByLastName", query = "SELECT d FROM DcContact d WHERE d.lastName = :lastName"),
    @NamedQuery(name = "DcContact.findByEmail", query = "SELECT d FROM DcContact d WHERE d.email = :email"),
    @NamedQuery(name = "DcContact.findByNotes", query = "SELECT d FROM DcContact d WHERE d.notes = :notes"),
    @NamedQuery(name = "DcContact.findByCreateTimestamp", query = "SELECT d FROM DcContact d WHERE d.createTimestamp = :createTimestamp"),
    @NamedQuery(name = "DcContact.findByUpdateTimestamp", query = "SELECT d FROM DcContact d WHERE d.updateTimestamp = :updateTimestamp"),
    @NamedQuery(name = "DcContact.findByRecordStatus", query = "SELECT d FROM DcContact d WHERE d.dcRecordStatusId = 1")
})
public class DcContact extends RecordStatusableEntry implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "seq", sequenceName = "dcctc_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "seq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, precision = 38, scale = 0)
    private Long id;

    @Column(name = "PHONE_NUMBER", length = 20)
    private String phoneNumber;

    @NotNull
    @Column(name = "FIRST_NAME", nullable = false, length = 100)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false, length = 100)
    private String lastName;

    @Column(name = "MOTHER_LAST_NAME", nullable = false, length = 50)
    private String motherLastName;

    @Column(name = "EMAIL", nullable = false, length = 50)
    private String email;

    @Column(name = "NOTES", length = 50)
    private String notes;

    @ManyToMany(mappedBy = "dcContactList")
    @JsonIgnore
    private List<DcDealer> dcDealerList;

    @ManyToMany(mappedBy = "dcContactList")
    @JsonIgnore
    private List<DcTerchief> dcTerchiefList;

    public DcContact() {
    }

    public DcContact(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getEmail() {
        return email;
    }

    public String getMotherLastName() {
        return motherLastName;
    }

    public void setMotherLastName(String motherLastName) {
        this.motherLastName = motherLastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @XmlTransient
    @JsonIgnore
    public List<DcDealer> getDcDealerList() {
        return dcDealerList;
    }

    public void setDcDealerList(List<DcDealer> dcDealerList) {
        this.dcDealerList = dcDealerList;
    }

    @XmlTransient
    @JsonIgnore
    public List<DcTerchief> getDcTerchiefList() {
        return dcTerchiefList;
    }

    public void setDcTerchiefList(List<DcTerchief> dcTerchiefList) {
        this.dcTerchiefList = dcTerchiefList;
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
        if (!(object instanceof DcContact)) {
            return false;
        }
        DcContact other = (DcContact) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.honda.hdm.datacollect.model.entity.DcContact[ id=" + id + " ]";
    }

    public static Predicate<DcContact> filterFileds(String term) {
        return (contact) -> {
            return contact.getFirstName().contains(term)
                    || (contact.getLastName() != null ? contact.getLastName().contains(term) : false)
                    || (contact.getMotherLastName() != null ? contact.getMotherLastName().contains(term) : false)
                    || (contact.getEmail() != null ? contact.getEmail().contains(term) : false);
        };
    }

}
