package com.honda.hdm.datacollect.web.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.honda.hdm.datacollect.model.entity.DcView;
import com.honda.hdm.datacollect.web.dto.ViewDto;

@Component
public class ViewMapper {
	
	@Autowired
	ViewActionMapper viewActionMapper;
        
    public List<ViewDto> mapToDto(List<DcView> originalList){
    	return originalList.stream().map(view -> {
    		return mapToDto(view);
    	}).collect(Collectors.toList());
    }
    
    public ViewDto mapToDto(DcView view){
    	ViewDto dto = new ViewDto();
    	dto.setId(view.getId());
    	dto.setName(view.getName());
    	dto.setFriendlyName(view.getFriendlyName());
    	dto.setOrder(view.getOrder());
    	dto.setRoute(view.getRoute());    	
    	
    	dto.setActions(
			view.getViewActions().stream().map(viewAction -> {
	    		return viewActionMapper.mapToDto(viewAction);
	    	}).collect(Collectors.toList())
		);
    	
        return dto;
    }
    
}
