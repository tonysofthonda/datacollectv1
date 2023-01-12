/*
 * Honda de Mexico 2018
 * All rights reserved
 */
package com.honda.hdm.datacollect.service.domain;

import com.honda.hdm.datacollect.model.dto.dbconst.DcRecordStatusEnum;
import com.honda.hdm.datacollect.model.entity.base.RecordStatusableEntry;
import com.honda.hdm.datacollect.repository.base.IBaseStatusableRepository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.Assert;

/**
 * Base Service Class for entity driven services that implements the common
 * attribute DcRecordStatusId. When an Entity does not have DcRecordStatusId,
 * then it should extend from BaseDomainService directly.
 *
 * @author Fabian Fonseca <fabian_fonseca@hdm.honda.com>
 * @param <T> Entity
 * @param <ID> Entity ID
 */
public abstract class RecordStatusableService<T extends RecordStatusableEntry, ID extends Serializable> extends BaseDomainService<T, ID> implements IRecordStatusableService<T, ID> {

    @Autowired
    private IBaseStatusableRepository<T, ID> repository;

    @Override
    public <S extends T> List<S> findByRecordStatusId(DcRecordStatusEnum dcRecordStatus) {
        Assert.notNull(dcRecordStatus, "RecordStatus criteria required for searching");

        return repository.findByRecordStatusId(dcRecordStatus.getId());
    }

    @Override
    public <S extends T> Page<S> findByRecordStatusId(DcRecordStatusEnum dcRecordStatus, Pageable pageable) {
        Assert.notNull(dcRecordStatus, "RecordStatus criteria required for searching");

        return repository.findByRecordStatusId(dcRecordStatus.getId(), pageable);
    }

    @Override
    public <S extends T> List<S> findByRecordStatusIdIn(List<DcRecordStatusEnum> dcRecordStatusList) {
        Assert.notEmpty(dcRecordStatusList, "RecordStatusList criteria required for searching");

        List<Integer> dcRecordStatusIdList = new ArrayList<Integer>();
        for (DcRecordStatusEnum item : dcRecordStatusList) {
            dcRecordStatusIdList.add(item.getId());
        }
        return repository.findByRecordStatusIdIn(dcRecordStatusIdList);
    }

    @Override
    public <S extends T> List<S> findAllEnables() {
        return findByRecordStatusId(DcRecordStatusEnum.ENABLED);
    }

    @Override
    public void disable(T obj) {
        Assert.notNull(obj, "Entity to be disabled must not be null");
        obj.setDcRecordStatusId(DcRecordStatusEnum.DISABLED);
        save(obj);
    }

    @Override
    public void disable(ID id) {
        Assert.notNull(id, "Entity ID to be disabled must not be null");
        T obj = findOne(id);
        Assert.notNull(obj, "Entity with specified ID not found to be disabled");
        obj.setDcRecordStatusId(DcRecordStatusEnum.DISABLED);
        save(obj);
    }

    @Override
    public void enable(T obj) {
        Assert.notNull(obj, "Entity to be enabled must not be null");
        obj.setDcRecordStatusId(DcRecordStatusEnum.ENABLED);
        save(obj);
    }

    @Override
    public void enable(ID id) {
        Assert.notNull(id, "Entity ID to be enabled must not be null");
        T obj = findOne(id);
        Assert.notNull(obj, "Entity with specified ID not found to be disabled");
        obj.setDcRecordStatusId(DcRecordStatusEnum.ENABLED);
        save(obj);
    }

    @Override
    public void softDelete(T obj) {
        Assert.notNull(obj, "Entity to be soft deleted must not be null");
        obj.setDcRecordStatusId(DcRecordStatusEnum.DELETED);
        save(obj);
    }

    @Override
    public void softDelete(ID id) {
        Assert.notNull(id, "Entity ID to be soft deleted must not be null");
        T obj = findOne(id);
        Assert.notNull(obj, "Entity with specified ID not found to be soft deleted");
        obj.setDcRecordStatusId(DcRecordStatusEnum.DELETED);
        save(obj);
    }
}
