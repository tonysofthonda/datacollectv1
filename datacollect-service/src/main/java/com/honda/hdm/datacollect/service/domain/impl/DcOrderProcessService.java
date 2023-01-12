package com.honda.hdm.datacollect.service.domain.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.honda.hdm.datacollect.model.entity.DcOrderProcess;
import com.honda.hdm.datacollect.repository.DcOrderProcessRepository;
import com.honda.hdm.datacollect.service.domain.RecordStatusableService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class DcOrderProcessService extends RecordStatusableService<DcOrderProcess, Long>{
	
	@Autowired
	private DcOrderProcessRepository dcOrderProcessRepository;
	
	public DcOrderProcess findOneByOrderNumberAndVin(String orderNumber, String vin) {
		return dcOrderProcessRepository.findOneByOrderNumberAndVin(orderNumber, vin);
	}
	
	public DcOrderProcess findOneByOrderNumberAndVinAndStatus(String orderNumber, String vin, String status) {
		return dcOrderProcessRepository.findOneByOrderNumberAndVinAndStatus(orderNumber, vin, status);
	}

    @Override
    public DcOrderProcess update(DcOrderProcess obj, Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
