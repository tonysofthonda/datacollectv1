/*
 * Honda de Mexico 2018.
 * All rights reserved.
 */

package com.honda.hdm.datacollect.model.dto.csv;

import java.math.BigDecimal;
import org.beanio.annotation.Field;
import org.beanio.annotation.Fields;
import org.beanio.annotation.Record;

/**
 *
 * @author VJC80413
 * @since  Sep 21, 2018
 *
 * Invoice Information Record.
 * Key: F5
 * Required: false
 * Occurrence: 1 to N
 * 
 */
@Record(name = "F5PartInformationRecord", minOccurs = 0, order = 6)
@Fields({
    @Field(literal = "F5", name = "recordType", at = 0, rid = true, required = true) // record ID
})
public class F5PartInformationRecord extends BaseBeanIoRecord {

    @Field(at = 1, required = false)
    private String partNumber;
    
    @Field(at = 2, required = false)
    private String description;
    
    @Field(at = 3, required = false)
    private BigDecimal quantity;

    @Field(at = 4, required = false)
    private BigDecimal listPrice;
    
    @Field(at = 5, required = false)
    private BigDecimal subtotal;
    
    @Field(at = 6, required = false)
    private BigDecimal discountPercent;
    
    @Field(at = 7, required = false)
    private BigDecimal discountAmount;
    
    @Field(at = 8, required = false)
    private BigDecimal netTotal;

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getListPrice() {
        return listPrice;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
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

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public BigDecimal getNetTotal() {
        return netTotal;
    }

    public void setNetTotal(BigDecimal netTotal) {
        this.netTotal = netTotal;
    }

    @Override
    public String toString() {
        return "F5PartInformationRecord{" + "partNumber=" + partNumber + ", description=" + description + ", quantity=" + quantity + ", listPrice=" + listPrice + ", subtotal=" + subtotal + ", discountPercent=" + discountPercent + ", discountAmount=" + discountAmount + ", netTotal=" + netTotal + '}';
    }            

    
}
