/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.repository.auth;

import com.honda.hdm.datacollect.model.entity.auth.DcLdapGroup;
import com.honda.hdm.datacollect.repository.base.IBaseRepository;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author VJC80439
 */
@Repository
public interface DcLdapGroupRepository extends IBaseRepository<DcLdapGroup, Long> {
   
    public DcLdapGroup findOneByLdapId(String ldapId);
    
    @Query(value=
            "SELECT lg.* \n" +
            "FROM \n"+ 
            "dcollectdb.DC_LDAP_GROUP lg JOIN dcollectdb.DC_LDAPG_X_ROLE lxr "+ 
            "ON lg.ID=lxr.DC_LDAP_GROUP_ID\n" +
            "WHERE lxr.DC_ROLE_ID = :roleId"
            , nativeQuery = true)
    public List<DcLdapGroup> findAllByRoleId(@Param("roleId")Long roleId);

    @Query(value=
            "SELECT lg.* \n" +
                    "FROM \n"+
                    "dcollectdb.DC_LDAP_GROUP lg JOIN dcollectdb.DC_LDAPG_X_ROLE lxr "+
                    "ON lg.ID=lxr.DC_LDAP_GROUP_ID\n" +
                    "WHERE lxr.DC_ROLE_ID = :roleId order by ?#{#pageable}",
            countQuery = "SELECT count(lg.*) \n" +
                    "FROM \n"+
                    "dcollectdb.DC_LDAP_GROUP lg JOIN dcollectdb.DC_LDAPG_X_ROLE lxr "+
                    "ON lg.ID=lxr.DC_LDAP_GROUP_ID\n" +
                    "WHERE lxr.DC_ROLE_ID = :roleId order by ?#{#pageable}"
            , nativeQuery = true)
    public Page<DcLdapGroup> findAllByRoleId(@Param("roleId")Long roleId, Pageable pageable);

    @Query("select dc from DcLdapGroup dc where dc.name like %?1% or dc.ldapId like %?1%")
    public Page<DcLdapGroup> findAllByTerm(String term, Pageable pageable);

    @Query(value=
            "SELECT lg.* FROM dcollectdb.DC_LDAP_GROUP lg\n" +
                    "JOIN dcollectdb.DC_LDAPG_X_ROLE lxr ON lg.ID=lxr.DC_LDAP_GROUP_ID\n" +
                    "WHERE (lg.name like :term or lg.ldap_id like :term) and " +
                    "lxr.DC_ROLE_ID = :roleId order by ?#{#pageable}",
            countQuery = "SELECT count(lg.*) FROM dcollectdb.DC_LDAP_GROUP lg\n" +
                    "JOIN dcollectdb.DC_LDAPG_X_ROLE lxr ON lg.ID=lxr.DC_LDAP_GROUP_ID\n" +
                    "WHERE (lg.name like :term or lg.ldap_id like :term) and " +
                    "lxr.DC_ROLE_ID = :roleId order by ?#{#pageable}"
            , nativeQuery = true)
    public Page<DcLdapGroup> findAllByTermWithRole(String term, Long roleId, Pageable pageable);
    
}
