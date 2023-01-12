package com.honda.hdm.datacollect.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.honda.hdm.datacollect.model.entity.DcView;
import com.honda.hdm.datacollect.model.entity.DcViewAction;
import com.honda.hdm.datacollect.model.entity.auth.DcRole;
import com.honda.hdm.datacollect.service.domain.impl.DcViewService;
import com.honda.hdm.datacollect.service.domain.impl.auth.DcRoleService;
import com.honda.hdm.datacollect.web.dto.ViewDto;
import com.honda.hdm.datacollect.web.mapper.ViewActionMapper;
import com.honda.hdm.datacollect.web.mapper.ViewMapper;

@Controller
@RequestMapping("view")
public class ViewController {
	
	@Autowired
	private DcViewService viewService;
	
	@Autowired
	ViewMapper viewMapper;
    
	@Autowired
	private DcRoleService dcRoleService;
	
    @GetMapping(path = {"getAll"})
    @ResponseBody
    public List<ViewDto> getAll() {    	
        return viewMapper.mapToDto(viewService.findAll());
    }
    
    @GetMapping(path = {"getViewsAllowedByRole/{roleId}"})
    @ResponseBody
    public ResponseEntity<?> getViewsAllowedByRole(Model model, @PathVariable("roleId") Long roleId) {    	
    	DcRole foundRole = dcRoleService.findOne(roleId);
    	if(foundRole == null) {
    		new ResponseEntity<>("Role does not exist", HttpStatus.INTERNAL_SERVER_ERROR);
    	}
    	
    	List<DcView> allowedViews = foundRole.getPermissionList().stream().map(viewAction -> {
    		return viewAction.getView();
    	})			/* generate main view list */
		.distinct()	/* remove duplicates */	
    	.map(view -> {    		
    		 List<DcViewAction> allowedViewActions = foundRole.getPermissionList().stream()
    				.filter(viewAction -> view.getId().equals(viewAction.getView().getId()))
    				.collect(Collectors.toList());
    		view.setViewActions(allowedViewActions);
    		return view;
    	}).collect(Collectors.toList());
    	
    	return new ResponseEntity<>(viewMapper.mapToDto(allowedViews), HttpStatus.OK);
    }
}
