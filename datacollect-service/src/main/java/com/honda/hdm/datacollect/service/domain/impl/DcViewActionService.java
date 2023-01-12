package com.honda.hdm.datacollect.service.domain.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.honda.hdm.datacollect.model.entity.DcViewAction;
import com.honda.hdm.datacollect.repository.DcViewActionRepository;
import com.honda.hdm.datacollect.service.domain.BaseDomainService;

@Service
public class DcViewActionService extends BaseDomainService<DcViewAction, Long>{
    
    private static final Logger LOGGER = LogManager.getLogger(DcViewActionService.class);
    
    @Autowired
    DcViewActionRepository repository;
    
    public List<DcViewAction> findAllByLdapGroups(List<String> ldapGroup){
    	return repository.findAllByLdapGroups(ldapGroup);
    }
    
    public List<DcViewAction> findAllByActionNameAndLdapGroups(String actionName, List<String> ldapGroup){
    	return repository.findAllByActionNameAndLdapGroups(actionName, ldapGroup);
    }

}
