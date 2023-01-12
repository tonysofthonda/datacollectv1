package com.honda.hdm.datacollect.web.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.honda.hdm.datacollect.model.entity.DcViewAction;
import com.honda.hdm.datacollect.web.dto.ViewActionDto;

@Component
public class ViewActionMapper {
        
    public List<ViewActionDto> mapToDto(List<DcViewAction> originalList){
    	return originalList.stream().map(view -> {
    		return mapToDto(view);
    	}).collect(Collectors.toList());
    }
    
    public ViewActionDto mapToDto(DcViewAction viewAction){
    	ViewActionDto dto = new ViewActionDto();    	
    	dto.setId(viewAction.getId());
    	dto.setShortName(viewAction.getShortName());
    	dto.setFriendlyName(viewAction.getFriendlyName());
        return dto;
    }
    
    public List<DcViewAction> mapFromDto(List<ViewActionDto> originalList){
    	return originalList.stream().map(dto -> {
    		return mapFromDto(dto);
    	}).collect(Collectors.toList());
    }
    
    public DcViewAction mapFromDto(ViewActionDto dto){
    	DcViewAction viewAction = new DcViewAction();    	
    	viewAction.setId(dto.getId());
    	viewAction.setShortName(dto.getShortName());
    	viewAction.setFriendlyName(dto.getFriendlyName());
        return viewAction;
    }
    
}
