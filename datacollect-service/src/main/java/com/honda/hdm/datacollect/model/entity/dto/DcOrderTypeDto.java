/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.model.entity.dto;

import com.honda.hdm.datacollect.validation.Alphanumeric;
import com.honda.hdm.datacollect.validation.OrderTypeValid;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author VJC80587
 */
@OrderTypeValid
public class DcOrderTypeDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private BigDecimal id;
    @Alphanumeric
    @Length(max = 5)
    @NotEmpty
    private String code;
    @Length(max = 50)
    private String description;
    @Length(max = 50)
    private String serviceType;
    public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	@Size(min = 1)
    private List<DcServiceTypeDto> servicesTypes;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<DcServiceTypeDto> getServicesTypes() {
        return servicesTypes;
    }

    public void setServicesTypes(List<DcServiceTypeDto> servicesTypes) {
        this.servicesTypes = servicesTypes;
    }
}
