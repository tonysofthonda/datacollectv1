package com.honda.hdm.datacollect.web.dto;

public class DealerFacilityDto {
    private Long dealerId;
    private Long facilityId;
    private String facilityConcept;
    private Integer quantity;

    public Long getDealerId() {
        return dealerId;
}

    public void setDealerId(Long dealerId) {
        this.dealerId = dealerId;
    }

    public Long getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(Long facilityId) {
        this.facilityId = facilityId;
    }

    public String getFacilityConcept() {
        return facilityConcept;
    }

    public void setFacilityConcept(String facilityConcept) {
        this.facilityConcept = facilityConcept;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    
}
