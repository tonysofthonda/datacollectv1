/*
 * Honda de Mexico 2018
 * All rights reserved
 */
package com.honda.hdm.datacollect.service.domain;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 *
 * @author Cesar Martinez <cesar_x_martinez@hdm.honda.com>
 * @param <T>
 * @param <ID>
 */
public interface IBaseDomainService<T extends Object, ID extends Serializable> {

    public boolean exists(ID id);
    
    public T findOne(ID id);

    public List<T> findAll();

    public List<T> findAll(Sort sort);

    public List<T> findAll(Iterable<ID> itrbl);
    
    public Page<T> findAll(Pageable pageable);

    public long count();

    public void delete(ID id);

    public void delete(T entity);

    public void delete(Iterable<? extends T> entities);

    public void deleteAll();
    
    public void deleteInBatch(Iterable<T> itrbl);

    public void deleteAllInBatch();
    
    public T getOne(ID id);
    
    public <S extends T> S save(S entity);

    public <S extends T> Iterable<S> save(Iterable<S> entities);
    
}
