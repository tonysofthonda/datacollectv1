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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author VJC80439
 */
@Entity
@Table(name = "DC_MONTH_FIN_INFO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DcMonthFinInfo.findAll", query = "SELECT d FROM DcMonthFinInfo d")
    , @NamedQuery(name = "DcMonthFinInfo.findById", query = "SELECT d FROM DcMonthFinInfo d WHERE d.id = :id")
    , @NamedQuery(name = "DcMonthFinInfo.findByMonthTotalExpenses", query = "SELECT d FROM DcMonthFinInfo d WHERE d.monthTotalExpenses = :monthTotalExpenses")
    , @NamedQuery(name = "DcMonthFinInfo.findByMonthSellExpenses", query = "SELECT d FROM DcMonthFinInfo d WHERE d.monthSellExpenses = :monthSellExpenses")
    , @NamedQuery(name = "DcMonthFinInfo.findByMonthProfit", query = "SELECT d FROM DcMonthFinInfo d WHERE d.monthProfit = :monthProfit")
    , @NamedQuery(name = "DcMonthFinInfo.findByMonthLaborSum", query = "SELECT d FROM DcMonthFinInfo d WHERE d.monthLaborSum = :monthLaborSum")
    , @NamedQuery(name = "DcMonthFinInfo.findByMonthNumber", query = "SELECT d FROM DcMonthFinInfo d WHERE d.monthNumber = :monthNumber")
    , @NamedQuery(name = "DcMonthFinInfo.findByYearNumber", query = "SELECT d FROM DcMonthFinInfo d WHERE d.yearNumber = :yearNumber")
    , @NamedQuery(name = "DcMonthFinInfo.findByCreateTimestamp", query = "SELECT d FROM DcMonthFinInfo d WHERE d.createTimestamp = :createTimestamp")
    , @NamedQuery(name = "DcMonthFinInfo.findByUpdateTimestamp", query = "SELECT d FROM DcMonthFinInfo d WHERE d.updateTimestamp = :updateTimestamp")})
public class DcMonthFinInfo extends RecordStatusableEntry implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @SequenceGenerator(name = "seq", sequenceName = "dmfi_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "seq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false, precision = 38, scale = 0)
    private BigDecimal id;
    @Column(name = "MONTH_TOTAL_EXPENSES", precision = 13, scale = 4)
    private Double monthTotalExpenses;
    @Column(name = "MONTH_SELL_EXPENSES", precision = 13, scale = 4)
    private Double monthSellExpenses;
    @Column(name = "MONTH_PROFIT", precision = 13, scale = 4)
    private Double monthProfit;
    @Column(name = "MONTH_LABOR_SUM", precision = 13, scale = 4)
    private Double monthLaborSum;
    @Column(name = "MONTH_NUMBER")
    private BigInteger monthNumber;
    @Column(name = "YEAR_NUMBER")
    private BigInteger yearNumber;
    @ManyToOne(optional = false, fetch=FetchType.LAZY)
    @JoinColumn(name = "DC_DEALER_ID", referencedColumnName = "ID", nullable = false)
    private DcDealer dcDealerId;

    public DcMonthFinInfo() {
    }

    public DcMonthFinInfo(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Double getMonthTotalExpenses() {
        return monthTotalExpenses;
    }

    public void setMonthTotalExpenses(Double monthTotalExpenses) {
        this.monthTotalExpenses = monthTotalExpenses;
    }

    public Double getMonthSellExpenses() {
        return monthSellExpenses;
    }

    public void setMonthSellExpenses(Double monthSellExpenses) {
        this.monthSellExpenses = monthSellExpenses;
    }

    public Double getMonthProfit() {
        return monthProfit;
    }

    public void setMonthProfit(Double monthProfit) {
        this.monthProfit = monthProfit;
    }

    public Double getMonthLaborSum() {
        return monthLaborSum;
    }

    public void setMonthLaborSum(Double monthLaborSum) {
        this.monthLaborSum = monthLaborSum;
    }

    public BigInteger getMonthNumber() {
        return monthNumber;
    }

    public void setMonthNumber(BigInteger monthNumber) {
        this.monthNumber = monthNumber;
    }

    public BigInteger getYearNumber() {
        return yearNumber;
    }

    public void setYearNumber(BigInteger yearNumber) {
        this.yearNumber = yearNumber;
    }

    public DcDealer getDcDealerId() {
        return dcDealerId;
    }

    public void setDcDealerId(DcDealer dcDealerId) {
        this.dcDealerId = dcDealerId;
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
        if (!(object instanceof DcMonthFinInfo)) {
            return false;
        }
        DcMonthFinInfo other = (DcMonthFinInfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.honda.hdm.datacollect.model.entity.DcMonthFinInfo[ id=" + id + " ]";
    }
    
}
