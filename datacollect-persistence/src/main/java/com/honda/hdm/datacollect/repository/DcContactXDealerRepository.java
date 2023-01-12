package com.honda.hdm.datacollect.repository;

import com.honda.hdm.datacollect.model.entity.DcContactXDealer;
import com.honda.hdm.datacollect.repository.base.IBaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DcContactXDealerRepository extends IBaseRepository<DcContactXDealer, Long> {

    @Query("select e from #{#entityName} e where e.dealerId = :dealerId")
    public List<DcContactXDealer> findAllByDealerId(@Param("dealerId") Long dealerId);

    @Query("select e from #{#entityName} e where e.dealerId = :dealerId")
    public Page<DcContactXDealer> findAllByDealerId(@Param("dealerId") Long dealerId, Pageable pageable);

    @Query("select e from #{#entityName} e where e.contact.id = :contactId")
    public List<DcContactXDealer> findAllByContactId(@Param("contactId") Long contactId);

    @Query("select e from #{#entityName} e where e.contact.id = :contactId")
    public Page<DcContactXDealer> findAllByContactId(@Param("contactId") Long contactId, Pageable pageable);

    @Query(nativeQuery = true,
            value = "select * from dcollectdb.dc_contact_x_dealer contactDealer " +
                    "join dcollectdb.dc_contact contact on contactDealer.dc_contact_id = contact.id " +
                    "join dcollectdb.dc_position position on contactDealer.dc_position_id = position.id " +
                    "where contactDealer.dc_dealer_id = :dealerId " +
                    "and (position.name like :term or contact.first_name like :term or contact.last_name like :term or contact.mother_last_name like :term or contact.email like :term or contact.phone_number like :term) order by ?#{#pageable}",
            countQuery = "select count(*) from dcollectdb.dc_contact_x_dealer contactDealer " +
                    "join dcollectdb.dc_contact contact on contactDealer.dc_contact_id = contact.id " +
                    "join dcollectdb.dc_position position on contactDealer.dc_position_id = position.id " +
                    "where contactDealer.dc_dealer_id = :dealerId " +
                    "and (position.name like :term or contact.first_name like :term or contact.last_name like :term or contact.mother_last_name like :term or contact.email like :term or contact.phone_number like :term) order by ?#{#pageable}")
    public Page<DcContactXDealer> findAllByDealerIdTerm(@Param("dealerId") Long dealerId, @Param("term") String term, Pageable pageable);


}
