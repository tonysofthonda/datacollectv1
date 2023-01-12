/*
 * Honda de Mexico 2018
 * All rights reserved
 */
package com.honda.hdm.datacollect.repository.base;

import com.honda.hdm.datacollect.repository.base.IBaseRepository;
import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 *
 * @author Cesar Martinez <cesar_x_martinez@hdm.honda.com>
 * 
 * References: 
 *  - Add EntityManager.refresh to all Spring Data Repositories [https://www.javabullets.com/add-entitymanager-refresh-spring-data-repositories/]
 * 
 * When using on SpringBoot application annotate app using something like:
 * @EnableJpaRepositories(basePackages = {"com.honda.hdm.datacollect"}, repositoryBaseClass = BaseRepositoryImpl.class)
 * 
 * @param <T> Entity Object
 * @param <ID> ID type class
 * 
 */
@NoRepositoryBean
public class BaseRepositoryImpl<T extends Object, ID extends Serializable> 
        extends SimpleJpaRepository<T, ID> implements IBaseRepository<T, ID> {

    private static final Logger LOGGER = LogManager.getLogger(BaseRepositoryImpl.class);
    
    private final EntityManager entityManager;

    public BaseRepositoryImpl(JpaEntityInformation entityInformation, 
            EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    /**
     * Extends EntityManager.refresh() method.
     * Default JPA implementation does not allows to refresh entity (from DB).
     * @param s Entity object to be refreshed from DB
     */
    @Override
    @Transactional
    public <S extends T> void refresh(S s) {
        entityManager.refresh(s);
        LOGGER.debug("Entity of type " + s.getClass().getSimpleName() + " refreshed successfully from database");
    }

	@Override
	public List<T> findAll(Iterable<ID> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteInBatch(Iterable<T> itrbl) {
		// TODO Auto-generated method stub
		
	}

}
