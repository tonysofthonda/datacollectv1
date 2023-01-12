/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.honda.hdm.datacollect.model.dto.csv;

import org.beanio.annotation.Field;
import org.beanio.annotation.Fields;
import org.beanio.annotation.Record;

/**
 *
 * @author Cesar Martinez <cesar_x_martinez@hdm.honda.com>
 * @since Aug 29, 2018
 *
 *        Dealer Information Record. Key: A0 Required: true Occurrence: 1
 * 
 */
@Record(name = "A0DealerInformationRecord", minOccurs = 1, maxOccurs = 1)
@Fields({ @Field(literal = "A0", name = "recordType", at = 0, rid = true, required = true) // record ID
})
public class A0DealerInformationRecord extends BaseBeanIoRecord {

	@Field(at = 1, required = false)
	private String dealerNumber;
	@Field(at = 2, required = false)
	private String dealerName;

	public String getDealerNumber() {
		return dealerNumber;
	}

	public void setDealerNumber(String dealerNumber) {
		this.dealerNumber = dealerNumber;
	}

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	@Override
	public String toString() {
		return "A0DealerInformationRecord{" + "dealerNumber=" + dealerNumber + ", dealerName=" + dealerName + '}';
	}

}
