/*
 * Honda de Mexico 2018
 * All rights reserved
 */
package com.honda.hdm.datacollect.service.domain;

import com.honda.hdm.datacollect.model.dto.dbconst.DcRecordStatusEnum;
import com.honda.hdm.datacollect.model.entity.base.RecordStatusableEntry;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Fabian Fonseca <fabian_fonseca@hdm.honda.com>
 * @param <T>
 * @param <ID>
 */
public interface IRecordStatusableService<T extends RecordStatusableEntry, ID extends Serializable> extends IBaseDomainService<T, ID> {

    /**
     * Find All records that match with the specified Record Status.
     *
     * @param <S> Entity
     * @param dcRecordStatus Record Status (use DcRecordStatusEnum for using
     * valid values)
     * @return Found records
     */
    public <S extends T> List<S> findByRecordStatusId(DcRecordStatusEnum dcRecordStatus);

    public <S extends T> Page<S> findByRecordStatusId(DcRecordStatusEnum dcRecordStatus, Pageable pageable);

    /**
     * Find All records that match with the specified Record Statuses
     *
     * @param <S> Entity
     * @param dcRecordStatusList List of Record Status (use DcRecordStatusEnum
     * for using valid values)
     * @return Found records
     */
    public <S extends T> List<S> findByRecordStatusIdIn(List<DcRecordStatusEnum> dcRecordStatusList);

    public <S extends T> List<S> findAllEnables();

    public T update(T obj, ID id);

    public void disable(T obj);

    public void disable(ID id);

    public void enable(T obj);

    public void enable(ID id);

    public void softDelete(T obj);

    public void softDelete(ID id);

}
