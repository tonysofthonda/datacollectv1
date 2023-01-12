package com.honda.hdm.datacollect.model.dto.csv;

import java.math.BigDecimal;
import org.beanio.annotation.Field;
import org.beanio.annotation.Fields;
import org.beanio.annotation.Record;

/**
 *
 * @author Cesar Martinez <cesar_x_martinez@hdm.honda.com>
 * @since  Sep 20, 2018
 *
 * Service Labor Information Record.
 * Key: E4
 * Required: false
 * Occurrence: 1 to N
 * 
 */
@Record(name = "E4LaborInformationRecord", minOccurs = 1)
@Fields({
    @Field(literal = "E4", name = "recordType", at = 0, rid = true, required = true) // record ID
})
public class E4LaborInformationRecord extends BaseBeanIoRecord {

    @Field(at=1, required = false)
    private String operationCode;
    
    @Field(at=2, required = false)
    private String description;
    
    @Field(at=3, required = false)
    private double hours;
    
    @Field(at=4, required = false)
    private BigDecimal unitPrice;
    
    @Field(at=5, required = false)
    private BigDecimal subtotal;

    @Field(at=6, required = false)
    private BigDecimal discountPercent;
    
    //@Field(at=7, required = true, regex = RecordFormatUtil.REGEX_DECIMAL_OPTIONAL_9INT_4DEC)
    //private BigDecimal discountAmmount;
    
    @Field(at=7, required = false)
    private BigDecimal netTotal;
    
    @Field(at=8, required = false)
    private BigDecimal laborCost;
    
    @Field(at = 9, required = false)
    private String dptsTechnician;

    public String getOperationCode() {
        return operationCode;
    }

    public void setOperationCode(String operationCode) {
        this.operationCode = operationCode;
        }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        description.replaceAll("\"",  "");
        this.description = description;
    }

    public Double getHours() {
        return hours;
    }

    public void setHours(double hours) {
        this.hours = hours;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(BigDecimal discountPercent) {
        this.discountPercent = discountPercent;
    }

    public BigDecimal getNetTotal() {
        return netTotal;
    }

    public void setNetTotal(BigDecimal netTotal) {
        this.netTotal = netTotal;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

    public String getDptsTechnician() {
        return dptsTechnician;
    }

    public void setDptsTechnician(String dptsTechnician) {
        this.dptsTechnician = dptsTechnician;
    }

    @Override
    public String toString() {
        return "E4LaborInformationRecord{" + "operationCode=" + operationCode + ", description=" + description + ", hours=" + hours + ", unitPrice=" + unitPrice + ", subtotal=" + subtotal + ", discountPercent=" + discountPercent + ", netTotal=" + netTotal + ", laborCost=" + laborCost + ", dptsTechnician=" + dptsTechnician + '}';
    }
    
}
