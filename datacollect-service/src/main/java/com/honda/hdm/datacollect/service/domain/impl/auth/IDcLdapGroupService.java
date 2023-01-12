/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.service.domain.impl.auth;

import com.honda.hdm.datacollect.model.entity.auth.DcLdapGroup;
import com.honda.hdm.datacollect.model.entity.dto.auth.DcLdapGroupDto;
import com.honda.hdm.datacollect.service.domain.IBaseDomainService;
import java.util.List;

import com.honda.hdm.datacollect.service.domain.IDtoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


public interface IDcLdapGroupService extends IBaseDomainService<DcLdapGroup, Long>, IDtoService<DcLdapGroupDto, Long> {

    public DcLdapGroup findOneByLdapId(String ldapId);

    public List<DcLdapGroup> findAllByRoleId(Long roleId);

    public DcLdapGroupDto findOneByLdapIdDto(String ldapId);

    public Page<DcLdapGroupDto> findAllByRoleIdDto(Long roleId, Pageable pageable);

    public List<DcLdapGroupDto> findAllDto();
}