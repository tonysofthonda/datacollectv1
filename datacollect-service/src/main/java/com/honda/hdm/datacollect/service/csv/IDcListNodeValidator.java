/*
 * Honda de Mexico 2018.
 * All rights reserved.
 */
package com.honda.hdm.datacollect.service.csv;

import com.honda.hdm.datacollect.model.dto.csv.BaseBeanIoRecord;
import com.honda.hdm.datacollect.model.exception.DataCollectBusinessLogicException;
import java.util.List;

public interface IDcListNodeValidator <T extends BaseBeanIoRecord> {
    
    public List<String> validate(List<T> beanIoRecords) throws DataCollectBusinessLogicException;
    public <S extends BaseBeanIoRecord> List<String> validateWithDependencies(List<T> beanIoRecords, S beanIoRelatedRecord) throws DataCollectBusinessLogicException;
    public <S extends BaseBeanIoRecord> List<String> validateWithDependencies(List<T> beanIoRecords, List<S> beanIoRelatedRecords) throws DataCollectBusinessLogicException;
    
}
