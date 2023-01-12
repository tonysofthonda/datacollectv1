/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.model.entity.base;

import com.honda.hdm.datacollect.model.dto.dbconst.DcRecordStatusEnum;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author VJC80439
 */
@MappedSuperclass
public class RecordStatusableEntry extends AuditableEntry implements Serializable{
       
    private static final long serialVersionUID = 1L;
    
    //TODO: change this attr type to enum and use ordinal values starting to 1<
    @Column(name="DC_RECORD_STATUS_ID", nullable=false)
    private int dcRecordStatusId = DcRecordStatusEnum.ENABLED.getId();

    public DcRecordStatusEnum getDcRecordStatusId() {
        return DcRecordStatusEnum.valueOf(dcRecordStatusId);
    }

    public void setDcRecordStatusId(DcRecordStatusEnum dcRecordStatus) {
        this.dcRecordStatusId = dcRecordStatus.getId();
    }
    
    
    
}
