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
 *        Dealer Monthly Financial Information Record. Key: B1 Required: false
 *        Occurrence: 0 to 1
 * 
 */
@Record(name = "B1MonthlyFinancialInfoRecord", minOccurs = 0, maxOccurs = 1)
@Fields({ @Field(literal = "B1", name = "recordType", at = 0, rid = true, required = true) // record ID
})
public class B1MonthlyFinancialInfoRecord extends BaseBeanIoRecord {

	@Field(at = 1, required = false)
	private String monthTotalExpenses;

	@Field(at = 2, required = false)
	private String monthSellExpenses;

	@Field(at = 3, required = false)
	private String monthProfit;

	@Field(at = 4, required = false)
	private String monthLaborSum;

	@Field(at = 5, required = false)
	private String monthYear;

	public String getMonthTotalExpenses() {
		return monthTotalExpenses;
	}

	public void setMonthTotalExpenses(String monthTotalExpenses) {
		this.monthTotalExpenses = monthTotalExpenses;
	}

	public String getMonthSellExpenses() {
		return monthSellExpenses;
	}

	public void setMonthSellExpenses(String monthSellExpenses) {
		this.monthSellExpenses = monthSellExpenses;
	}

	public String getMonthProfit() {
		return monthProfit;
	}

	public void setMonthProfit(String monthProfit) {
		this.monthProfit = monthProfit;
	}

	public String getMonthLaborSum() {
		return monthLaborSum;
	}

	public void setMonthLaborSum(String monthLaborSum) {
		this.monthLaborSum = monthLaborSum;
	}

	public String getMonthYear() {
		return monthYear;
	}

	public void setMonthYear(String monthYear) {
		this.monthYear = monthYear;
	}

	@Override
	public String toString() {
		return "B1MonthlyFinancialInfoRecord{" + "monthTotalExpenses=" + monthTotalExpenses + ", monthSellExpenses="
				+ monthSellExpenses + ", monthProfit=" + monthProfit + ", monthLaborSum=" + monthLaborSum
				+ ", monthYear=" + monthYear + '}';
	}

}
