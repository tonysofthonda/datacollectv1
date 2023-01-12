package com.honda.hdm.datacollect.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.honda.hdm.datacollect.model.entity.auth.DcLdapGroup;
import com.honda.hdm.datacollect.model.entity.auth.DcRole;
import com.honda.hdm.datacollect.service.domain.impl.DcViewActionService;
import com.honda.hdm.datacollect.service.domain.impl.auth.DcLdapGroupService;
import com.honda.hdm.datacollect.service.domain.impl.auth.DcRoleService;
import com.honda.hdm.datacollect.web.dto.LdapGroupDto;
import com.honda.hdm.datacollect.web.dto.RoleDto;
import com.honda.hdm.datacollect.web.mapper.LdapGroupMapper;
import com.honda.hdm.datacollect.web.mapper.RoleMapper;

/**
 *
 * @author VJC80496
 */
@Controller
@RequestMapping("role")
public class RoleController {

	@Autowired
	private DcRoleService dcRoleService;
	
	@Autowired
	private DcLdapGroupService ldapGroupService;
	
	@Autowired
	private DcViewActionService viewActionService;
	
	@Autowired
	private LdapGroupMapper ldapGroupMapper;
	
	@Autowired
	private RoleMapper roleMapper;

	@ModelAttribute
	public void init(Model model) {
	}

	/**
	 * To get a list of roles.
	 *
	 * @param model
	 * @return
	 */
	@GetMapping(path = { "list" })
	@PreAuthorize("hasPermission('roles', 'view')")
	public String list(Model model) {
		List<DcRole> roleList = dcRoleService.findAll();
		model.addAttribute("roleList", roleList);
		return "role/list";
	}
	
    /**
     * To show form for adding a new record
     *
     * @param model
     * @return String representing the view to resolve
     */
    @GetMapping(path = {"add"})
    @PreAuthorize("hasPermission('roles', 'create')")
    public String add(Model model) {
        model.addAttribute("edit", false);
        model.addAttribute("roleData", new DcRole());
        model.addAttribute("ldapGroupList", new ArrayList<>());
        return "role/form";
    }
    
    @PostMapping(path = {"add"})
    @PreAuthorize("hasPermission('roles', 'create')")
    @Transactional
    public ResponseEntity<?> add(@RequestBody RoleDto roleDto, BindingResult result, final RedirectAttributes redirectAttributes) {
        try {
	    	List<String> validations = validateDealerForm(roleDto);
	        if(!validations.isEmpty()){
	            return new ResponseEntity<>(validations, HttpStatus.INTERNAL_SERVER_ERROR); 
	        }
	        
	        DcRole dcRole = roleMapper.mapFromDto(roleDto);
	        dcRole.setDcLdapGroupList(saveLdapGroups(roleDto.getLdapGroupList()));
	        dcRoleService.save(dcRole);
	        return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e) {
        	return new ResponseEntity<>(Arrays.asList("Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR); 		
        }
    }    
    
    private List<String> validateDealerForm(RoleDto roleFormData){
        List<String> result = new ArrayList<>();
        
        DcRole foundRole;
        String roleName = roleFormData.getName();
        if(roleName == null || roleName.trim().equals("")){
        	result.add("Role name can not be empty");
        }else{    
        	foundRole = dcRoleService.findByName(roleName);
            if(foundRole != null && !foundRole.getId().equals(roleFormData.getId())){
                result.add("Role already exist");
            }
        }
        if (roleFormData.getLdapGroupList() == null || roleFormData.getLdapGroupList().isEmpty()){
            result.add("LDAP groups can not be empty");
        }
        if (roleFormData.getPermissionList() == null || roleFormData.getPermissionList().isEmpty()){
            result.add("Permission list can not be empty");
        }
        
        return result;
    }
    
    private List<DcLdapGroup> saveLdapGroups(List<LdapGroupDto> ldapGroups) {
    	return ldapGroups.stream().map(ldapG ->{
    		DcLdapGroup foundGroup = ldapGroupService.findOneByLdapId(ldapG.getLdapId().trim());
    		if(foundGroup == null) {
    			foundGroup = ldapGroupMapper.mapFromDto(ldapG);
    			ldapGroupService.save(foundGroup);
    		}
    		return foundGroup;
    	}).collect(Collectors.toList());
    }    
    
    @GetMapping(path = {"saved"})
    @PreAuthorize("hasPermission('roles', 'view')")
    public String saved(Model model, final RedirectAttributes redirectAttributes) {
    	redirectAttributes.addFlashAttribute("message", "Role saved successfully.");
        return "redirect:list";
    }
    
    /**
     * Delete a role from database
     * @param id
     * @return ModelAndView
     */
    @GetMapping(path = "delete/{id}")
    @PreAuthorize("hasPermission('roles', 'delete')")
    public ModelAndView delete(@PathVariable("id") Long id, RedirectAttributes ra) {
    	DcRole foundRole = dcRoleService.findOne(id);
    	foundRole.setDcLdapGroupList(new ArrayList<>());
    	foundRole.setPermissionList(new ArrayList<>());
    	dcRoleService.save(foundRole);
    	dcRoleService.delete(foundRole);
    	ra.addFlashAttribute("message", "Role deleted successfully");
        return new ModelAndView(new RedirectView("/role/list", true));
    }
    
    @GetMapping(path = {"edit/{id}"})
    @PreAuthorize("hasPermission('roles', 'edit')")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("edit", true);
        DcRole dcRole= dcRoleService.findOne(id);
        model.addAttribute("roleData", dcRole);
        model.addAttribute("ldapGroupList",dcRole.getDcLdapGroupList());
        return "role/form";
    }
}
