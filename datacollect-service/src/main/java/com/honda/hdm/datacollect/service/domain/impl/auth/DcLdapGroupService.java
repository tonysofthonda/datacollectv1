/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.service.domain.impl.auth;

import com.honda.hdm.datacollect.model.dto.dbconst.DcRecordStatusEnum;
import com.honda.hdm.datacollect.model.entity.auth.DcLdapGroup;
import com.honda.hdm.datacollect.model.entity.dto.auth.DcLdapGroupDto;
import com.honda.hdm.datacollect.service.converter.DtoConverter;
import com.honda.hdm.datacollect.service.converter.ModelConverter;
import com.honda.hdm.datacollect.service.domain.BaseDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.honda.hdm.datacollect.repository.auth.DcLdapGroupRepository;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author VJC80439
 */
@Service
public class DcLdapGroupService extends BaseDomainService<DcLdapGroup, Long> implements IDcLdapGroupService{
    
	@Lazy
    @Autowired
    DcLdapGroupRepository repository;

	@Lazy
    @Autowired
    DtoConverter dtoConverter;

	@Lazy
    @Autowired
    ModelConverter modelConverter;

    public DcLdapGroup findOneByLdapId(String ldapId) {
    	return repository.findOneByLdapId(ldapId);
    }
    
    public List<DcLdapGroup> findAllByRoleId(Long roleId) {
        return repository.findAllByRoleId(roleId);
    }

    @Override
    public DcLdapGroupDto saveDto(DcLdapGroupDto dto) {
        return dtoConverter.convertLdapGroup(modelConverter.convertLdapGroup(dto));
    }

    @Override
    public DcLdapGroupDto updateDto(DcLdapGroupDto dto, Long id) {
        DcLdapGroup ldapGroup = findOne(id);
        if(ldapGroup == null){
            return null;
        }
        ldapGroup.setLdapId(dto.getLdapId());
        ldapGroup.setName(dto.getName());
        return dtoConverter.convertLdapGroup(save(ldapGroup));
    }

    @Override
    public DcLdapGroupDto findOneDto(Long id) {
        DcLdapGroup ldapGroup = findOne(id);
        if(ldapGroup == null){
            return null;
        }
        return dtoConverter.convertLdapGroup(ldapGroup);
    }

    @Override
    public Page<DcLdapGroupDto> findAllDto(Pageable pageable) {
        return findAll(pageable).map(dtoConverter::convertLdapGroup);
    }

    @Override
    public Page<DcLdapGroupDto> findAllByTermDto(String term, Pageable pageable) {
        return repository.findAllByTerm(term, pageable).map(dtoConverter::convertLdapGroup);
    }

    @Override
    public Page<DcLdapGroupDto> findByRecordStatusIdDto(DcRecordStatusEnum dcRecordStatus, Pageable pageable) {
        return findAllDto(pageable);
    }

    @Override
    public DcLdapGroupDto findOneByLdapIdDto(String ldapId) {
        DcLdapGroup ldapGroup = findOneByLdapId(ldapId);
        if(ldapGroup == null){
            return null;
        }
        return dtoConverter.convertLdapGroup(ldapGroup);
    }

    @Override
    public Page<DcLdapGroupDto> findAllByRoleIdDto(Long roleId, Pageable pageable) {
        return repository.findAllByRoleId(roleId, pageable).map(dtoConverter::convertLdapGroup);
    }

    @Override
    public List<DcLdapGroupDto> findAllDto() {
        return repository.findAll().stream().map(dtoConverter::convertLdapGroup).collect(Collectors.toList());
    }
}