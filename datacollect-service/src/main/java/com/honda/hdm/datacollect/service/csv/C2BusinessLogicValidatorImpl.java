/*
 * Honda de Mexico 2018.
 * All rights reserved.
 */

package com.honda.hdm.datacollect.service.csv;

import com.honda.hdm.datacollect.model.dto.csv.BaseBeanIoRecord;
import com.honda.hdm.datacollect.model.dto.csv.C2ClientInformationRecord;
import com.honda.hdm.datacollect.model.dto.csv.RecordFormatUtil;
import com.honda.hdm.datacollect.model.exception.DataCollectBusinessLogicException;
import com.honda.hdm.datacollect.service.domain.impl.DcStateService;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 *
 * @author Cesar Martinez <cesar_x_martinez@hdm.honda.com>
 * @since  Oct 4, 2018
 *
 */
@Component
public class C2BusinessLogicValidatorImpl implements IDcNodeValidator<C2ClientInformationRecord>{
    
    @Autowired
    private DcStateService dcStateService;
    
    @Override
    public List<String> validate(C2ClientInformationRecord c2ClientInformationRecord) throws DataCollectBusinessLogicException {
        List<String> issueListC2 = new ArrayList<>();
        
        /*VALIDATE POSTAL CODE*/
        //IS REQUIRED
        if(c2ClientInformationRecord.getAddrPostCode() == null || c2ClientInformationRecord.getAddrPostCode().isEmpty()){
            issueListC2.add("C2: THE POSTCODE " + RecordFormatUtil.REQUIRED_ERROR);
        }
        //HAS LENGHT
        if(c2ClientInformationRecord.getAddrPostCode().length() > 6){
            issueListC2.add("C2: THE POSTCODE " + c2ClientInformationRecord.getAddrPostCode() + " " + RecordFormatUtil.LENGHT_ERROR);
        }
        
        return issueListC2;
    }
    
    public List<String> replaceValuesByDefault(C2ClientInformationRecord c2ClientInformationRecord) {
    	List<String> issueListC2 = new ArrayList<>();
    	
    	/*VALIDATE STREET*/
        //IS REQUIRED
    	String street = c2ClientInformationRecord.getAddrStreet();
        if(street == null || street.trim().isEmpty()){
        	c2ClientInformationRecord.setAddrStreet(null);
        	issueListC2.add("WARNING C2: STREET " + RecordFormatUtil.REQUIRED_ERROR);
        }else if(street.length() > 80){
        	c2ClientInformationRecord.setAddrStreet(street.substring(0, 80));
        	issueListC2.add("WARNING C2: STREET " + street + " " + RecordFormatUtil.LENGHT_ERROR);
        } 
        
       
    	/*VALIDATE NEIGHBORHOOD*/
        //IS REQUIRED
    	String neighborhood = c2ClientInformationRecord.getAddrNeighborhood();
        if(neighborhood == null || neighborhood.trim().isEmpty()){
        	c2ClientInformationRecord.setAddrNeighborhood(null);
        	issueListC2.add("WARNING C2: NEIGHBORHOOD " + RecordFormatUtil.REQUIRED_ERROR);
        }else if(neighborhood.length() > 80){
        	c2ClientInformationRecord.setAddrNeighborhood(neighborhood.substring(0, 80));
        	issueListC2.add("WARNING C2: NEIGHBORHOOD " + street + " " + RecordFormatUtil.LENGHT_ERROR);
        }
        
    	/*VALIDATE EXTERIOR NUMBER*/
        //IS REQUIRED
    	String addrExtNum = c2ClientInformationRecord.getAddrExtNum();
        if(addrExtNum == null || addrExtNum.trim().isEmpty()){
        	c2ClientInformationRecord.setAddrExtNum(null);
        	issueListC2.add("WARNING C2: EXTERIOR NUMBER " + RecordFormatUtil.REQUIRED_ERROR);
        }else if(addrExtNum.length() > 10){
        	c2ClientInformationRecord.setAddrExtNum(addrExtNum.substring(0, 10));
        	issueListC2.add("WARNING C2: EXTERIOR NUMBER " + addrExtNum + " " + RecordFormatUtil.LENGHT_ERROR);
        }
        
        /*VALIDATE INTERIOR NUMBER*/
    	String addrIntNum = c2ClientInformationRecord.getAddrIntNum();
        if(addrIntNum != null && addrIntNum.trim().length() > 10){
        	c2ClientInformationRecord.setAddrIntNum(addrIntNum.substring(0, 10));
        	issueListC2.add("WARNING C2: INTERIOR NUMBER " + addrIntNum + " " + RecordFormatUtil.LENGHT_ERROR);
        }
        
        
        /*VALIDATE STATE*/
        if(!StringUtils.isEmpty(c2ClientInformationRecord.getAddrState())){
        	try {
            	BigInteger dcStateId = dcStateService.findOneByName(c2ClientInformationRecord.getAddrState());
                c2ClientInformationRecord.setIdState(dcStateId);
                if(dcStateId == null) {
                	issueListC2.add("WARNING C2: STATE IS NOT FOUND IN THE CATALOG");
                }
            }catch(Exception ex){
                c2ClientInformationRecord.setAddrState(null);
            }
        }
        
        
    	/*VALIDATE GENDER*/
        //IS REQUIRED
    	String gender = c2ClientInformationRecord.getGender();
        if(gender == null || gender.trim().isEmpty()){
        	c2ClientInformationRecord.setGender(null);
        	issueListC2.add("WARNING C2: GENDER " + RecordFormatUtil.REQUIRED_ERROR);
        }else if(!gender.matches(RecordFormatUtil.REGEX_GENDER)){
        	 //IF GENDER IS DISTINCT H M E THEN VALUE IS NULL
        	c2ClientInformationRecord.setGender(null);
        	issueListC2.add("WARNING C2: GENDER " + gender + " " + RecordFormatUtil.PATTERN_ERROR);
        }
        
        /*VALIDATE EMAIL*/
        //IS REQUIRED
    	String email = c2ClientInformationRecord.getEmail();
        if(email == null || email.trim().isEmpty()){
        	c2ClientInformationRecord.setEmail(null);
        	issueListC2.add("WARNING C2: EMAIL " + RecordFormatUtil.REQUIRED_ERROR);
        }else if(!email.matches(RecordFormatUtil.REGEX_VALID_EMAIL)){
        	//IF EMAIL IS INVALID SET TO NULL
        	c2ClientInformationRecord.setEmail(null);
        	issueListC2.add("WARNING C2: EMAIL " + email + " " + RecordFormatUtil.PATTERN_ERROR);
        }
        
        /*VALIDATE PHONE*/
        //IS REQUIRED
    	String phone = c2ClientInformationRecord.getPhone();
        if(phone == null || phone.trim().isEmpty()){
        	c2ClientInformationRecord.setPhone(null);
        	issueListC2.add("WARNING C2: PHONE " + RecordFormatUtil.REQUIRED_ERROR);
        }else {
        	if(!phone.matches(RecordFormatUtil.REGEX_TELEPHONS_WITH_EXT)) {
	        	//IF EMAIL IS INVALID SET TO NULL
	        	c2ClientInformationRecord.setPhone(null);
	        	issueListC2.add("WARNING C2: PHONE " + phone + " " + RecordFormatUtil.PATTERN_ERROR);
        	}
        	
        	if(phone.length() > 100){
        		c2ClientInformationRecord.setPhone(null);
            	issueListC2.add("WARNING C2: PHONE " + phone + " " + RecordFormatUtil.LENGHT_ERROR);
            }
        }
        
        /*VALIDATE FIRST NAME*/
        //IS REQUIRED
    	String name = c2ClientInformationRecord.getFirstName();
        if(name == null || name.trim().isEmpty()){
        	c2ClientInformationRecord.setFirstName(null);
        	issueListC2.add("WARNING C2: FIRST NAME " + RecordFormatUtil.REQUIRED_ERROR);
        }else if(name.length() > 100){
        	c2ClientInformationRecord.setFirstName(name.substring(0, 100));
        	issueListC2.add("WARNING C2: FIRST NAME " + name + " " + RecordFormatUtil.LENGHT_ERROR);
        }
        
        /*VALIDATE LAST NAME*/
        //IS REQUIRED
    	String paternalName = c2ClientInformationRecord.getLastName();
        if(paternalName == null || paternalName.trim().isEmpty() && gender != null && !gender.equals("E") && !gender.isEmpty()){
        	c2ClientInformationRecord.setLastName(null);
        	issueListC2.add("WARNING C2: LAST NAME " + RecordFormatUtil.REQUIRED_ERROR);
        }else if(paternalName.length() > 40){
        	c2ClientInformationRecord.setLastName(paternalName.substring(0, 40));
        	issueListC2.add("WARNING C2: LAST NAME " + name + " " + RecordFormatUtil.LENGHT_ERROR);
        }
        
        /*VALIDATE MOTHERS LAST NAME*/
        //IS REQUIRED
    	String motherLastName = c2ClientInformationRecord.getLastNameB();
        if(motherLastName == null || motherLastName.trim().isEmpty() && gender != null && !gender.equals("E") && !gender.isEmpty()){
        	c2ClientInformationRecord.setLastNameB(null);
        	issueListC2.add("WARNING C2: MOTHER SURNAME " + RecordFormatUtil.REQUIRED_ERROR);
        }else if(motherLastName.length() > 40){
        	c2ClientInformationRecord.setLastNameB(motherLastName.substring(0, 40));
        	issueListC2.add("WARNING C2: MOTHER SURNAME  " + motherLastName + " " + RecordFormatUtil.LENGHT_ERROR);
        }
        
        /*VALIDATE PRIVACY*/
        //IS REQUIRED
    	String privacy = c2ClientInformationRecord.getPrivacy();
        if(privacy == null || privacy.isEmpty()){
        	c2ClientInformationRecord.setPrivacy(null);
        	issueListC2.add("WARNING C2: PRIVACY " + RecordFormatUtil.REQUIRED_ERROR);
        }else if(!privacy.matches(RecordFormatUtil.REGEX_PRIVACY) ){
        	c2ClientInformationRecord.setPrivacy(null);
        	issueListC2.add("WARNING C2: PRIVACY " + privacy + " " + RecordFormatUtil.PATTERN_ERROR);
        }
    	return issueListC2;
    }
    
    @Override
    public <S extends BaseBeanIoRecord> List<String> validateWithDependencies(C2ClientInformationRecord beanIoRecord, List<S> beanIoRelatedRecords) throws DataCollectBusinessLogicException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public <S extends BaseBeanIoRecord> List<String> validateWithDependencies(C2ClientInformationRecord beanIoRecord, S beanIoRelatedRecords) throws DataCollectBusinessLogicException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

}
