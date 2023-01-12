/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.service.domain.impl.auth;

import com.honda.hdm.datacollect.model.dto.dbconst.DcRecordStatusEnum;
import com.honda.hdm.datacollect.model.entity.DcPosition;
import com.honda.hdm.datacollect.model.entity.auth.DcRole;
import com.honda.hdm.datacollect.model.entity.dto.DcPositionDto;
import com.honda.hdm.datacollect.model.entity.dto.auth.DcLdapGroupDto;
import com.honda.hdm.datacollect.model.entity.dto.auth.DcRoleDto;
import com.honda.hdm.datacollect.repository.auth.DcRoleRepository;
import com.honda.hdm.datacollect.service.converter.DtoConverter;
import com.honda.hdm.datacollect.service.converter.ModelConverter;
import com.honda.hdm.datacollect.service.domain.BaseDomainService;
import com.honda.hdm.datacollect.service.domain.IDcPositionService;
import com.honda.hdm.datacollect.service.domain.IDcViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author VJC80439
 */
@Service
public class DcRoleService extends BaseDomainService<DcRole, Long> implements IDcRoleService {

	@Lazy
    @Autowired
    DcRoleRepository repository;

    @Lazy
    @Autowired
    IDcPositionService positionService;

    @Lazy
    @Autowired
    IDcLdapGroupService ldapGroupService;

    @Lazy
    @Autowired
    IDcViewService viewService;

    @Lazy
    @Autowired
    DtoConverter dtoConverter;

    @Lazy
    @Autowired
    ModelConverter modelConverter;

    public DcRole findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public DcRoleDto findByNameDto(String name) {
        DcRole role = findByName(name);
        if (role == null) {
            return null;
        }
        return dtoConverter.convertRole(role);
    }

    @Override
    public DcRoleDto saveDto(DcRoleDto dto) {
        dto.setPositions(dto.getPositions().stream().map(this::checkAndSavePosition).collect(Collectors.toList()));
        dto.setLdapGroups(dto.getLdapGroups().stream().map(this::checkAndSaveLdapGroup).collect(Collectors.toList()));
        return dtoConverter.convertRole(save(modelConverter.convertRole(dto)));
    }

    @Override
    public DcRoleDto updateDto(DcRoleDto dto, Long id) {
        DcRole role = findOne(id);
        if(role == null){
            return null;
        }
        role.setName(dto.getName());
        role.setDescription(dto.getDescription());
        role.setPositions(dto.getPositions().stream().map(modelConverter::convertPosition).collect(Collectors.toList()));
        role.setDcLdapGroupList(dto.getLdapGroups().stream().map(modelConverter::convertLdapGroup).collect(Collectors.toList()));
        role.setPermissionList(dto.getPermissions().stream().map(modelConverter::convertViewAction).collect(Collectors.toList()));
        return saveDto(dtoConverter.convertRole(role));
    }

    @Override
    public DcRoleDto findOneDto(Long id) {
        DcRole role = findOne(id);
        if(role == null){
            return null;
        }
        return dtoConverter.convertRole(role);
    }

    @Override
    public List<DcRoleDto> findAllDto() {
        return findAll().stream().map(dtoConverter::convertRole).collect(Collectors.toList());
    }

    @Override
    public Page<DcRoleDto> findAllDto(Pageable pageable) {
        return findAll(pageable).map(dtoConverter::convertRole);
    }

    @Override
    public Page<DcRoleDto> findAllByTermDto(String term, Pageable pageable) {
        return repository.findAllByTerm(term, pageable).map(dtoConverter::convertRole);
    }

    @Override
    public Page<DcRoleDto> findByRecordStatusIdDto(DcRecordStatusEnum dcRecordStatus, Pageable pageable) {
        return findAllDto(pageable);
    }

    private DcPositionDto checkAndSavePosition(DcPositionDto position){
        Boolean saveNew = position.getId() == null;
        if(saveNew){
            return positionService.saveDto(position);
        }else {
            DcPositionDto currentPosition = positionService.findOneDto(position.getId());
            if(!currentPosition.getJobId().equals(position.getJobId()) || !currentPosition.getName().equals(position.getName())){
                currentPosition = positionService.updateDto(position, currentPosition.getId());
            }
            return currentPosition;
        }
    }

    private DcLdapGroupDto checkAndSaveLdapGroup(DcLdapGroupDto ldapGroup){

        Boolean saveNew = ldapGroup.getId() == null;
        if(saveNew){
            return ldapGroupService.saveDto(ldapGroup);
        }else {
            DcLdapGroupDto currentLdapGroup = ldapGroupService.findOneDto(ldapGroup.getId());
            if(!currentLdapGroup.getLdapId().equals(ldapGroup.getLdapId()) || !currentLdapGroup.getName().equals(ldapGroup.getName())){
                currentLdapGroup = ldapGroupService.updateDto(ldapGroup, currentLdapGroup.getId());
            }
            return currentLdapGroup;
        }
    }
}