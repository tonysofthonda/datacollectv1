package com.honda.hdm.datacollect.service.domain.impl;

import com.honda.hdm.datacollect.model.dto.dbconst.DcRecordStatusEnum;
import com.honda.hdm.datacollect.model.entity.DcFacilityXDealer;
import com.honda.hdm.datacollect.model.entity.DcFacilityXDealerPK;
import com.honda.hdm.datacollect.model.entity.dto.DcDealerDto;
import com.honda.hdm.datacollect.model.entity.dto.DcFacilityDto;
import com.honda.hdm.datacollect.model.entity.dto.DcFacilityXDealerDto;
import com.honda.hdm.datacollect.repository.DcFacilityXDealerRepository;
import com.honda.hdm.datacollect.service.converter.DtoConverter;
import com.honda.hdm.datacollect.service.converter.ModelConverter;
import com.honda.hdm.datacollect.service.domain.BaseDomainService;

import java.util.List;

import com.honda.hdm.datacollect.service.domain.IDcDealerService;
import com.honda.hdm.datacollect.service.domain.IDcFacilityService;
import com.honda.hdm.datacollect.service.domain.IDcFacilityXDealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DcFacilityXDealerService extends BaseDomainService<DcFacilityXDealer, DcFacilityXDealerPK> implements IDcFacilityXDealerService {

    @Autowired
    DcFacilityXDealerRepository repository;

    @Autowired
    IDcDealerService dealerService;

    @Autowired
    IDcFacilityService facilityService;

    @Autowired
    DtoConverter dtoConverter;

    @Autowired
    ModelConverter modelConverter;

    public List<DcFacilityXDealer> findAllByDealerId(Long dealerId) {
        return this.repository.findAllByDealerId(dealerId);
    }

    public Page<DcFacilityXDealer> findAllByDealerId(Long dealerId, Pageable pageable) {
        return repository.findAllByDealerId(dealerId, pageable);
    }

    public Page<DcFacilityXDealer> findAllByDealerIdTerm(Long dealerId, String term, Pageable pageable) {
        return repository.findAllByDealerIdTerm(dealerId, term, pageable);
    }

    public List<DcFacilityXDealer> findAllByFacilityId(Long facilityId) {
        return this.repository.findAllByFacilityId(facilityId);
    }

    public Page<DcFacilityXDealer> findAllByFacilityId(Long facilityId, Pageable pageable) {
        return repository.findAllByFacilityId(facilityId, pageable);
    }

    public DcFacilityXDealer update(DcFacilityXDealer facilityDealer, DcFacilityXDealerPK id) {
        DcFacilityXDealer currentFacilityDealer = findOne(id);
        if (currentFacilityDealer == null) {
            return null;
        }
        currentFacilityDealer.setQuantity(facilityDealer.getQuantity());
        return repository.save(currentFacilityDealer);
    }

    public void delete(Long facilityId, Long dealerId) {
        this.delete(new DcFacilityXDealerPK(facilityId, dealerId));
    }

    @Override
    public DcFacilityXDealerDto saveDto(DcFacilityXDealerDto dto) {
        DcDealerDto dealer = dealerService.findOneDto(dto.getDealer().getId());
        DcFacilityDto facilityDto = facilityService.findOneDto(dto.getFacility().getId());
        if (dealer == null || facilityDto == null) {
            return null;
        }
        return dtoConverter.convertFacilityXDealer(save(modelConverter.convertFacilityXDealer(dto)));
    }

    @Override
    public DcFacilityXDealerDto updateDto(DcFacilityXDealerDto dto, DcFacilityXDealerPK id) {
        DcFacilityXDealer facilityDealer = update(modelConverter.convertFacilityXDealer(dto), id);
        if (facilityDealer == null) {
            return null;
        }
        return dtoConverter.convertFacilityXDealer(facilityDealer);
    }

    @Override
    public DcFacilityXDealerDto findOneDto(DcFacilityXDealerPK id) {
        DcFacilityXDealer facilityDealer = findOne(id);
        if (facilityDealer == null) {
            return null;
        }
        return dtoConverter.convertFacilityXDealer(facilityDealer);
    }

    @Override
    public Page<DcFacilityXDealerDto> findAllDto(Pageable pageable) {
        return repository.findAll(pageable).map((dealerFacility) -> dtoConverter.convertFacilityXDealer(dealerFacility));
    }

    @Override
    public Page<DcFacilityXDealerDto> findAllDto(Long dealerId, Pageable pageable) {
        return repository.findAllByDealerId(dealerId, pageable).map((dealerFacility) -> dtoConverter.convertFacilityXDealer(dealerFacility));
    }

    @Override
    public Page<DcFacilityXDealerDto> findAllByTermDto(String term, Pageable pageable) {
        return findAllDto(pageable);
    }

    @Override
    public Page<DcFacilityXDealerDto> findAllByTermDto(Long dealerId, String term, Pageable pageable) {
        return repository.findAllByDealerIdTerm(dealerId, "%"+term+"%", pageable).map((dealerFacility) -> dtoConverter.convertFacilityXDealer(dealerFacility));
    }

    @Override
    public Page<DcFacilityXDealerDto> findByRecordStatusIdDto(DcRecordStatusEnum dcRecordStatus, Pageable pageable) {
        return findAllDto(pageable);
    }

    @Override
    public Boolean deleteById(DcFacilityXDealerPK id) {
        repository.deleteById(id);
        return true;
    }
}
