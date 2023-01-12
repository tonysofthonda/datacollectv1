/**
 * 
 */
package com.honda.hdm.datacollect.service.csv;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.honda.hdm.datacollect.model.dto.csv.A0DealerInformationRecord;
import com.honda.hdm.datacollect.model.dto.csv.BaseBeanIoRecord;
import com.honda.hdm.datacollect.model.dto.csv.RecordFormatUtil;
import com.honda.hdm.datacollect.model.exception.DataCollectBusinessLogicException;

/**
 * @author VJ082361
 *
 */
@Component
public class A0BusinessLogicValidatorImpl implements IDcNodeValidator<A0DealerInformationRecord>{

	@Override
	public List<String> validate(A0DealerInformationRecord a0DealerInformationRecord) throws DataCollectBusinessLogicException {
		List<String> issueListA0 = new ArrayList<>();
		issueListA0.addAll(validateFormat(a0DealerInformationRecord));
		
		return issueListA0;
	}
	
	public List<String> validateFormat(A0DealerInformationRecord a0DealerInformationRecord) throws DataCollectBusinessLogicException {		
		List<String> issueListA0 = new ArrayList<String>();
		
		/*VALIDATE DEALER NUMBER*/
		//IS REQUIRED
		if (a0DealerInformationRecord.getDealerNumber() == null || a0DealerInformationRecord.getDealerNumber().isEmpty()) {
			issueListA0.add("A0: DEALER NUMBER " + RecordFormatUtil.REQUIRED_ERROR);
		}
		//HAS A LENGHT
		if (a0DealerInformationRecord.getDealerNumber().length() < 1 || a0DealerInformationRecord.getDealerNumber().length() > 5) {
			issueListA0.add("A0: DEALER NUMBER " + RecordFormatUtil.LENGHT_ERROR);
		}
		//HAS A PATTERN
		if (a0DealerInformationRecord.getDealerNumber().matches(RecordFormatUtil.REGEX_FIVE_DIGITS)) {
			issueListA0.add("A0: DEALER NUMBER "+ RecordFormatUtil.PATTERN_ERROR);
		}
		
		/*VALIDATE DEALER NAME*/
		//IS REQUIRED
		String dealerName = a0DealerInformationRecord.getDealerName();
		if (dealerName == null || dealerName.isEmpty()) {
			issueListA0.add("A0: DEALER NAME " + RecordFormatUtil.REQUIRED_ERROR);
		}else {
			//HAS LENGHT
			if (dealerName.length() < 1 || dealerName.length() > 100) {
				issueListA0.add("A0: DEALER NAME " + RecordFormatUtil.LENGHT_ERROR);
			}
		}
		return issueListA0;
	}
	
	@Override
	public <S extends BaseBeanIoRecord> List<String> validateWithDependencies(A0DealerInformationRecord a0DealerInformationRecord,
			S beanIoRelatedRecords) throws DataCollectBusinessLogicException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends BaseBeanIoRecord> List<String> validateWithDependencies(A0DealerInformationRecord a0DealerInformationRecord,
			List<S> beanIoRelatedRecords) throws DataCollectBusinessLogicException {
		// TODO Auto-generated method stub
		return null;
	}

}
