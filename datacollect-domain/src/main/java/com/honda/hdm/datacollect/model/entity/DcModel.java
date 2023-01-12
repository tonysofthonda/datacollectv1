/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.model.entity;

import com.honda.hdm.datacollect.model.entity.base.RecordStatusableEntry;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author VJC80439
 */
@Entity
@Table(name = "DC_MODEL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DcModel.findAll", query = "SELECT d FROM DcModel d"),
    @NamedQuery(name = "DcModel.findById", query = "SELECT d FROM DcModel d WHERE d.id = :id"),
    @NamedQuery(name = "DcModel.findByCode", query = "SELECT d FROM DcModel d WHERE d.code = :code"),
    @NamedQuery(name = "DcModel.findByBrand", query = "SELECT d FROM DcModel d WHERE d.brand = :brand"),
    @NamedQuery(name = "DcModel.findByName", query = "SELECT d FROM DcModel d WHERE d.name = :name"),
    @NamedQuery(name = "DcModel.findByYear", query = "SELECT d FROM DcModel d WHERE d.year = :year"),
    @NamedQuery(name = "DcModel.findByAssemblyLocation", query = "SELECT d FROM DcModel d WHERE d.assemblyLocation = :assemblyLocation"),
    @NamedQuery(name = "DcModel.findByExtColorCode", query = "SELECT d FROM DcModel d WHERE d.extColorCode = :extColorCode"),
    @NamedQuery(name = "DcModel.findByExtColorName", query = "SELECT d FROM DcModel d WHERE d.extColorName = :extColorName"),
    @NamedQuery(name = "DcModel.findByIntColorCode", query = "SELECT d FROM DcModel d WHERE d.intColorCode = :intColorCode"),
    @NamedQuery(name = "DcModel.findByDescription", query = "SELECT d FROM DcModel d WHERE d.description = :description"),
    @NamedQuery(name = "DcModel.findByCreateTimestamp", query = "SELECT d FROM DcModel d WHERE d.createTimestamp = :createTimestamp"),
    @NamedQuery(name = "DcModel.findByUpdateTimestamp", query = "SELECT d FROM DcModel d WHERE d.updateTimestamp = :updateTimestamp")})
public class DcModel extends RecordStatusableEntry implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "seq", sequenceName = "dcmodel_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "seq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, precision = 38, scale = 0)
    private Long id;
    @Column(name = "CODE", length = 25)
    private String code;
    @Column(name = "BRAND", length = 10)
    private String brand;
    @Column(name = "NAME", length = 20)
    private String name;
    @Column(name = "YEAR", length = 4)
    private String year;
    @Column(name = "ASSEMBLY_LOCATION", length = 50)
    private String assemblyLocation;
    @Column(name = "EXT_COLOR_CODE", length = 15)
    private String extColorCode;
    @Column(name = "EXT_COLOR_NAME", length = 60)
    private String extColorName;
    @Column(name = "INT_COLOR_CODE", length = 15)
    private String intColorCode;
    @Column(name = "DESCRIPTION", length = 50)
    private String description;
    //@Column(name = "ACCOUNT_NUMBER", length = 100)
    @Transient
    private transient String accountNumber;

    @JoinTable(name = "DC_SYSTEM_SERVICE_X_MODEL",
            joinColumns = {
                @JoinColumn(name = "DC_MODEL_ID", referencedColumnName = "ID", nullable = false)},
            inverseJoinColumns = {
                @JoinColumn(name = "DC_SYSTEM_SERVICE_ID", referencedColumnName = "ID", nullable = false)})
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<DcSystemService> dcSystemServices;

    public DcModel() {
    }

    public DcModel(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getAssemblyLocation() {
        return assemblyLocation;
    }

    public void setAssemblyLocation(String assemblyLocation) {
        this.assemblyLocation = assemblyLocation;
    }

    public String getExtColorCode() {
        return extColorCode;
    }

    public void setExtColorCode(String extColorCode) {
        this.extColorCode = extColorCode;
    }

    public String getExtColorName() {
        return extColorName;
    }

    public void setExtColorName(String extColorName) {
        this.extColorName = extColorName;
    }

    public String getIntColorCode() {
        return intColorCode;
    }

    public void setIntColorCode(String intColorCode) {
        this.intColorCode = intColorCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @XmlTransient
    public List<DcSystemService> getDcSystemServices() {
        return dcSystemServices;
    }

    public void setDcSystemServices(List<DcSystemService> dcSystemServices) {
        this.dcSystemServices = dcSystemServices;
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
        if (!(object instanceof DcModel)) {
            return false;
        }
        DcModel other = (DcModel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.honda.hdm.datacollect.model.entity.DcModel[ id=" + id + " ]";
    }

}
