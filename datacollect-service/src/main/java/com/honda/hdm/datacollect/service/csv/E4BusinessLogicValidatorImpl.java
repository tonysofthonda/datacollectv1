/*
 * Honda de Mexico 2018.
 * All rights reserved.
 */
package com.honda.hdm.datacollect.service.csv;

import com.honda.hdm.datacollect.model.dto.csv.BaseBeanIoRecord;
import com.honda.hdm.datacollect.model.dto.csv.D3VinInformationRecord;
import com.honda.hdm.datacollect.model.dto.csv.E4LaborInformationRecord;
import com.honda.hdm.datacollect.model.dto.csv.RecordFormatUtil;
import com.honda.hdm.datacollect.model.entity.DcOperationCode;
import com.honda.hdm.datacollect.model.enums.ServiceTypeEnum;
import com.honda.hdm.datacollect.model.exception.DataCollectBusinessLogicException;
import com.honda.hdm.datacollect.service.domain.impl.DcOperationCodeService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Cesar Martinez <cesar_x_martinez@hdm.honda.com>
 */
@Component
public class E4BusinessLogicValidatorImpl implements IDcListNodeValidator<E4LaborInformationRecord>{
	
	@Autowired
	private DcOperationCodeService dcOperationCodeService;
	
	private Long dealerNumber;
	
    @Override
    public List<String> validate(List<E4LaborInformationRecord> e4List) throws DataCollectBusinessLogicException {
        return null;
    }
    
    @Override
    public <S extends BaseBeanIoRecord> List<String> validateWithDependencies(List<E4LaborInformationRecord> e4List, S relatedRecord) throws DataCollectBusinessLogicException {
        if (!(relatedRecord instanceof D3VinInformationRecord)){
            throw new DataCollectBusinessLogicException("A D3VinInformationRecord associated is required for validate the E4LaborInformationRecord List");
        }
        D3VinInformationRecord d3VinInformationRecord = (D3VinInformationRecord)relatedRecord;
        List<String> issueListD3 = new ArrayList<>();
        
        String orderType = d3VinInformationRecord.getOrderType();
        if(orderType.equals("VM") || orderType.equals("VMY")){
            if(e4List != null && !e4List.isEmpty()){
                issueListD3.add("E4: D3 ORDER TYPE IS " + d3VinInformationRecord.getOrderType() + ", E4 MUST BE EMPTY");
            }                 
        } else {
        	if(!(d3VinInformationRecord.getOrderStatus() == 1 || d3VinInformationRecord.getOrderStatus() == 2 || d3VinInformationRecord.getOrderStatus() == 3) && e4List == null) {
            	issueListD3.add("E4: NODE E4 " + RecordFormatUtil.REQUIRED_ERROR);
        	}else {
                if(e4List != null) {
                	for(E4LaborInformationRecord e4Object : e4List){
                        /*VALIDATE OPERATION CODE*/
                    	//IS REQUIRED
                    	String operationCode = e4Object.getOperationCode();
                    	if(operationCode == null){
                            issueListD3.add("E4: OPERATION CODE " + RecordFormatUtil.REQUIRED_ERROR);
                        }else {
                        	operationCode = operationCode.trim();
                        	e4Object.setOperationCode(operationCode);
                        	//EXIST IN THE DATABASE(CATALOG)
                            try {
                            	DcOperationCode dcOperationCode= dcOperationCodeService.findOneByCode(operationCode);
                            	if(dcOperationCode == null) {
                            		issueListD3.add("E4: OPERATION CODE " + operationCode + " DOESNT EXIST IN THE OPERATION CODE CATALOG");
                            	}else {
                            		String serviceInOperation = dcOperationCode.getServiceType();
                            		if(serviceInOperation.equals(ServiceTypeEnum.BP.toString()) && this.dealerNumber < 32000) {
                            			issueListD3.add("E4: THE OPERATION CODE DOES NOT APPLY TO THE RANGE OF DEALER SENT");
                            		}
                            		if(serviceInOperation.equals(ServiceTypeEnum.SE.toString()) && this.dealerNumber > 29999) {
                            			issueListD3.add("E4: THE OPERATION CODE DOES NOT APPLY TO THE RANGE OF DEALER SENT");
                            		}
                            	}
                            }catch(Exception ex){
                            	issueListD3.add("E4: OPERATION CODE " + operationCode + " DOESNT EXIST IN THE OPERATION CODE CATALOG");
                            }
                        }
                    	
                    	/*VALIDATE DESCRIPTION*/
                    	//HAS LENGTH
                        if (e4Object.getDescription() != null && e4Object.getDescription().length() > 150) {
                      		issueListD3.add("E4: DESCRIPTION " + RecordFormatUtil.LENGHT_ERROR);
                      	}
                    	
                        Double hours = e4Object.getHours();
                    	/*VALIDATE HOURS*/
                    	//IS REQUIRED
                        if(hours == null && this.dealerNumber >= 32000 && (orderType.equals("P") || orderType.equals("S"))){
                            issueListD3.add("E4: HOURS" + RecordFormatUtil.REQUIRED_ERROR);
                        }else {
                            //HAS PATTERN 
                            //NOTE: THIS ALSO VALIDATES LENGHT
                            if(!hours.toString().matches(RecordFormatUtil.REGEX_DECIMAL_OPTIONAL_3INT_2DEC)){
                                issueListD3.add("E4: HOURS " + e4Object.getHours() + " " + RecordFormatUtil.LENGHT_ERROR);
                            }
                        }    

                       	//IF OPERATION CODE IS T THE VALUE MUST BE 1
                    	if(operationCode != null && this.dealerNumber >= 10000 && this.dealerNumber <= 20000 
                    			&& operationCode.equals("T") && hours != 1){
                            issueListD3.add("E4: IF OPERATION CODE IS T THE HOUR VALUE MUST BE 1");
                        }
                    	
                    	/*VALIDATE UNIT PRICE*/
                    	//IS REQUIRED
                    	if(e4Object.getUnitPrice() == null){
                            issueListD3.add("E4: UNIT PRICE " + RecordFormatUtil.REQUIRED_ERROR);
                        }else if(!e4Object.getUnitPrice().toString().matches(RecordFormatUtil.REGEX_DECIMAL_OPTIONAL_9INT_4DEC)){
                            issueListD3.add("E4: UNIT PRICE " + e4Object.getUnitPrice() + " " + RecordFormatUtil.PATTERN_ERROR);
                        }
                        	
                    	/*VALIDATE SUB TOTAL*/
                    	//IS REQUIRED
    	            	 if(e4Object.getSubtotal() == null){
    	                     issueListD3.add("E4: SUB-TOTAL " + RecordFormatUtil.REQUIRED_ERROR);
    	                 }else if(!e4Object.getSubtotal().toString().matches(RecordFormatUtil.REGEX_DECIMAL_OPTIONAL_9INT_4DEC)){
    	                     issueListD3.add("E4: SUB TOTAL NUMBER " + e4Object.getSubtotal() + " " + RecordFormatUtil.PATTERN_ERROR);
    	                 }
                    	
                    	/*VALIDATE DISCOUNT PERCENTAGE*/
                    	//IS REQUIRED
                    	 if(e4Object.getDiscountPercent() == null){
                             issueListD3.add("E4: DISCOUN PERCENTAGE " + RecordFormatUtil.REQUIRED_ERROR);
                         }else if(!e4Object.getDiscountPercent().toString().matches(RecordFormatUtil.REGEX_DECIMAL_OPTIONAL_3INT_2DEC)){
                             issueListD3.add("E4: DISCOUN PERCENTAGE " + e4Object.getDiscountPercent() + " " +RecordFormatUtil.PATTERN_ERROR);
                         }
                    	
                    	/*VALIDATE NET AMOUNT*/
                    	//IS REQUIRED
                    	 if(e4Object.getNetTotal() == null){
                             issueListD3.add("E4: NET TOTAL " + RecordFormatUtil.REQUIRED_ERROR);
                         } else if(!e4Object.getNetTotal().toString().matches(RecordFormatUtil.REGEX_DECIMAL_OPTIONAL_9INT_4DEC)){
                             issueListD3.add("E4: NET TOTAL " + e4Object.getNetTotal() + " " + RecordFormatUtil.PATTERN_ERROR);
                         }
                    	
                    	/*VALIDATE LABOR COST*/
                    	//IS REQUIRED IF THE OPERATION CODE IS T
                    	if(operationCode != null && operationCode.equals("T")) { 
                    		if (e4Object.getLaborCost() == null){
                    			issueListD3.add("E4: IF OPERATION CODE IS T THE LABOR COST IS REQUIRED");
                    		}else if(!e4Object.getLaborCost().toString().matches(RecordFormatUtil.REGEX_DECIMAL_OPTIONAL_9INT_4DEC)){
                                issueListD3.add("E4: LABOR COST " + e4Object.getLaborCost() + " " +  RecordFormatUtil.PATTERN_ERROR);
                            }
                    	}
                    }
                }
            } 
        }
        
        return issueListD3;
    }
    
    public void setDealer(String dealerNumber) {
    	this.dealerNumber = new Long(dealerNumber);
    }
    
    
    public List<String> replaceValuesByDefault(List<E4LaborInformationRecord> e4List) {
    	List<String> issueListD3 = new ArrayList<>();
    	if(e4List != null) {
	    	for(E4LaborInformationRecord e4Object : e4List){
	    		if(e4Object != null) {
		    		/*VALIDATE DPTS TECHNICIAN*/
					String dptsTechnical = e4Object.getDptsTechnician().trim();
					//IS REQUIRED IF OPERATION CODE IS DIFERENT THAN T
		    		if(!e4Object.getOperationCode().equals("T") && dptsTechnical.isEmpty()){
		    			e4Object.setDptsTechnician(null);
		    			issueListD3.add("WARNING E4: OPERATION CODE IS " + dptsTechnical + " AND E4 DPTS TECHNICIAN SHOULD NOT BE EMPTY");
		    		}
		    		if (dptsTechnical.length() > 8){
		    			e4Object.setDptsTechnician(null);
		          		issueListD3.add("WARNING E4: DPTS TECHNICIAN " + dptsTechnical + " " + RecordFormatUtil.LENGHT_ERROR);
		          	}
		    		if(!dptsTechnical.isEmpty() && !(dptsTechnical.matches(RecordFormatUtil.REGEX_DPTS_ADVISOR) || dptsTechnical.matches(RecordFormatUtil.REGEX_DPTS_ADVISORBP))){
		    			e4Object.setDptsTechnician(null);
		                issueListD3.add("WARNING E4: DPTS TECHNICIAN " + dptsTechnical + " " + RecordFormatUtil.PATTERN_ERROR);
		            }
	    		}
	    	}
    	}
    	return issueListD3;
    }
    
    @Override
    public <S extends BaseBeanIoRecord> List<String> validateWithDependencies(List<E4LaborInformationRecord> e4List, List<S> beanIoRelatedRecords) throws DataCollectBusinessLogicException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
