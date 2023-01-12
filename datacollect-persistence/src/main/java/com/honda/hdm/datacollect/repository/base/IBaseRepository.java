/*
 * Honda de Mexico 2018
 * All rights reserved
 */
package com.honda.hdm.datacollect.repository.base;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author Cesar Martinez <cesar_x_martinez@hdm.honda.com>
 *
 * References: - Add EntityManager.refresh to all Spring Data Repositories
 * [https://www.javabullets.com/add-entitymanager-refresh-spring-data-repositories/]
 *
 * * When using on SpringBoot application annotate app using something like:
 * @EnableJpaRepositories(basePackages = {"com.honda.hdm.datacollect"},
 * repositoryBaseClass = BaseRepositoryImpl.class)
 *
 *
 * @param <T> Entity object
 * @param <ID> ID Type Class
 */
@NoRepositoryBean
public interface IBaseRepository<T extends Object, ID extends Serializable> extends PagingAndSortingRepository<T, ID> {

    public List<T> findAll();

    public List<T> findAll(Sort sort);

    public List<T> findAll(Iterable<ID> ids);

    public void deleteInBatch(Iterable<T> itrbl);

    public void deleteAllInBatch();

    public T getOne(ID id);

    /**
     * Extends EntityManager.refresh() method. Default JPA implementation does
     * not allows to refresh entity (from DB).
     *
     * @param <S>
     * @param s Entity object to be refreshed from DB
     */
    public <S extends T> void refresh(S s);
}
