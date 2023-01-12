package com.honda.hdm.datacollect.model.entity;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class DcFacilityXDealerPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "DC_FACILITY_ID")
    private Long dcFacilityId;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "DC_DEALER_ID")
    private Long dcDealerId;

    public DcFacilityXDealerPK() {
    }

    public DcFacilityXDealerPK(Long dcFacilityId, Long dcDealerId) {
        this.dcFacilityId = dcFacilityId;
        this.dcDealerId = dcDealerId;
    }

    public Long getDcFacilityId() {
        return dcFacilityId;
    }

    public void setDcFacilityId(Long dcFacilityId) {
        this.dcFacilityId = dcFacilityId;
    }

    public Long getDcDealerId() {
        return dcDealerId;
    }

    public void setDcDealerId(Long dcDealerId) {
        this.dcDealerId = dcDealerId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dcFacilityId != null ? dcFacilityId.hashCode() : 0);
        hash += (dcDealerId != null ? dcDealerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DcFacilityXDealerPK)) {
            return false;
        }
        DcFacilityXDealerPK other = (DcFacilityXDealerPK) object;
        if ((this.dcFacilityId == null && other.dcFacilityId != null) || (this.dcFacilityId != null && !this.dcFacilityId.equals(other.dcFacilityId))) {
            return false;
        }
        if ((this.dcDealerId == null && other.dcDealerId != null) || (this.dcDealerId != null && !this.dcDealerId.equals(other.dcDealerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.honda.hdm.datacollect.model.entity.DcFacilityXDealerPK[ dcFacilityId=" + dcFacilityId + ", dcDealerId=" + dcDealerId + " ]";
    }
    
}
