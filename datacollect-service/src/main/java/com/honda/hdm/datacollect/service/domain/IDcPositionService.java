package com.honda.hdm.datacollect.service.domain;

import com.honda.hdm.datacollect.model.entity.DcPosition;
import com.honda.hdm.datacollect.model.entity.dto.DcPositionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDcPositionService extends IRecordStatusableService<DcPosition, Long>,
        IDtoService<DcPositionDto, Long>{

    public DcPosition findOneByJobId(String jobId);

    public List<DcPosition> findByRoleId(Long roleId);

    public Page<DcPosition> findByRoleId(Long roleId, Pageable pageable);

    public List<DcPosition> findAllByTermWithRoleId(String term, Long roleId);

    public Page<DcPosition> findAllByTermWithRoleId(String term, Long roleId, Pageable pageable);

    public Page<DcPosition> findAllByTerm(String term, Pageable pageable);

    public List<DcPositionDto> findAllDto();

    public DcPositionDto findOneByJobIdDto(String jobId);

    public Page<DcPositionDto> findByRoleIdDto(Long roleId, Pageable pageable);

    public Page<DcPositionDto> findAllByTermWithRoleIdDto(String term, Long roleId, Pageable pageable);

}
