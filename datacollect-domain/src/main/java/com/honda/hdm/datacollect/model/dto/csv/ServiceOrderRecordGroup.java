/*
 * Honda de Mexico 2018.
 * All rights reserved.
 */

package com.honda.hdm.datacollect.model.dto.csv;

import java.util.List;
import org.beanio.annotation.Group;
import org.beanio.annotation.Record;

/**
 * Service Order Group :  (0-N) <br/>
 *  C2 (1)      <br/>
 *  D3 (1)      <br/>
 *  E4 (0-N)    <br/>
 *  F5 (1-N)    <br/>
 *  G6 (1)      <br/>
 *  H7 (0-N)    <br/>
 * 
 * @author Cesar Martinez <cesar_x_martinez@hdm.honda.com>
 * @since  Aug 29, 2018
 * 
 */
@Group(name = "ServiceOrderRecordGroup", minOccurs = 1)
public class ServiceOrderRecordGroup {
    
    @Record(minOccurs = 1, maxOccurs = 1)
    private C2ClientInformationRecord c2ClientInformationRecord;
    
    @Record(minOccurs = 1, maxOccurs = 1)
    private D3VinInformationRecord d3VinInformationRecord;
    
    @Record(minOccurs = 0, collection = List.class)
    private List<E4LaborInformationRecord> e4LaborInfoList;
    
    @Record(minOccurs = 0, collection = List.class)
    private List<F5PartInformationRecord> f5PartInformationList;
    
    @Record(minOccurs = 0, maxOccurs = 1)
    private G6ServiceOrderTotalRecord g6ServiceOrderTotalRecord;       
    
    @Record(minOccurs = 0, collection = List.class)
    private List<H7InvoiceInformationRecord> h7InvoiceInfoList;
    
    private Boolean validadoOK = true;

    public Boolean getValidadoOK() {
        return validadoOK;
    }

    public void setValidadoOK(Boolean validadoOK) {
        this.validadoOK = validadoOK;
    }

    public C2ClientInformationRecord getC2ClientInformationRecord() {
        return c2ClientInformationRecord;
    }

    public void setC2ClientInformationRecord(C2ClientInformationRecord c2ClientInformationRecord) {
        this.c2ClientInformationRecord = c2ClientInformationRecord;
    }

    public D3VinInformationRecord getD3VinInformationRecord() {
        return d3VinInformationRecord;
    }

    public void setD3VinInformationRecord(D3VinInformationRecord d3VinInformationRecord) {
        this.d3VinInformationRecord = d3VinInformationRecord;
    }

    public List<E4LaborInformationRecord> getE4LaborInfoList() {
        return e4LaborInfoList;
    }

    public void setE4LaborInfoList(List<E4LaborInformationRecord> e4LaborInfoList) {
        this.e4LaborInfoList = e4LaborInfoList;
    }

    public List<F5PartInformationRecord> getF5PartInformationList() {
        return f5PartInformationList;
    }

    public void setF5PartInformationList(List<F5PartInformationRecord> f5PartInformationList) {
        this.f5PartInformationList = f5PartInformationList;
    }

    public G6ServiceOrderTotalRecord getG6ServiceOrderTotalRecord() {
        return g6ServiceOrderTotalRecord;
    }

    public void setG6ServiceOrderTotalRecord(G6ServiceOrderTotalRecord g6ServiceOrderTotalRecord) {
        this.g6ServiceOrderTotalRecord = g6ServiceOrderTotalRecord;
    }

    public List<H7InvoiceInformationRecord> getH7InvoiceInfoList() {
        return h7InvoiceInfoList;
    }

    public void setH7InvoiceInfoList(List<H7InvoiceInformationRecord> h7InvoiceInfoList) {
        this.h7InvoiceInfoList = h7InvoiceInfoList;
    }

    @Override
    public String toString() {
        return "ServiceOrderRecordGroup{" + "c2ClientInformationRecord=" + c2ClientInformationRecord + ", d3VinInformationRecord=" + d3VinInformationRecord + ", e4LaborInfoList=" + e4LaborInfoList + ", f5PartInformationList=" + f5PartInformationList + ", g6ServiceOrderTotalRecord=" + g6ServiceOrderTotalRecord + ", h7InvoiceInfoList=" + h7InvoiceInfoList + '}';
    }

}
