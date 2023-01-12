/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.model.dto.csv;

import java.math.BigInteger;

import org.beanio.annotation.Field;
import org.beanio.annotation.Fields;
import org.beanio.annotation.Record;

/**
 *
 * @author Fabian Fonseca <fabian_fonseca@hdm.honda.com>
 * @since Sep 07, 2018
 * 
 * VIN Information Record
 * Key: D3
 * Required: true
 * Occurrence: 1
 * 
 */
@Record(name = "D3VinInformationRecord", minOccurs = 1, maxOccurs = 1, order = 2)
@Fields({
    @Field(literal = "D3", name = "recordType", at = 0, rid = true, required = true) // record ID
})
public class D3VinInformationRecord extends BaseBeanIoRecord {
	
    @Field(at = 1, required = false, trim = true)
    private String serviceOrder;
    
    @Field(at = 2, required = false, trim = true)
    private BigInteger mileage;
    
    @Field(at = 3, required = false, trim = true)
    private String vin;
    
    @Field(at = 4, required = false, trim = true)
    private String carPlate;
    
    @Field(at = 5, required = false, trim = true)
    private String model;
    
    @Field(at = 6, required = false, trim = true)
    private String year;
    
    @Field(at = 7, required = false, trim = true)
    private String engine;
    
    @Field(at = 8, required = false, trim = true)
    private String orderType;
    
    @Field(at = 9, required = false, trim = true)
    private byte orderStatus;
    
    @Field(at = 10, required = false, trim = true)
    private String dptsAdvisor;
    
    @Field(at = 11, required = false, trim = true)
    private String openingDate;
    
    @Field(at = 12, required = false, trim = true)
    private String closedDate;
    
    public String getServiceOrder() {
        return serviceOrder;
    }

    public void setServiceOrder(String serviceOrder) {
        this.serviceOrder = serviceOrder;
    }

    public BigInteger getMileage() {
        return mileage;
    }

    public void setMileage(BigInteger mileage) {
        this.mileage = mileage;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public byte getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(byte orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getDptsAdvisor() {
        return dptsAdvisor;
    }

    public void setDptsAdvisor(String dptsAdvisor) {
        this.dptsAdvisor = dptsAdvisor;
    }

    public String getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(String openingDate) {
        this.openingDate = openingDate;
    }

    public String getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(String closedDate) {
        this.closedDate = closedDate;
    }

    @Override
    public String toString() {
        return "D3VinInformationRecord{" + "serviceOrder=" + serviceOrder + ",mileage=" + mileage + ",vin=" + vin + ",carPlate=" + carPlate + ",model=" + model + ",year=" + year + ",engine=" + engine + ",orderType=" + orderType + ",orderStatus=" + orderStatus + ",dptsAdvisor=" + dptsAdvisor + ",openingDate=" + openingDate + ",closedDate=" + closedDate + "}";
    }
}
