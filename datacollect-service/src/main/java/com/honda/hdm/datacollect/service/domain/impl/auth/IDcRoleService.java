/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.service.domain.impl.auth;

import com.honda.hdm.datacollect.model.entity.auth.DcRole;
import com.honda.hdm.datacollect.model.entity.dto.auth.DcRoleDto;
import com.honda.hdm.datacollect.service.domain.IBaseDomainService;
import java.math.BigDecimal;
import java.util.List;

import com.honda.hdm.datacollect.service.domain.IDtoService;
import org.springframework.stereotype.Service;


public interface IDcRoleService extends IBaseDomainService<DcRole, Long>, IDtoService<DcRoleDto, Long> {

    public DcRole findByName(String name);

    public DcRoleDto findByNameDto(String name);

    public List<DcRoleDto> findAllDto();
}