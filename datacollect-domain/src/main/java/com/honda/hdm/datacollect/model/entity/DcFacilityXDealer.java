package com.honda.hdm.datacollect.model.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "DC_FACILITY_X_DEALER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DcFacilityXDealer.findAll", query = "SELECT d FROM DcFacilityXDealer d")
    , @NamedQuery(name = "DcFacilityXDealer.findByDcFacilityId", query = "SELECT d FROM DcFacilityXDealer d WHERE d.dcFacilityXDealerPK.dcFacilityId = :dcFacilityId")
    , @NamedQuery(name = "DcFacilityXDealer.findByDcDealerId", query = "SELECT d FROM DcFacilityXDealer d WHERE d.dcFacilityXDealerPK.dcDealerId = :dcDealerId")
    , @NamedQuery(name = "DcFacilityXDealer.findByQuantity", query = "SELECT d FROM DcFacilityXDealer d WHERE d.quantity = :quantity")})
public class DcFacilityXDealer implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected DcFacilityXDealerPK dcFacilityXDealerPK;
    
    @Column(name = "QUANTITY")
    private Integer quantity;
    
    public DcFacilityXDealer() {
    }

    public DcFacilityXDealer(DcFacilityXDealerPK dcFacilityXDealerPK) {
        this.dcFacilityXDealerPK = dcFacilityXDealerPK;
    }

    public DcFacilityXDealer(Long dcFacilityId, Long dcDealerId) {
        this.dcFacilityXDealerPK = new DcFacilityXDealerPK(dcFacilityId, dcDealerId);
    }

    public DcFacilityXDealerPK getDcFacilityXDealerPK() {
        return dcFacilityXDealerPK;
    }

    public void setDcFacilityXDealerPK(DcFacilityXDealerPK dcFacilityXDealerPK) {
        this.dcFacilityXDealerPK = dcFacilityXDealerPK;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dcFacilityXDealerPK != null ? dcFacilityXDealerPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DcFacilityXDealer)) {
            return false;
        }
        DcFacilityXDealer other = (DcFacilityXDealer) object;
        if ((this.dcFacilityXDealerPK == null && other.dcFacilityXDealerPK != null) || (this.dcFacilityXDealerPK != null && !this.dcFacilityXDealerPK.equals(other.dcFacilityXDealerPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.honda.hdm.datacollect.model.entity.DcFacilityXDealer[ dcFacilityXDealerPK=" + dcFacilityXDealerPK + " ]";
    }


    
}
