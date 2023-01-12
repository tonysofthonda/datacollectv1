package com.honda.hdm.datacollect.repository;


import org.springframework.stereotype.Repository;

import com.honda.hdm.datacollect.model.entity.DcOrderProcess;
import com.honda.hdm.datacollect.repository.base.IBaseStatusableRepository;

@Repository
public interface DcOrderProcessRepository extends IBaseStatusableRepository<DcOrderProcess, Long>{
	
	public DcOrderProcess findOneByOrderNumberAndVin(String orderNumber,String vin);

	public DcOrderProcess findOneByOrderNumberAndVinAndStatus(String orderNumber, String vin, String status);

}
