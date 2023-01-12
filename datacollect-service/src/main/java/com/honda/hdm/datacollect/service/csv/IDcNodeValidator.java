/*
 * Honda de Mexico 2018.
 * All rights reserved.
 */
package com.honda.hdm.datacollect.service.csv;

import com.honda.hdm.datacollect.model.dto.csv.BaseBeanIoRecord;
import com.honda.hdm.datacollect.model.exception.DataCollectBusinessLogicException;
import java.util.List;

public interface IDcNodeValidator <T extends BaseBeanIoRecord> {
    
    public List<String> validate(T beanIoRecord) throws DataCollectBusinessLogicException;
    public <S extends BaseBeanIoRecord> List<String> validateWithDependencies(T beanIoRecord, S beanIoRelatedRecords) throws DataCollectBusinessLogicException;
    public <S extends BaseBeanIoRecord> List<String> validateWithDependencies(T beanIoRecord, List<S> beanIoRelatedRecords) throws DataCollectBusinessLogicException;
    
}
