package com.honda.hdm.datacollect.repository.auth;

import com.honda.hdm.datacollect.model.entity.auth.DcRole;
import com.honda.hdm.datacollect.repository.base.IBaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DcRoleRepository extends IBaseRepository<DcRole, Long> {

    public DcRole findByName(@Param("name") String name);

    @Query("select dc from DcRole dc where dc.name like %?1% or dc.description like %?1%")
    public Page<DcRole> findAllByTerm(String term, Pageable pageable);
}
