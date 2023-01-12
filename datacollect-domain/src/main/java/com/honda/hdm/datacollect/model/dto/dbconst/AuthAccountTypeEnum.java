/*
 * Honda de Mexico 2018
 * All rights reserved
 */
package com.honda.hdm.datacollect.model.dto.dbconst;

/**
 *
 * @author Cesar Martinez fabian_fonseca@hdm.honda.com>
 */
public enum AuthAccountTypeEnum {
    LDAP ("LDAP"),
    BUILT_IN("Built-in");
    
    private final String value;

    private AuthAccountTypeEnum(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
