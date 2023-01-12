/*
 * Honda de Mexico 2018
 * All rights reserved
 */
package com.honda.hdm.datacollect.model.entity.base;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Cesar Martinez <cesar_x_martinez@hdm.honda.com>
 */
@MappedSuperclass
public class AuditableEntry implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Basic(optional = false)
    //@NotNull
    @Column(name = "CREATE_TIMESTAMP", nullable = false, insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTimestamp;
    
    @Column(name = "UPDATE_TIMESTAMP", insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTimestamp;

    public Date getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(Date createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    public Date getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(Date updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

    @Override
    public String toString() {
        return "AuditableEntry{" + "createTimestamp=" + createTimestamp + ", updateTimestamp=" + updateTimestamp + '}';
    }
    
}
