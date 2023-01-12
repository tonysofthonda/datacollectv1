package com.honda.hdm.datacollect.repository;

import com.honda.hdm.datacollect.model.entity.DcFacility;
import com.honda.hdm.datacollect.repository.base.IBaseStatusableRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DcFacilityRepository extends IBaseStatusableRepository<DcFacility, Long>{
    
    
    @Query("select e from #{#entityName} e where e.concept = (:concept)")
    DcFacility findIfExistByConcept(@Param("concept") String concept);

    @Query("select dc from DcFacility dc where (dc.concept like %?1% or dc.description like %?1%) and dc.dcRecordStatusId = 1")
    public Page<DcFacility> findAllByTerm(String term, Pageable pageable);
}
