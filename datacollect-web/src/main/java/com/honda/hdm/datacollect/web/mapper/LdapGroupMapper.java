package com.honda.hdm.datacollect.web.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.honda.hdm.datacollect.model.entity.auth.DcLdapGroup;
import com.honda.hdm.datacollect.web.dto.LdapGroupDto;

@Component
public class LdapGroupMapper {
	        
    public List<DcLdapGroup> mapFromDto(List<LdapGroupDto> originalList){
    	return originalList.stream().map(dto -> {
    		return mapFromDto(dto);
    	}).collect(Collectors.toList());
    }
    
    public DcLdapGroup mapFromDto(LdapGroupDto dto){
    	DcLdapGroup ldapGroup = new DcLdapGroup();  	
    	ldapGroup.setName(dto.getName().trim());
    	ldapGroup.setLdapId(dto.getLdapId().trim());    	    	
        return ldapGroup;
    }
    
}
