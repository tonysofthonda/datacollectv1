/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.model.entity.dto;

import com.honda.hdm.datacollect.model.dto.dbconst.DcRecordStatusEnum;
import com.honda.hdm.datacollect.validation.Alphabetic;
import java.io.Serializable;
import java.math.BigDecimal;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author VJC80587
 */
public class DcDealerGroupDto implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private BigDecimal id;
    
    @Length(max = 50)
    @NotEmpty
    @Alphabetic
    private String name;
    
    private DcRecordStatusEnum status;
    
    public BigDecimal getId() {
        return id;
    }
    
    public void setId(BigDecimal id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public DcRecordStatusEnum getStatus() {
        return status;
    }
    
    public void setStatus(DcRecordStatusEnum status) {
        this.status = status;
    }
}
