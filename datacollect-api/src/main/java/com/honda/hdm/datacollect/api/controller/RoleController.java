package com.honda.hdm.datacollect.api.controller;

import com.honda.hdm.datacollect.api.helpers.FetchType;
import com.honda.hdm.datacollect.api.helpers.Util;
import com.honda.hdm.datacollect.model.entity.dto.auth.DcRoleDto;
import com.honda.hdm.datacollect.response.FriendlyError;
import com.honda.hdm.datacollect.service.domain.IDcPositionService;
import com.honda.hdm.datacollect.service.domain.impl.auth.IDcLdapGroupService;
import com.honda.hdm.datacollect.service.domain.impl.auth.IDcRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    IDcRoleService roleService;

    @Autowired
    IDcLdapGroupService ldapGroupService;

    @Autowired
    IDcPositionService positionService;

    @GetMapping("/list")
    public ResponseEntity<?> listRoles(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "elementsByPage", defaultValue = "10") Integer elementsByPage,
            @RequestParam(name = "fetchType", defaultValue = "lazy") String fetchType) {
        if (FetchType.LAZY.toString().equals(fetchType)) {
            return new ResponseEntity<>(roleService.findAllDto(PageRequest.of(page, elementsByPage)), HttpStatus.OK);
        } else if (FetchType.EAGER.toString().equals(fetchType)) {
            return new ResponseEntity<>(roleService.findAllDto(), HttpStatus.OK);
        } else {
            Model response = new ExtendedModelMap();
            response.addAttribute("msg", "fetchType required as request param");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRole(@PathVariable Long id) {
        DcRoleDto role = roleService.findOneDto(id);
        if (role == null) {
            Model response = new ExtendedModelMap();
            response.addAttribute("friendlyError", new FriendlyError("Role not found", "warn"));
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

}
