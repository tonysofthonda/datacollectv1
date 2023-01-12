package com.honda.hdm.datacollect.repository;

import org.springframework.stereotype.Repository;

import com.honda.hdm.datacollect.model.entity.DcView;
import com.honda.hdm.datacollect.repository.base.IBaseRepository;

@Repository
public interface DcViewRepository extends IBaseRepository<DcView, Long>{
   
}
