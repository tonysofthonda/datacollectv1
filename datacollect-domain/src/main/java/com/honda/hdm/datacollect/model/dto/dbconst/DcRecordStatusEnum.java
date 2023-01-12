/*
 * Honda de Mexico 2018
 * All rights reserved
 */
package com.honda.hdm.datacollect.model.dto.dbconst;

import java.math.BigDecimal;

/**
 *
 * @author Cesar Martinez <cesar_x_martinez@hdm.honda.com>
 */
public enum DcRecordStatusEnum {
    ENABLED(1, "enabled"),
    DISABLED(2, "disabled"),
    DELETED(3, "deleted");

    private final int id;
    private final String name;

    private DcRecordStatusEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public static DcRecordStatusEnum valueOf(int statusId){
        for(DcRecordStatusEnum item: DcRecordStatusEnum.values()){
            if(item.getId()==statusId){
                return item;
            }
        }
        return null;
    }
}
