package com.honda.hdm.datacollect.model.entity.dto;

import com.honda.hdm.datacollect.model.dto.dbconst.DcRecordStatusEnum;
import com.honda.hdm.datacollect.validation.Alphanumeric;
import com.honda.hdm.datacollect.validation.DealerValid;
import com.honda.hdm.datacollect.validation.Rfc;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@DealerValid
public class DcDealerDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    @NotEmpty
    @Length(min = 5, max = 6)
    private String dealerNumber;
    @NotNull
    @Alphanumeric
    private String name;
    @Alphanumeric
    private String businessName;
    @Rfc
    private String rfc;
    @NotNull
    private DcCityDto city;
    private String street;
    private String neighborhood;
    @Min(value = 10000)
    @Max(value = 99999)
    private Integer postCode;
    @NotNull
    private DcDealerGroupDto dealerGroup;
    @NotNull
    private DcTerchiefDto terchief;
    @NotNull
    private DcWorkshopDto workshop;
    private DcRecordStatusEnum status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDealerNumber() {
        return dealerNumber;
    }

    public void setDealerNumber(String dealerNumber) {
        this.dealerNumber = dealerNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public DcCityDto getCity() {
        return city;
    }

    public void setCity(DcCityDto city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public Integer getPostCode() {
        return postCode;
    }

    public void setPostCode(Integer postCode) {
        this.postCode = postCode;
    }

    public DcDealerGroupDto getDealerGroup() {
        return dealerGroup;
    }

    public void setDealerGroup(DcDealerGroupDto dealerGroup) {
        this.dealerGroup = dealerGroup;
    }

    public DcTerchiefDto getTerchief() {
        return terchief;
    }

    public void setTerchief(DcTerchiefDto terchief) {
        this.terchief = terchief;
    }

    public DcWorkshopDto getWorkshop() {
        return workshop;
    }

    public void setWorkshop(DcWorkshopDto workshop) {
        this.workshop = workshop;
    }

    public DcRecordStatusEnum getStatus() {
        return status;
    }

    public void setStatus(DcRecordStatusEnum status) {
        this.status = status;
    }
}
