/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.model.entity;

import com.honda.hdm.datacollect.model.entity.base.AuditableEntry;
import com.honda.hdm.datacollect.model.entity.base.RecordStatusableEntry;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author VJC80439
 */
@Entity
@Table(name = "DC_VIN_MASTER")
@XmlRootElement
public class DcVinMaster extends AuditableEntry implements Serializable {

    @Id
    @SequenceGenerator(name = "seq", sequenceName = "dc_visvin_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "seq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "VIN", nullable = false)
    private String vin;
    @Column(name = "MODEL_ID")
    private String modelId;
    @Column(name = "JAPAN_COLOR_CODE")
    private String japanColorCode;
    @Column(name = "DEALER_NUMBER")
    private String dealerNumber;
    @Column(name = "VIN_STATUS_CODE")
    private String vinStatusCode;
    @Column(name = "MODEL_YEAR")
    private String modelYear;
    @Column(name = "WARRANTY_DATE")
    private Long warrantyDate;
    @Column(name = "CUSTOMER_SALE_DATE")
    private Long customerSaleDate;
    @Column(name = "PDI_PROVIDER_DATE")
    private Long pdiProviderDate;

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getJapanColorCode() {
        return japanColorCode;
    }

    public void setJapanColorCode(String japanColorCode) {
        this.japanColorCode = japanColorCode;
    }

    public String getDealerNumber() {
        return dealerNumber;
    }

    public void setDealerNumber(String dealerNumber) {
        this.dealerNumber = dealerNumber;
    }

    public String getVinStatusCode() {
        return vinStatusCode;
    }

    public void setVinStatusCode(String vinStatusCode) {
        this.vinStatusCode = vinStatusCode;
    }

    public String getModelYear() {
        return modelYear;
    }

    public void setModelYear(String modelYear) {
        this.modelYear = modelYear;
    }

    public Long getWarrantyDate() {
        return warrantyDate;
    }

    public void setWarrantyDate(Long warrantyDate) {
        this.warrantyDate = warrantyDate;
    }

    public Long getCustomerSaleDate() {
        return customerSaleDate;
    }

    public void setCustomerSaleDate(Long customerSaleDate) {
        this.customerSaleDate = customerSaleDate;
    }

    public Long getPdiProviderDate() {
        return pdiProviderDate;
    }

    public void setPdiProviderDate(Long pdiProviderDate) {
        this.pdiProviderDate = pdiProviderDate;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.vin);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DcVinMaster other = (DcVinMaster) obj;
        if (!Objects.equals(this.vin, other.vin)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DcVinMaster{" + "vin=" + vin + ", modelId=" + modelId + ", japanColorCode=" + japanColorCode + ", dealerNumber=" + dealerNumber + ", vinStatusCode=" + vinStatusCode + ", modelYear=" + modelYear + ", warrantyDate=" + warrantyDate + ", customerSaleDate=" + customerSaleDate + ", pdiProviderDate=" + pdiProviderDate + '}';
    }

    
}
