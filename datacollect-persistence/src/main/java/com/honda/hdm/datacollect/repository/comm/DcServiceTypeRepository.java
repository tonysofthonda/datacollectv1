/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.repository.comm;

import com.honda.hdm.datacollect.model.entity.DcServiceType;
import com.honda.hdm.datacollect.repository.base.IBaseStatusableRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author VJC80587
 */
@Repository
public interface DcServiceTypeRepository extends IBaseStatusableRepository<DcServiceType, Long> {

}
