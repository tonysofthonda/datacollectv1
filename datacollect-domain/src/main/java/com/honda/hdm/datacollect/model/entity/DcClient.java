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
@Table(name = "DC_CLIENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DcClient.findAll", query = "SELECT d FROM DcClient d")
    , @NamedQuery(name = "DcClient.findById", query = "SELECT d FROM DcClient d WHERE d.id = :id")
    , @NamedQuery(name = "DcClient.findByRfc", query = "SELECT d FROM DcClient d WHERE d.rfc = :rfc")
    , @NamedQuery(name = "DcClient.findByFirstName", query = "SELECT d FROM DcClient d WHERE d.firstName = :firstName")
    , @NamedQuery(name = "DcClient.findByLastName", query = "SELECT d FROM DcClient d WHERE d.lastName = :lastName")
    , @NamedQuery(name = "DcClient.findByLastNameB", query = "SELECT d FROM DcClient d WHERE d.lastNameB = :lastNameB")
    , @NamedQuery(name = "DcClient.findByGender", query = "SELECT d FROM DcClient d WHERE d.gender = :gender")
    , @NamedQuery(name = "DcClient.findByPrivacy", query = "SELECT d FROM DcClient d WHERE d.privacy = :privacy")
    , @NamedQuery(name = "DcClient.findByEmail", query = "SELECT d FROM DcClient d WHERE d.email = :email")
    , @NamedQuery(name = "DcClient.findByPhone", query = "SELECT d FROM DcClient d WHERE d.phone = :phone")
    , @NamedQuery(name = "DcClient.findByAddrStreet", query = "SELECT d FROM DcClient d WHERE d.addrStreet = :addrStreet")
    , @NamedQuery(name = "DcClient.findByAddrExtNum", query = "SELECT d FROM DcClient d WHERE d.addrExtNum = :addrExtNum")
    , @NamedQuery(name = "DcClient.findByAddrIntNum", query = "SELECT d FROM DcClient d WHERE d.addrIntNum = :addrIntNum")
    , @NamedQuery(name = "DcClient.findByAddrNeighborhood", query = "SELECT d FROM DcClient d WHERE d.addrNeighborhood = :addrNeighborhood")
    , @NamedQuery(name = "DcClient.findByAddrPostcode", query = "SELECT d FROM DcClient d WHERE d.addrPostcode = :addrPostcode")
    , @NamedQuery(name = "DcClient.findByDcCityId", query = "SELECT d FROM DcClient d WHERE d.dcCityId = :dcCityId")
    , @NamedQuery(name = "DcClient.findByDcStateId", query = "SELECT d FROM DcClient d WHERE d.dcStateId = :dcStateId")
    , @NamedQuery(name = "DcClient.findByCreateTimestamp", query = "SELECT d FROM DcClient d WHERE d.createTimestamp = :createTimestamp")
    , @NamedQuery(name = "DcClient.findByUpdateTimestamp", query = "SELECT d FROM DcClient d WHERE d.updateTimestamp = :updateTimestamp")})
public class DcClient extends RecordStatusableEntry implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "seq", sequenceName = "dclien_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "seq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, precision = 38, scale = 0)
    private BigDecimal id;
    @Basic(optional = false)
    @Column(name = "RFC", nullable = false, length = 15)
    private String rfc;
    @Basic(optional = false)
    @Column(name = "FIRST_NAME", nullable = false, length = 100)
    private String firstName;
    @Column(name = "LAST_NAME", length = 40)
    private String lastName;
    @Column(name = "LAST_NAME_B", length = 40)
    private String lastNameB;
    @Column(name = "GENDER", length = 1)
    private String gender;
    @Column(name = "PRIVACY", length = 1)
    private String privacy;
    @Column(name = "EMAIL", length = 120)
    private String email;
    @Column(name = "PHONE", length = 100)
    private String phone;
    @Column(name = "ADDR_STREET", length = 80)
    private String addrStreet;
    @Column(name = "ADDR_EXT_NUM", length = 10)
    private String addrExtNum;
    @Column(name = "ADDR_INT_NUM", length = 10)
    private String addrIntNum;
    @Column(name = "ADDR_NEIGHBORHOOD", length = 80)
    private String addrNeighborhood;
    @Column(name = "ADDR_POSTCODE", length = 6)
    private String addrPostcode;
    @Column(name = "DC_CITY_ID")
    private BigInteger dcCityId;
    @Column(name = "DC_STATE_ID")
    private BigInteger dcStateId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dcClientId")
    private List<DcSvcOrderVin> dcSvcOrderVinList;

    public DcClient() {
    }

    public DcClient(BigDecimal id) {
        this.id = id;
    }

    public DcClient(BigDecimal id, String rfc, String firstName) {
        this.id = id;
        this.rfc = rfc;
        this.firstName = firstName;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
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

    public String getLastNameB() {
        return lastNameB;
    }

    public void setLastNameB(String lastNameB) {
        this.lastNameB = lastNameB;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddrStreet() {
        return addrStreet;
    }

    public void setAddrStreet(String addrStreet) {
        this.addrStreet = addrStreet;
    }

    public String getAddrExtNum() {
        return addrExtNum;
    }

    public void setAddrExtNum(String addrExtNum) {
        this.addrExtNum = addrExtNum;
    }

    public String getAddrIntNum() {
        return addrIntNum;
    }

    public void setAddrIntNum(String addrIntNum) {
        this.addrIntNum = addrIntNum;
    }

    public String getAddrNeighborhood() {
        return addrNeighborhood;
    }

    public void setAddrNeighborhood(String addrNeighborhood) {
        this.addrNeighborhood = addrNeighborhood;
    }

    public String getAddrPostcode() {
        return addrPostcode;
    }

    public void setAddrPostcode(String addrPostcode) {
        this.addrPostcode = addrPostcode;
    }

    public BigInteger getDcCityId() {
        return dcCityId;
    }

    public void setDcCityId(BigInteger dcCityId) {
        this.dcCityId = dcCityId;
    }

    public BigInteger getDcStateId() {
        return dcStateId;
    }

    public void setDcStateId(BigInteger dcStateId) {
        this.dcStateId = dcStateId;
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
        if (!(object instanceof DcClient)) {
            return false;
        }
        DcClient other = (DcClient) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.honda.hdm.datacollect.model.entity.DcClient[ id=" + id + " ]";
    }
    
}
