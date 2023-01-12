/*
 * Honda de Mexico 2018.
 * All rights reserved.
 */

package com.honda.hdm.datacollect.model.dto.csv;

import java.util.List;
import org.beanio.annotation.Group;
import org.beanio.annotation.Record;

/**
 *
 * Data Collect Services Input Interface :
 * 
 *  A0 (1)
 *  B1 (0-1)
 *      C2 (1)
 *      D3 (1)
 *      E4 (1-N)
 *      F5 (1-N)
 *      G6 (1)
 *      H7 (0-N)
 *  I8 (1)
 * 
 * @author Cesar Martinez <cesar_x_martinez@hdm.honda.com>
 * @since  Aug 29, 2018
 *
 */
@Group(minOccurs = 1, maxOccurs = 1)
public class DataCollectInterfaceWrapper {

    @Record(minOccurs = 1, maxOccurs = 1)
    private A0DealerInformationRecord a0;
    
    @Record(minOccurs = 0, maxOccurs = 1)
    private B1MonthlyFinancialInfoRecord b1;
    
    @Group(minOccurs = 1, collection = List.class)
    private List<ServiceOrderRecordGroup> serviceOrderRecordGroupList;
    
    @Record(minOccurs = 1, maxOccurs = 1)
    private I8EndOfFileRecord i8;

    public A0DealerInformationRecord getA0() {
        return a0;
    }

    public void setA0(A0DealerInformationRecord a0) {
        this.a0 = a0;
    }

    public B1MonthlyFinancialInfoRecord getB1() {
        return b1;
    }

    public void setB1(B1MonthlyFinancialInfoRecord b1) {
        this.b1 = b1;
    }

    public List<ServiceOrderRecordGroup> getServiceOrderRecordGroupList() {
        return serviceOrderRecordGroupList;
    }

    public void setServiceOrderRecordGroupList(List<ServiceOrderRecordGroup> serviceOrderRecordGroupList) {
        this.serviceOrderRecordGroupList = serviceOrderRecordGroupList;
    }

    public I8EndOfFileRecord getI8() {
        return i8;
    }

    public void setI8(I8EndOfFileRecord i8) {
        this.i8 = i8;
    }

    @Override
    public String toString() {
        return "DataCollectInterfaceWrapper{" + "a0=" + a0 + ", b1=" + b1 + ", serviceOrderRecordGroupList=" + serviceOrderRecordGroupList + ", i8=" + i8 + '}';
    }
    
}
