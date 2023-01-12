package com.honda.hdm.datacollect.web.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.honda.hdm.datacollect.model.entity.DcFacility;
import com.honda.hdm.datacollect.model.entity.DcFacilityXDealer;
import com.honda.hdm.datacollect.model.entity.DcFacilityXDealerPK;
import com.honda.hdm.datacollect.service.domain.impl.DcFacilityService;
import com.honda.hdm.datacollect.web.dto.DealerFacilityDto;

@Component
public class DealerFacilityMapper {
    
    @Autowired
    DcFacilityService dcFacilityService;
    
    public List<DealerFacilityDto> map(List<DcFacilityXDealer> originalList){
        List<DealerFacilityDto> resultList = new ArrayList<>();
        List<DcFacility> facilities = dcFacilityService.findAll();
        
        for(DcFacilityXDealer item: originalList){
            DealerFacilityDto dto = new DealerFacilityDto();
            dto.setDealerId( item.getDcFacilityXDealerPK().getDcDealerId() );
            dto.setFacilityId( item.getDcFacilityXDealerPK().getDcFacilityId() );
            for(DcFacility facility : facilities){
                if(facility.getId().equals(item.getDcFacilityXDealerPK().getDcFacilityId())){
                    dto.setFacilityConcept(facility.getConcept());
                    break;
                }                
            }       
            dto.setQuantity( item.getQuantity() );
            resultList.add(dto);
        }
        return resultList;
    }
    
    public DcFacilityXDealer map(DealerFacilityDto dto){
        DcFacilityXDealer dealerFacility = new DcFacilityXDealer();
        DcFacilityXDealerPK dcFacilityXDealerPK = new DcFacilityXDealerPK(dto.getFacilityId(), dto.getDealerId());
        dealerFacility.setDcFacilityXDealerPK(dcFacilityXDealerPK);
        dealerFacility.setQuantity(dto.getQuantity());
                
        return dealerFacility;
    }
    
}
