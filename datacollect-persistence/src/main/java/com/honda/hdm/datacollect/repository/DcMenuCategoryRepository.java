package com.honda.hdm.datacollect.repository;

import org.springframework.stereotype.Repository;

import com.honda.hdm.datacollect.model.entity.DcMenuCategory;
import com.honda.hdm.datacollect.repository.base.IBaseRepository;

@Repository
public interface DcMenuCategoryRepository extends IBaseRepository<DcMenuCategory, Long>{
   
}
