package com.honda.hdm.datacollect.repository;

import com.honda.hdm.datacollect.model.entity.DcWorkshop;
import com.honda.hdm.datacollect.repository.base.IBaseStatusableRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DcWorkshopRepository extends IBaseStatusableRepository<DcWorkshop, Long>{
	
	  public DcWorkshop findByName(String name);
   
}
