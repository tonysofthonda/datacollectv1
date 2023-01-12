package com.honda.hdm.datacollect.repository;

import com.honda.hdm.datacollect.model.entity.DcFacilityXDealer;
import com.honda.hdm.datacollect.model.entity.DcFacilityXDealerPK;
import com.honda.hdm.datacollect.repository.base.IBaseRepository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DcFacilityXDealerRepository extends IBaseRepository<DcFacilityXDealer, DcFacilityXDealerPK> {

    @Query("select e from #{#entityName} e where e.dcFacilityXDealerPK.dcDealerId = :dealerId")
    public List<DcFacilityXDealer> findAllByDealerId(@Param("dealerId") Long dealerId);

    @Query("select e from #{#entityName} e where e.dcFacilityXDealerPK.dcDealerId = :dealerId")
    public Page<DcFacilityXDealer> findAllByDealerId(@Param("dealerId") Long dealerId, Pageable pageable);

    @Query(nativeQuery = true,
            value = "select * from dcollectdb.dc_facility_x_dealer facilityDealer " +
                    "join dcollectdb.dc_facility facility on facilitydealer.dc_facility_id = facility.id " +
                    "where facilityDealer.dc_dealer_id = :dealerId " +
                    "and (facility.concept like :term or cast(facilityDealer.quantity as VARCHAR2(100)) like :term) order by ?#{#pageable}",
            countQuery = "select count(*) from dcollectdb.dc_facility_x_dealer facilityDealer " +
                    "join dcollectdb.dc_facility facility on facilitydealer.dc_facility_id = facility.id " +
                    "where facilityDealer.dc_dealer_id = :dealerId " +
                    "and (facility.concept like :term or cast(facilityDealer.quantity as VARCHAR2(100)) like :term) order by ?#{#pageable}")
    public Page<DcFacilityXDealer> findAllByDealerIdTerm(@Param("dealerId") Long dealerId, @Param("term") String term, Pageable pageable);


    @Query("select e from #{#entityName} e where e.dcFacilityXDealerPK.dcFacilityId = :facilityId")
    public List<DcFacilityXDealer> findAllByFacilityId(@Param("facilityId") Long facilityId);

    @Query("select e from #{#entityName} e where e.dcFacilityXDealerPK.dcFacilityId = :facilityId")
    public Page<DcFacilityXDealer> findAllByFacilityId(@Param("facilityId") Long facilityId, Pageable pageable);

}
