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
 * @author Cesar Martinez <cesar_x_martinez@hdm.honda.com>
 * @since  Aug 29, 2018
 *
 * Invoice Information Record.
 * Key: H7
 * Required: false
 * Occurrence: 1 to N
 * 
 */
@Record(name = "H7InvoiceInformationRecord", minOccurs = 0, order = 6)
@Fields({
    @Field(literal = "H7", name = "recordType", at = 0, rid = true, required = true) // record ID
})
public class H7InvoiceInformationRecord extends BaseBeanIoRecord {

    @Field(at = 1, required = false)
    private String folio;
    
    @Field(at = 2, required = false)
    private String serviceOrder;
    
    @Field(at = 3, required = false)
    private String date;

    @Field(at = 4,  required = false)
    private String customerName;
    
    @Field(at = 5,  required = false)
    private String addrPostCode;
    
    @Field(at = 6, required = false)
    private BigDecimal total;
    
    @Field(at = 7,  required = false)
    private String status;
    
    @Field(at = 8,  required = false)
    private String customerRFC;
       
    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getServiceOrder() {
        return serviceOrder;
    }

    public void setServiceOrder(String serviceOrder) {
        this.serviceOrder = serviceOrder;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddrPostCode() {
        return addrPostCode;
    }

    public void setAddrPostCode(String addrPostCode) {
        this.addrPostCode = addrPostCode;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getCustomerRFC() {
        return customerRFC;
    }

    public void setCustomerRFC(String customerRFC) {
        this.customerRFC = customerRFC;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return "H7InvoiceInformationRecord{" + "folio=" + folio + ", serviceOrder=" + serviceOrder + ", date=" + date + ", customerName=" + customerName + ", addrPostCode=" + addrPostCode + ", total=" + total + ", customerRFC=" + customerRFC + ", status=" + status + " }";
    }

    
}
