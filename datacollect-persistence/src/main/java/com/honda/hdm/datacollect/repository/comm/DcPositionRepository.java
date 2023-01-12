package com.honda.hdm.datacollect.repository.comm;

import com.honda.hdm.datacollect.model.entity.DcPosition;
import com.honda.hdm.datacollect.repository.base.IBaseStatusableRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DcPositionRepository  extends IBaseStatusableRepository<DcPosition, Long> {

    public DcPosition findOneByJobId(String jobId);

    @Query(value=
            "SELECT pos.* FROM dcollectdb.DC_POSITION pos\n" +
                    "JOIN dcollectdb.DC_POSITION_X_ROLE posrole\n"+
                    "ON pos.ID = posrole.DC_POSITION_ID\n" +
                    "WHERE posrole.DC_ROLE_ID = :roleId"
            , nativeQuery = true)
    public List<DcPosition> findAllByRoleId(@Param("roleId")Long roleId);

    @Query(value=
            "SELECT pos.* FROM dcollectdb.DC_POSITION pos\n" +
                    "JOIN dcollectdb.DC_POSITION_X_ROLE posrole\n"+
                    "ON pos.ID = posrole.DC_POSITION_ID\n" +
                    "WHERE posrole.DC_ROLE_ID = :roleId order by ?#{#pageable}",
            countQuery = "SELECT count(pos.*) FROM dcollectdb.DC_POSITION pos\n" +
                    "JOIN dcollectdb.DC_POSITION_X_ROLE posrole\n"+
                    "ON pos.ID = posrole.DC_POSITION_ID\n" +
                    "WHERE posrole.DC_ROLE_ID = :roleId order by ?#{#pageable}"
            , nativeQuery = true)
    public Page<DcPosition> findAllByRoleId(@Param("roleId")Long roleId, Pageable pageable);

    @Query(value=
            "SELECT pos.* FROM dcollectdb.DC_POSITION pos\n" +
                    "JOIN dcollectdb.DC_POSITION_X_ROLE posrole\n"+
                    "ON pos.ID = posrole.DC_POSITION_ID\n" +
                    "WHERE (pos.jobId like :term or pos.name_desc like :term) and posrole.DC_ROLE_ID = :roleId"
            , nativeQuery = true)
    public List<DcPosition> findAllByTermWithRoleId(@Param("term") String term, @Param("roleId")Long roleId);

    @Query(value=
            "SELECT pos.* FROM dcollectdb.DC_POSITION pos\n" +
                    "JOIN dcollectdb.DC_POSITION_X_ROLE posrole\n"+
                    "ON pos.ID = posrole.DC_POSITION_ID\n" +
                    "WHERE (pos.jobId like :term or pos.name_desc like :term) and posrole.DC_ROLE_ID = :roleId order by ?#{#pageable}",
            countQuery = "SELECT count(pos.*) FROM dcollectdb.DC_POSITION pos\n" +
                    "JOIN dcollectdb.DC_POSITION_X_ROLE posrole\n"+
                    "ON pos.ID = posrole.DC_POSITION_ID\n" +
                    "WHERE (pos.jobId like :term or pos.name_desc like :term) and posrole.DC_ROLE_ID = :roleId order by ?#{#pageable}"
            , nativeQuery = true)
    public Page<DcPosition> findAllByTermWithRoleId(@Param("term") String term, @Param("roleId")Long roleId, Pageable pageable);

    @Query("select dc from DcPosition dc where dc.jobId like %?1% or dc.nameDescription like %?1%")
    public Page<DcPosition> findAllByTerm(String term, Pageable pageable);

}
