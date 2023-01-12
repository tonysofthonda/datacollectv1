/*
 * Honda de Mexico 2018.
 * All rights reserved.
 */
package com.honda.hdm.datacollect.service.csv;

import com.honda.hdm.datacollect.model.dto.csv.BaseBeanIoRecord;
import com.honda.hdm.datacollect.model.dto.csv.D3VinInformationRecord;
import com.honda.hdm.datacollect.model.dto.csv.G6ServiceOrderTotalRecord;
import com.honda.hdm.datacollect.model.dto.csv.RecordFormatUtil;
import com.honda.hdm.datacollect.model.exception.DataCollectBusinessLogicException;
import com.honda.hdm.datacollect.service.domain.impl.DcModelService;
import com.honda.hdm.datacollect.service.domain.impl.DcOrderStatusService;
import com.honda.hdm.datacollect.service.domain.impl.DcOrderTypeService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class G6BusinessLogicValidatorImpl implements IDcNodeValidator<G6ServiceOrderTotalRecord>{

    @Autowired
    DcModelService dcModelService;
    
    @Autowired
    DcOrderTypeService dcOrderTypeService;
    
    @Autowired
    DcOrderStatusService dcOrderStatusService;
    
    @Override
    public <S extends BaseBeanIoRecord> List<String> validateWithDependencies(G6ServiceOrderTotalRecord g6ServiceOrderTotalRecord, S relatedRecord) throws DataCollectBusinessLogicException {
        if (!(relatedRecord instanceof D3VinInformationRecord)){
            throw new DataCollectBusinessLogicException("A D3VinInformationRecord associated is required for validate the G6ServiceOrderTotalRecord");
        }
        
        List<String> issueListG6 = new ArrayList<>();    
        D3VinInformationRecord d3VinInformationRecord = (D3VinInformationRecord)relatedRecord;   
    	byte orderStatus = d3VinInformationRecord.getOrderStatus();
    	if (orderStatus == 4 && g6ServiceOrderTotalRecord == null) {
    		issueListG6.add("G6: D3 ORDER STATUS IS " + orderStatus + " AND NODE G6 IS REQUIRED");
    	}else if(g6ServiceOrderTotalRecord != null){
    		
    		/*VALIDATE LABOR TOTAL*/
    		BigDecimal laborTotal = g6ServiceOrderTotalRecord.getLaborTotal();
    		if(laborTotal == null) {
    			issueListG6.add("G6: LABOR TOTAL " + RecordFormatUtil.REQUIRED_ERROR);
    		}else if (!laborTotal.toString().matches(RecordFormatUtil.REGEX_DECIMAL_OPTIONAL_9INT_4DEC)){
    			issueListG6.add("G6: LABOR TOTAL " + laborTotal + " " + RecordFormatUtil.PATTERN_ERROR);
            }
    		
    		/*VALIDATE TOTAL PARTS*/
    		BigDecimal partTotal = g6ServiceOrderTotalRecord.getPartTotal();
    		if(partTotal == null) {
    			issueListG6.add("G6: TOTAL PARTS " + RecordFormatUtil.REQUIRED_ERROR);
    		}else if (!partTotal.toString().matches(RecordFormatUtil.REGEX_DECIMAL_OPTIONAL_9INT_4DEC)){
    			issueListG6.add("G6: TOTAL PARTS " + partTotal + " " + RecordFormatUtil.PATTERN_ERROR);
            }
    		
    		/*VALIDATE DISCOUNT TOTAL*/
    		BigDecimal discountTotal = g6ServiceOrderTotalRecord.getDiscountTotal();
    		if(discountTotal == null) {
    			issueListG6.add("G6: DISCOUNT TOTAL " + RecordFormatUtil.REQUIRED_ERROR);
    		}else if (!discountTotal.toString().matches(RecordFormatUtil.REGEX_DECIMAL_OPTIONAL_9INT_4DEC)){
    			issueListG6.add("G6: DISCOUNT TOTAL " + discountTotal + " " + RecordFormatUtil.PATTERN_ERROR);
    		}
    		
    		/*VALIDATE SUBTOTAL*/
    		BigDecimal subTotal = g6ServiceOrderTotalRecord.getSubtotal();
    		if(subTotal == null) {
    			issueListG6.add("G6: SUB TOTAL " + RecordFormatUtil.REQUIRED_ERROR);
    		}else if (!subTotal.toString().matches(RecordFormatUtil.REGEX_DECIMAL_OPTIONAL_9INT_4DEC)){
    			issueListG6.add("G6: SUB TOTAL " + subTotal + " " + RecordFormatUtil.PATTERN_ERROR);
    		}
    		
    		/*VALIDATE TAXES TOTAL*/
    		BigDecimal taxesTotal = g6ServiceOrderTotalRecord.getSubtotal();
    		if(taxesTotal == null) {
    			issueListG6.add("G6: TAXES TOTAL " + RecordFormatUtil.REQUIRED_ERROR);
    		}else if (!taxesTotal.toString().matches(RecordFormatUtil.REGEX_DECIMAL_OPTIONAL_9INT_4DEC)){
    			issueListG6.add("G6: TAXES TOTAL " + taxesTotal + " " + RecordFormatUtil.PATTERN_ERROR);
    		}
    	}
    	return issueListG6;
    }

    @Override
    public List<String> validate(G6ServiceOrderTotalRecord g6ServiceOrder) throws DataCollectBusinessLogicException {
    	throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public <S extends BaseBeanIoRecord> List<String> validateWithDependencies(G6ServiceOrderTotalRecord beanIoRecord, List<S> beanIoRelatedRecords) throws DataCollectBusinessLogicException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
