package com.honda.hdm.datacollect.web.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.honda.hdm.datacollect.model.entity.auth.DcRole;
import com.honda.hdm.datacollect.web.dto.RoleDto;

@Component
public class RoleMapper {
	
	@Autowired
	ViewActionMapper viewActionMapper;
	
	@Autowired
	LdapGroupMapper ldapGroupMapper;
	        
    public List<DcRole> mapFromDto(List<RoleDto> originalList){
    	return originalList.stream().map(dto -> {
    		return mapFromDto(dto);
    	}).collect(Collectors.toList());
    }
    
    public DcRole mapFromDto(RoleDto dto){
    	DcRole dcRole = new DcRole();  	
    	dcRole.setId(dto.getId());
    	dcRole.setName(dto.getName());
    	dcRole.setDescription(dto.getDescription());
    	dcRole.setDcLdapGroupList(ldapGroupMapper.mapFromDto(dto.getLdapGroupList()));
    	dcRole.setPermissionList(viewActionMapper.mapFromDto(dto.getPermissionList()));
        return dcRole;
    }
    
}
