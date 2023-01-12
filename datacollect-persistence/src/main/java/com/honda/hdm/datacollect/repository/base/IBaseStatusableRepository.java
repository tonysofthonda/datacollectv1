/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.repository.base;

import com.honda.hdm.datacollect.model.dto.dbconst.DcRecordStatusEnum;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author VJC80439
 */
@NoRepositoryBean
public interface IBaseStatusableRepository<T extends Object, ID extends Serializable> extends IBaseRepository<T, ID> {

    public List<T> findAll();

    /**
     * Find All records that match with the specified Record Status.
     *
     * @param <S> Entity
     * @param dcRecordStatusId Record Status (use DcRecordStatusEnum for using
     * valid values)
     * @return Found records
     */
    @Query("select e from #{#entityName} e where e.dcRecordStatusId = :dcRecordStatusId")
    public <S extends T> List<S> findByRecordStatusId(@Param("dcRecordStatusId") Integer dcRecordStatus);

    /**
     * Find All records that match with the specified Record Status.
     *
     * @param <S> Entity
     * @param dcRecordStatusId Record Status (use DcRecordStatusEnum for using
     * valid values)
     * @return Found records
     */
    @Query("select e from #{#entityName} e where e.dcRecordStatusId = :dcRecordStatusId")
    public <S extends T> Page<S> findByRecordStatusId(@Param("dcRecordStatusId") Integer dcRecordStatus, Pageable pageable);

    /**
     * Find All records that match with the specified Record Statuses
     *
     * @param <S> Entity
     * @param dcRecordStatusIdList List of Record Status (use DcRecordStatusEnum
     * for using valid values)
     * @return Found records
     */
    @Query("select e from #{#entityName} e where e.dcRecordStatusId in :dcRecordStatusList")
    public <S extends T> List<S> findByRecordStatusIdIn(@Param("dcRecordStatusList") List<Integer> dcRecordStatusList);

    /**
     * Find All records that match with the specified Record Statuses
     *
     * @param <S> Entity
     * @param dcRecordStatusIdList List of Record Status (use DcRecordStatusEnum
     * for using valid values)
     * @return Found records
     */
    @Query("select e from #{#entityName} e where e.dcRecordStatusId in :dcRecordStatusList")
    public <S extends T> Page<S> findByRecordStatusIdIn(@Param("dcRecordStatusList") List<Integer> dcRecordStatusList, Pageable pageable);

    

}
