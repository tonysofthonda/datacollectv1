/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.model.dto.csv;

import java.math.BigDecimal;
import org.beanio.annotation.Field;
import org.beanio.annotation.Fields;
import org.beanio.annotation.Record;

/** 
 * @author VJC80413
 * 
 * Service Order Total Record.
 * Key: G6
 * Required: true
 * Occurrence: 1
 * 
 */
@Record(name = "G6ServiceOrderTotalRecord", minOccurs = 1, maxOccurs = 1)
@Fields({
    @Field(literal = "G6", name = "recordType", at = 0, rid = true, required = true) // record ID
})

public class G6ServiceOrderTotalRecord extends BaseBeanIoRecord {

    @Field(at=1, required = false)
    private BigDecimal laborTotal;
    
    @Field(at=2, required = false)
    private BigDecimal partTotal;
    
    @Field(at=3, required = false)
    private BigDecimal discountTotal;
    
    @Field(at=4, required = false)
    private BigDecimal subtotal;
    
    @Field(at=5, required = false)
    private BigDecimal taxTotal;

    public BigDecimal getLaborTotal() {
        return laborTotal;
    }

    public void setLaborTotal(BigDecimal laborTotal) {
        this.laborTotal = laborTotal;
    }

    public BigDecimal getPartTotal() {
        return partTotal;
    }

    public void setPartTotal(BigDecimal partTotal) {
        this.partTotal = partTotal;
    }

    public BigDecimal getDiscountTotal() {
        return discountTotal;
    }

    public void setDiscountTotal(BigDecimal discountTotal) {
        this.discountTotal = discountTotal;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTaxTotal() {
        return taxTotal;
    }

    public void setTaxTotal(BigDecimal taxTotal) {
        this.taxTotal = taxTotal;
    }

    @Override
    public String toString() {
        return "G6ServiceOrderTotalRecord{" + "laborTotal=" + laborTotal + ", partTotal=" + partTotal + ", discountTotal=" + discountTotal + ", subtotal=" + subtotal + ", taxTotal=" + taxTotal + '}';
    }

    
}
