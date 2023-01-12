package com.honda.hdm.datacollect.service.csv;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.honda.hdm.datacollect.model.dto.csv.B1MonthlyFinancialInfoRecord;
import com.honda.hdm.datacollect.model.dto.csv.BaseBeanIoRecord;
import com.honda.hdm.datacollect.model.dto.csv.RecordFormatUtil;
import com.honda.hdm.datacollect.model.exception.DataCollectBusinessLogicException;

@Component
public class B1BusinessLogicValidatorImpl implements IDcNodeValidator<B1MonthlyFinancialInfoRecord> {

	@Override
	public List<String> validate(B1MonthlyFinancialInfoRecord b1MonthlyFinancialInfoRecord) throws DataCollectBusinessLogicException {
		List<String> issueListB1 = new ArrayList<>();
		Optional.ofNullable(b1MonthlyFinancialInfoRecord).ifPresent(b1->{
			
			/*IN THE PROJECT DESCRIPTION FILE  IS DEFINED THAT ALL THIS VALUES MUST HAVE AT LEAST A ZERO (0) AS A VALUE.  
			 * THIS WAS RE-EVALUATED LATER AND THE NODE COULD BE EMPTY AS LONG AS IT HAS THE PIPE (|) SEPARATORS*/
			//NOTE: THIS NOW HAS BEEN ADDED TO DE PROJECT DESCRIPTION FILE (PAGE 9, FIGURE 19)
			String monthTotalExpenses = b1MonthlyFinancialInfoRecord.getMonthTotalExpenses();
			if(!monthTotalExpenses.equals("") && !monthTotalExpenses.equals("0")) {
				/*VALIDATE TOTAL EXPENSES*/
				if(!monthTotalExpenses.matches(RecordFormatUtil.REGEX_DECIMAL_OPTIONAL_9INT_4DEC)) {
					issueListB1.add("B1: THE MONTH TOTAL EXPENSES "+ RecordFormatUtil.PATTERN_ERROR);
				}
				
				/*VALIDATE SELL EXPENSES*/
				String monthSellExpenses = b1MonthlyFinancialInfoRecord.getMonthSellExpenses();
				if(monthSellExpenses == null) {
					issueListB1.add("B1: THE SELL EXPENSES " + RecordFormatUtil.REQUIRED_ERROR);
				}else if(!monthSellExpenses.matches(RecordFormatUtil.REGEX_DECIMAL_OPTIONAL_9INT_4DEC)) {
					issueListB1.add("B1: THE SELL EXPENSES " + RecordFormatUtil.PATTERN_ERROR);
				}
				
				
				/*VALIDATE PROFIT*/
				String monthProfit = b1MonthlyFinancialInfoRecord.getMonthProfit();
				if(monthProfit == null) {
					issueListB1.add("B1: THE PROFIT " + RecordFormatUtil.REQUIRED_ERROR);
				}else if(!monthProfit.matches(RecordFormatUtil.REGEX_DECIMAL_OPTIONAL_9INT_4DEC_REAL)) {
					issueListB1.add("B1: THE PROFIT " + RecordFormatUtil.PATTERN_ERROR);
				}
				

				/*VALIDATE LABOR */
				String monthLaborSum = b1MonthlyFinancialInfoRecord.getMonthLaborSum();
				if(monthLaborSum == null || monthLaborSum.equals("")) {
					issueListB1.add("B1: THE MONTH LABOR SUM " + RecordFormatUtil.REQUIRED_ERROR);
				}else if(!monthLaborSum.matches(RecordFormatUtil.REGEX_DECIMAL_OPTIONAL_9INT_4DEC)) {
					issueListB1.add("B1: THE MONTH LABOR SUM " +  RecordFormatUtil.PATTERN_ERROR);
				}
				
				/*VALIDATE MONTH YEAR*/
				String monthYear = b1MonthlyFinancialInfoRecord.getMonthYear();
				if(monthYear == null) {
					issueListB1.add("B1: THE MONTH YEAR " + RecordFormatUtil.REQUIRED_ERROR);
				} else if(!monthYear.matches(RecordFormatUtil.REGEX_MONTH_YEAR_OR_ZERO)) {
					issueListB1.add("B1: THE MONTH YEAR " + RecordFormatUtil.PATTERN_ERROR);
				}
				
				if(!issueListB1.isEmpty()) {
					valuesByDefault(b1MonthlyFinancialInfoRecord,issueListB1);
				}
			}else {
				valuesByDefault(b1MonthlyFinancialInfoRecord,issueListB1);
			}
		});
		return issueListB1;
	}
	
	private void valuesByDefault(B1MonthlyFinancialInfoRecord b1MonthlyFinancialInfoRecord, List<String> issueListB1) {
		b1MonthlyFinancialInfoRecord.setMonthSellExpenses("0");
		b1MonthlyFinancialInfoRecord.setMonthProfit("0");
		b1MonthlyFinancialInfoRecord.setMonthLaborSum("0");
		b1MonthlyFinancialInfoRecord.setMonthYear("0");
		//issueListB1.add("THE VALUES IN B1 WAS REPLACE DEFAULT 0");
	}
	
	@Override
	public <S extends BaseBeanIoRecord> List<String> validateWithDependencies(B1MonthlyFinancialInfoRecord b1MonthlyFinancialInfoRecord,
			S beanIoRelatedRecords) throws DataCollectBusinessLogicException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends BaseBeanIoRecord> List<String> validateWithDependencies(B1MonthlyFinancialInfoRecord b1MonthlyFinancialInfoRecord,
			List<S> beanIoRelatedRecords) throws DataCollectBusinessLogicException {
		// TODO Auto-generated method stub
		return null;
	}

}
