package com.honda.hdm.datacollect.model.entity.dto;

import com.honda.hdm.datacollect.validation.FacilityDealerValid;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@FacilityDealerValid
public class DcFacilityXDealerDto {

    @NotNull
    private DcDealerDto dealer;
    @NotNull
    private DcFacilityDto facility;
    @NotNull
    @Min(1)
    private Integer quantity;

    public DcDealerDto getDealer() {
        return dealer;
    }

    public void setDealer(DcDealerDto dealer) {
        this.dealer = dealer;
    }

    public DcFacilityDto getFacility() {
        return facility;
    }

    public void setFacility(DcFacilityDto facility) {
        this.facility = facility;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
