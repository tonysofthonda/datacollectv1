/*
 * Honda de Mexico 2018.
 * All rights reserved.
 */
package com.honda.hdm.datacollect.service.csv;

import com.honda.hdm.datacollect.model.dto.csv.BaseBeanIoRecord;
import com.honda.hdm.datacollect.model.dto.csv.D3VinInformationRecord;
import com.honda.hdm.datacollect.model.dto.csv.F5PartInformationRecord;
import com.honda.hdm.datacollect.model.dto.csv.RecordFormatUtil;
import com.honda.hdm.datacollect.model.exception.DataCollectBusinessLogicException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Cesar Martinez <cesar_x_martinez@hdm.honda.com>
 */
@Component
public class F5BusinessLogicValidatorImpl implements IDcListNodeValidator<F5PartInformationRecord> {

	@Override
	public List<String> validate(List<F5PartInformationRecord> f5List) throws DataCollectBusinessLogicException {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public <S extends BaseBeanIoRecord> List<String> validateWithDependencies(List<F5PartInformationRecord> f5List,
			S relatedRecord) throws DataCollectBusinessLogicException {

		if (!(relatedRecord instanceof D3VinInformationRecord)) {
			throw new DataCollectBusinessLogicException(
					"A D3VinInformationRecord associated is required for validate the F5PartInformationRecord List");
		}
		D3VinInformationRecord d3VinInformationRecord = (D3VinInformationRecord) relatedRecord;
		List<String> issueListF5 = new ArrayList<>();
		try {
			if (f5List != null) {
				f5List.forEach(f5 -> {
					/* PART NUMBER */
					String partNumber = f5.getPartNumber().trim().replaceAll("[ -/*]", "");
					f5.setPartNumber(partNumber);
					if (partNumber.isEmpty()) {
						issueListF5.add("F5: PART NUMBER " + RecordFormatUtil.REQUIRED_ERROR);
					} else {
						if (partNumber.length() > 20) {
							issueListF5.add("F5: PART NUMBER " + partNumber + " " + RecordFormatUtil.LENGHT_ERROR);
						}
						if (!partNumber.matches(RecordFormatUtil.REGEX_CHAR_N_NUM)) {
							issueListF5.add("F5: PART NUMBER " + partNumber + " " + RecordFormatUtil.PATTERN_ERROR);
						}
					}

					String description = f5.getDescription().trim();
					if (description.isEmpty()) {
						issueListF5.add("F5: DESCRIPTION " + RecordFormatUtil.REQUIRED_ERROR);
					} else if (partNumber.length() > 255) {
						issueListF5.add("F5: DESCRIPTION " + partNumber + RecordFormatUtil.LENGHT_ERROR);
					}

					if (f5.getQuantity() == null) {
						issueListF5.add("F5: QUANTITY " + RecordFormatUtil.REQUIRED_ERROR);
					} else if (!f5.getQuantity().toString().matches(RecordFormatUtil.REGEX_DECIMAL_OPTIONAL_9INT_4DEC)) {
						issueListF5.add("F5: QUANTITY " + f5.getQuantity() + " " + RecordFormatUtil.PATTERN_ERROR);
					}

					BigDecimal listPrice = f5.getListPrice();
					if (listPrice == null) {
						issueListF5.add("F5: LIST PRICE " + RecordFormatUtil.REQUIRED_ERROR);
					} else if (!listPrice.toString().matches(RecordFormatUtil.REGEX_DECIMAL_OPTIONAL_9INT_4DEC)) {
						issueListF5.add("F5: LIST PRICE " + listPrice + " " + RecordFormatUtil.PATTERN_ERROR);
					}

					if (f5.getNetTotal() == null) {
						issueListF5.add("F5: NET TOTAL " + RecordFormatUtil.REQUIRED_ERROR);
					} else if (!f5.getNetTotal().toString()
							.matches(RecordFormatUtil.REGEX_DECIMAL_OPTIONAL_9INT_4DEC)) {
						issueListF5.add("F5: NET TOTAL " + f5.getNetTotal() + " " + RecordFormatUtil.PATTERN_ERROR);
					}

					BigDecimal subTotal = f5.getSubtotal();
					if (subTotal == null) {
						issueListF5.add("F5: SUBTOTAL " + RecordFormatUtil.REQUIRED_ERROR);
					} else if (!subTotal.toString().matches(RecordFormatUtil.REGEX_DECIMAL_OPTIONAL_9INT_4DEC)) {
						issueListF5.add("F5: SUBTOTAL " + subTotal + " " + RecordFormatUtil.PATTERN_ERROR);
					}

					BigDecimal discountAmount = f5.getDiscountAmount();
					if (d3VinInformationRecord.getOrderStatus() == 4) {
						if (discountAmount == null || discountAmount.compareTo(BigDecimal.ZERO) < 0) {
							issueListF5.add("F5: ORDER STATUS IS 4 AND DISCOUNT AMOUNT IS REQUIRED FOR PART NUMBER: " + f5.getPartNumber());
						}

						if (f5.getDiscountPercent() == null) {
							issueListF5.add("F5: ORDER STATUS IS 4 AND PERCENT AMOUNT IS REQUIRED FOR PART NUMBER: " + f5.getPartNumber());
						}
					}

					if (discountAmount != null && !discountAmount.toString().matches(RecordFormatUtil.REGEX_DECIMAL_OPTIONAL_9INT_4DEC)) {
						issueListF5.add("F5: THE DISCOUNT AMMOUNT " + discountAmount + " " + RecordFormatUtil.PATTERN_ERROR);
					}
				});
				return issueListF5;
			}
		} catch (Exception ex) {
			throw new DataCollectBusinessLogicException(ex.toString());
		}
		return issueListF5;
	}

	@Override
	public <S extends BaseBeanIoRecord> List<String> validateWithDependencies(List<F5PartInformationRecord> f5List,
			List<S> beanIoRelatedRecords) throws DataCollectBusinessLogicException {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
