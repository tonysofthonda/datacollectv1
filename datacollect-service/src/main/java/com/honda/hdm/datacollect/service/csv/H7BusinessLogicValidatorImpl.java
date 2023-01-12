/*
 * Honda de Mexico 2018.
 * All rights reserved.
 */

package com.honda.hdm.datacollect.service.csv;

import com.honda.hdm.datacollect.model.dto.csv.BaseBeanIoRecord;
import com.honda.hdm.datacollect.model.dto.csv.D3VinInformationRecord;
import com.honda.hdm.datacollect.model.dto.csv.H7InvoiceInformationRecord;
import com.honda.hdm.datacollect.model.dto.csv.RecordFormatUtil;
import com.honda.hdm.datacollect.model.exception.DataCollectBusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Cesar Martinez <cesar_x_martinez@hdm.honda.com>
 * @since Oct 4, 2018
 *
 */
@Component
public class H7BusinessLogicValidatorImpl implements IDcNodeValidator<H7InvoiceInformationRecord> {

	public List<String> validateRecords(List<H7InvoiceInformationRecord> h7InvoiceInformationRecords)
			throws DataCollectBusinessLogicException {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

	@Override
	public List<String> validate(H7InvoiceInformationRecord beanIoRecord) throws DataCollectBusinessLogicException {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

	public <S extends BaseBeanIoRecord> List<String> validateWithDependencies(List<H7InvoiceInformationRecord> h7InvoiceList,
			D3VinInformationRecord d3VinRecord) throws DataCollectBusinessLogicException {
		if (!(d3VinRecord instanceof D3VinInformationRecord)) {
			throw new DataCollectBusinessLogicException(
					"A D3VinInformationRecord associated is required for validate the H7InvoiceInformationRecord");
		}
		List<String> issueListH7 = new ArrayList<>();
		byte orderStatus = d3VinRecord.getOrderStatus();
		if (orderStatus == 4 && h7InvoiceList == null) {
			issueListH7.add("H7: D3 ORDER STATUS IS " + orderStatus + " AND NODE H7 IS REQUIRED");
		}
		if(h7InvoiceList != null) {
			for (H7InvoiceInformationRecord h7Object : h7InvoiceList) {
				String folio = h7Object.getFolio();
				if (folio == null) {
					issueListH7.add("H7: FOLIO " + RecordFormatUtil.REQUIRED_ERROR);
				}else if (folio.length() > 20) {
					issueListH7.add("H7: FOLIO " + folio +" "+ RecordFormatUtil.LENGHT_ERROR);
				}
				
				String orderService = h7Object.getServiceOrder();
				if (orderService == null) {
					issueListH7.add("SERVICE ORDER " + RecordFormatUtil.REQUIRED_ERROR);
				}else if (orderService.length() > 20) {
					issueListH7.add("H7: SERVICE ORDER " + orderService + " " + RecordFormatUtil.LENGHT_ERROR);
				}
				
				if (h7Object.getDate() == null) {
					issueListH7.add("H7: DATE " + RecordFormatUtil.REQUIRED_ERROR);
				}else if (!h7Object.getDate().matches(RecordFormatUtil.REGEX_DATE_FORMAT)) {
					issueListH7.add("H7: DATE  "+ h7Object.getDate() + " " + RecordFormatUtil.PATTERN_ERROR);
				}
				
				String customerName = h7Object.getCustomerName();
				if (customerName == null) {
					issueListH7.add("H7: CUSTOMER NAME " + RecordFormatUtil.REQUIRED_ERROR);
				}else if (customerName.length() > 100) {
					issueListH7.add("H7: CUSTOMER NAME " + customerName + " " + RecordFormatUtil.LENGHT_ERROR);
				}
				
				String addrPostCode = h7Object.getAddrPostCode();
				if (addrPostCode == null) {
					issueListH7.add("H7: ADDRESS POST CODE " + RecordFormatUtil.REQUIRED_ERROR);
				}else if (addrPostCode.length() > 5) {
					issueListH7.add("H7: ADDRESS POST CODE " + addrPostCode + " " + RecordFormatUtil.LENGHT_ERROR);
				}
				
				if (h7Object.getTotal() == null) {
					issueListH7.add("H7: TOTAL " + RecordFormatUtil.REQUIRED_ERROR);
				}else if (!h7Object.getTotal().toString().matches(RecordFormatUtil.REGEX_DECIMAL_OPTIONAL_9INT_4DEC)) {
					issueListH7.add("H7: TOTAL "+ h7Object.getTotal() + " " + RecordFormatUtil.PATTERN_ERROR);
				}
				
				String status = h7Object.getStatus();
				if (status == null) {
					issueListH7.add("H7: STATUS " + RecordFormatUtil.REQUIRED_ERROR);
				}else if (!status.matches(RecordFormatUtil.REGEX_STATUS_INVOICE)) {
					issueListH7.add("H7: STATUS "+ status + " " + RecordFormatUtil.PATTERN_ERROR);
				}
			}	
		}
		
		return issueListH7;
	}
	
	public List<String> replaceValuesByDefault(List<H7InvoiceInformationRecord> h7InvoiceList) {
		List<String> issueListH7 = new ArrayList<>();
		if(h7InvoiceList != null) {
			for (H7InvoiceInformationRecord h7Object : h7InvoiceList) {
				String rfc= h7Object.getCustomerRFC();
				if(rfc != null) {
					if (rfc.length() > 15) {
						issueListH7.add("WARNING H7: RFC " + rfc + " " + RecordFormatUtil.LENGHT_ERROR);
					}
					if (!rfc.matches(RecordFormatUtil.REGEX_RFC)) {
						issueListH7.add("WARNING H7: RFC "+ rfc + " " + RecordFormatUtil.PATTERN_ERROR);
					}
				}
			}
		}
		return issueListH7;
	}

	@Override
	public <S extends BaseBeanIoRecord> List<String> validateWithDependencies(H7InvoiceInformationRecord beanIoRecord,
			List<S> beanIoRelatedRecords) throws DataCollectBusinessLogicException {
		throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
																		// Tools | Templates.
	}

	@Override
	public <S extends BaseBeanIoRecord> List<String> validateWithDependencies(H7InvoiceInformationRecord beanIoRecord,
			S beanIoRelatedRecords) throws DataCollectBusinessLogicException {
		return null;
	}
}
