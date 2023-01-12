/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.model.entity;

import com.honda.hdm.datacollect.model.entity.base.RecordStatusableEntry;
import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "DC_VIN_MODEL")
@XmlRootElement
public class DcVinModel extends RecordStatusableEntry implements Serializable {

    @Id
    @SequenceGenerator(name = "seq", sequenceName = "dc_vinmdl_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "seq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private BigDecimal vin;
    @Column(name = "MODEL_YEAR")
    private String modelYear;
    @Column(name = "MODEL_ID")
    private String modelId;
    @Column(name = "BODY_TYPE_CODE")
    private String bodyTypeCode;
    @Column(name = "MODEL_DESCRIPTION")
    private String modelDescription;
    @Column(name = "MODEL_RELEASE_DATE")
    private String modelReleaseDate;
    @Column(name = "DOOR_QUANTITY")
    private long doorQuantity;
    @Column(name = "ADJUST_BODY_TYPE_ID")
    private String adjustBodyTypeId;
    @Column(name = "ABR_TRANSMISION_TYPE")
    private String abrTransmisionType;
    @Column(name = "YEARS_OF_WARRANTY")
    private long yearsOfWarranty;
    @Column(name = "KM_OF_WARRANTY")
    private long kmOfWarranty;
    @Column(name = "PREFIX_ENGINE_GROUP")
    private String prefixEngineGroup;
    @Column(name = "VIN_POSITION_1")
    private String vinPosition1;
    @Column(name = "INITIALS_DISTRIBUTOR")
    private String initialsDistributor;
    @Column(name = "DISTRIBUTOR_NUMBER")
    private long distributorNumber;
    @Column(name = "VIN_POSITION_10")
    private String vinPosition10;
    @Column(name = "VIN_POSITION_10_DESC")
    private String vinPosition10Desc;
    @Column(name = "VIN_POSITION_3_MODEL_ID")
    private String vinPosition3ModelId;
    @Column(name = "COST_CENTER")
    private String costCenter;
    @Column(name = "ACCOUNTING_ACCOUNT")
    private String accountingAccount;
    @Column(name = "PRODUCT")
    private String product;

    public BigDecimal getVin() {
        return vin;
    }

    public void setVin(BigDecimal vin) {
        this.vin = vin;
    }

    public String getModelYear() {
        return modelYear;
    }

    public void setModelYear(String modelYear) {
        this.modelYear = modelYear;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getBodyTypeCode() {
        return bodyTypeCode;
    }

    public void setBodyTypeCode(String bodyTypeCode) {
        this.bodyTypeCode = bodyTypeCode;
    }

    public String getModelDescription() {
        return modelDescription;
    }

    public void setModelDescription(String modelDescription) {
        this.modelDescription = modelDescription;
    }

    public String getModelReleaseDate() {
        return modelReleaseDate;
    }

    public void setModelReleaseDate(String modelReleaseDate) {
        this.modelReleaseDate = modelReleaseDate;
    }

    public long getDoorQuantity() {
        return doorQuantity;
    }

    public void setDoorQuantity(long doorQuantity) {
        this.doorQuantity = doorQuantity;
    }

    public String getAdjustBodyTypeId() {
        return adjustBodyTypeId;
    }

    public void setAdjustBodyTypeId(String adjustBodyTypeId) {
        this.adjustBodyTypeId = adjustBodyTypeId;
    }

    public String getAbrTransmisionType() {
        return abrTransmisionType;
    }

    public void setAbrTransmisionType(String abrTransmisionType) {
        this.abrTransmisionType = abrTransmisionType;
    }

    public long getYearsOfWarranty() {
        return yearsOfWarranty;
    }

    public void setYearsOfWarranty(long yearsOfWarranty) {
        this.yearsOfWarranty = yearsOfWarranty;
    }

    public long getKmOfWarranty() {
        return kmOfWarranty;
    }

    public void setKmOfWarranty(long kmOfWarranty) {
        this.kmOfWarranty = kmOfWarranty;
    }

    public String getPrefixEngineGroup() {
        return prefixEngineGroup;
    }

    public void setPrefixEngineGroup(String prefixEngineGroup) {
        this.prefixEngineGroup = prefixEngineGroup;
    }

    public String getVinPosition1() {
        return vinPosition1;
    }

    public void setVinPosition1(String vinPosition1) {
        this.vinPosition1 = vinPosition1;
    }

    public String getInitialsDistributor() {
        return initialsDistributor;
    }

    public void setInitialsDistributor(String initialsDistributor) {
        this.initialsDistributor = initialsDistributor;
    }

    public long getDistributorNumber() {
        return distributorNumber;
    }

    public void setDistributorNumber(long distributorNumber) {
        this.distributorNumber = distributorNumber;
    }

    public String getVinPosition10() {
        return vinPosition10;
    }

    public void setVinPosition10(String vinPosition10) {
        this.vinPosition10 = vinPosition10;
    }

    public String getVinPosition10Desc() {
        return vinPosition10Desc;
    }

    public void setVinPosition10Desc(String vinPosition10Desc) {
        this.vinPosition10Desc = vinPosition10Desc;
    }

    public String getVinPosition3ModelId() {
        return vinPosition3ModelId;
    }

    public void setVinPosition3ModelId(String vinPosition3ModelId) {
        this.vinPosition3ModelId = vinPosition3ModelId;
    }

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    public String getAccountingAccount() {
        return accountingAccount;
    }

    public void setAccountingAccount(String accountingAccount) {
        this.accountingAccount = accountingAccount;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "DcVinModel{" + "vin=" + vin + ", modelYear=" + modelYear + ", modelId=" + modelId + ", bodyTypeCode=" + bodyTypeCode + ", modelDescription=" + modelDescription + ", modelReleaseDate=" + modelReleaseDate + ", doorQuantity=" + doorQuantity + ", adjustBodyTypeId=" + adjustBodyTypeId + ", abrTransmisionType=" + abrTransmisionType + ", yearsOfWarranty=" + yearsOfWarranty + ", kmOfWarranty=" + kmOfWarranty + ", prefixEngineGroup=" + prefixEngineGroup + ", vinPosition1=" + vinPosition1 + ", initialsDistributor=" + initialsDistributor + ", distributorNumber=" + distributorNumber + ", vinPosition10=" + vinPosition10 + ", vinPosition10Desc=" + vinPosition10Desc + ", vinPosition3ModelId=" + vinPosition3ModelId + ", costCenter=" + costCenter + ", accountingAccount=" + accountingAccount + ", product=" + product + '}';
    }
 
    
   
}
