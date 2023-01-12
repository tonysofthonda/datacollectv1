package com.honda.hdm.datacollect.api.controller;

import com.honda.hdm.datacollect.service.domain.impl.auth.IDcLdapGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ldap-group")
public class LdapGroupController {
    @Autowired
    IDcLdapGroupService ldapGroupService;

    @GetMapping("/list")
    public ResponseEntity<?> listLdapGroups() {
        return new ResponseEntity<>(ldapGroupService.findAllDto(), HttpStatus.OK);
    }

    @GetMapping("/ldap-id/{ldapId}")
    public ResponseEntity<?> listLdapGroups(@PathVariable String ldapId) {
        return new ResponseEntity<>(ldapGroupService.findOneByLdapIdDto(ldapId), HttpStatus.OK);
    }
}
