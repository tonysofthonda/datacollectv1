package com.honda.hdm.datacollect.service.domain;

import com.honda.hdm.datacollect.model.entity.dto.DcContactXDealerDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IDcContactXDealerService extends IDtoService<DcContactXDealerDto, Long>{
    public Page<DcContactXDealerDto> findAllByTermDto(Long dealerId, String term, Pageable pageable);

    public Page<DcContactXDealerDto> findAllDto(Long dealerId, Pageable pageable);

    public Boolean deleteById(Long id);
}
