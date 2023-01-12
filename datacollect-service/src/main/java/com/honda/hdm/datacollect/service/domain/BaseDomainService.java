/*
 * Honda de Mexico 2018
 * All rights reserved
 */
package com.honda.hdm.datacollect.service.domain;

import com.honda.hdm.datacollect.repository.base.IBaseRepository;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;

/**
 *
 * @author Cesar Martinez <cesar_x_martinez@hdm.honda.com>
 * @param <T> Entity
 * @param <ID> Entity ID
 */
public abstract class BaseDomainService<T extends Object, ID extends Serializable> implements IBaseDomainService<T, ID>{
    
    public static class RecordNotFounException extends RuntimeException{
        public RecordNotFounException(Throwable ex){
            super(ex);
        }
        public RecordNotFounException(String ex){
            super(ex);
        }
    }
    
    @Autowired
    private IBaseRepository<T, ID> repository;

    @Override
    public <S extends T> Iterable<S> save(Iterable<S> entities) {
        Assert.notNull(entities, "Entities to be created must not be null");
        
        return repository.saveAll(entities);
    }

    @Override
    public T findOne(ID id) {
        Assert.notNull(id, "Entity ID to be found must not be null");
        
        return repository.findById(id).get();
    }

    @Override
    public boolean exists(ID id) {
        Assert.notNull(id, "Entity ID to be verified not be null");
        
        return repository.existsById(id);
    }

    @Override
    public List<T> findAll() {        
        return repository.findAll();
    }

    @Override
    public List<T> findAll(Sort sort){
        return repository.findAll(sort);
    }
    
    @Override
    public List<T> findAll(Iterable<ID> ids) {
        Assert.notNull(ids, "Entity IDs to be found must not be null");
        
        return repository.findAll(ids);
    }
    
    @Override
    public Page<T> findAll(Pageable pageable){        
        return repository.findAll(pageable);
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public void delete(ID id){
        Assert.notNull(id, "Entity ID to be deleted must not be null");        
        repository.deleteById(id);
    }
    
    @Override
    public void delete(T entity) {
        Assert.notNull(entity, "Entity to be deleted must not be null");        
        repository.delete(entity);
    }

    @Override
    public void delete(Iterable<? extends T> entities) {
        Assert.notNull(entities, "Entities to be deleted must not be null");        
        repository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
    
    @Override
    public void deleteInBatch(Iterable<T> itrbl){
        Assert.notNull(itrbl, "Entities to be deleted in batch must not be null");        
        repository.deleteInBatch(itrbl);
    }

    @Override
    public void deleteAllInBatch(){
        repository.deleteAllInBatch();
    }
    
    @Override
    public T getOne(ID id){
        Assert.notNull(id, "Entity ID to be gotten must not be null");        
        return repository.getOne(id);
    }
    
    @Override
    public <S extends T> S save(S entity){
        Assert.notNull(entity, "Entity to be created must not be null");        
        return repository.save(entity);
    }
}
