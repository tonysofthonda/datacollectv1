/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.service.domain;

import com.honda.hdm.datacollect.model.dto.dbconst.DcRecordStatusEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author VJC80587
 * @param <U>
 * @param <ID>
 * @param <E>
 */
public interface IDtoService<U, ID> {

    public U saveDto(U dto);

    public U updateDto(U dto, ID id);

    public U findOneDto(ID id);

    public Page<U> findAllDto(Pageable pageable);

    public Page<U> findAllByTermDto(String term, Pageable pageable);

    public Page<U> findByRecordStatusIdDto(DcRecordStatusEnum dcRecordStatus, Pageable pageable);

}
