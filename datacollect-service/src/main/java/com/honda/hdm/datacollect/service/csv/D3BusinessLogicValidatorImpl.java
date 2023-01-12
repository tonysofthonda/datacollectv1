/*
 * Honda de Mexico 2018.
 * All rights reserved.
 */
package com.honda.hdm.datacollect.service.csv;

import com.honda.hdm.datacollect.model.dto.csv.BaseBeanIoRecord;
import com.honda.hdm.datacollect.model.dto.csv.D3VinInformationRecord;
import com.honda.hdm.datacollect.model.dto.csv.RecordFormatUtil;
import com.honda.hdm.datacollect.model.entity.DcOrderType;
import com.honda.hdm.datacollect.model.enums.ServiceTypeEnum;
import com.honda.hdm.datacollect.model.exception.DataCollectBusinessLogicException;
import com.honda.hdm.datacollect.service.domain.impl.DcOrderStatusService;
import com.honda.hdm.datacollect.service.domain.impl.DcOrderTypeService;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Cesar Martinez <cesar_x_martinez@hdm.honda.com>
 */
@Component
public class D3BusinessLogicValidatorImpl implements IDcNodeValidator<D3VinInformationRecord> {

    @Autowired
    private DcOrderTypeService dcOrderTypeService;

    @Autowired
    private DcOrderStatusService dcOrderStatusService;

    private Long dealerNumber;

    @Override
    public List<String> validate(D3VinInformationRecord d3VinInformationRecord)
            throws DataCollectBusinessLogicException {
        String orderType = d3VinInformationRecord.getOrderType();
        List<String> issueListD3 = new ArrayList<>();

        /* VALIDATE SERVICE ORDER */
        String serviceOrder = d3VinInformationRecord.getServiceOrder().trim();
        // IS REQUIRED
        if (serviceOrder.equals("")) {
            issueListD3.add("D3: SERVICE ORDER " + RecordFormatUtil.REQUIRED_ERROR);
        } else if (d3VinInformationRecord.getServiceOrder().length() > 20) {
            issueListD3.add("D3: SERVICE ORDER " + d3VinInformationRecord.getServiceOrder() + " " + RecordFormatUtil.LENGHT_ERROR);
        }

        /* VALIDATE MILEAGE */
        // IS REQUIRED IF THE ORDER TYPE IS DIFERENT THAN VM OR VMY
        BigInteger mileage = d3VinInformationRecord.getMileage();
        if (!orderType.equals("VM") && !orderType.equals("VMY") && (mileage == null || mileage.toString().equals(""))) {
            issueListD3.add("D3: MILEAGE " + RecordFormatUtil.REQUIRED_ERROR);
        }
        // HAS LENGTH
        if (mileage != null && mileage.toString().length() > 10) {
            issueListD3.add("D3: MILEAGE " + mileage + " " + RecordFormatUtil.LENGHT_ERROR);
        }

        /* VALIDATE VIN */
        String vin = d3VinInformationRecord.getVin().trim();
        // IS REQUIRED IF THE ORDER TYPE IS DIFERENT THAN VM OR VMY
        if (!orderType.equals("VM") && !orderType.equals("VMY") && vin.equals("")) {
            issueListD3.add("D3: VIN " + RecordFormatUtil.REQUIRED_ERROR);
        }
        // HAS LENGTH
        if (!vin.equals("") && vin.length() != 17) {
            issueListD3.add("D3: VIN " + d3VinInformationRecord.getVin() + " " + RecordFormatUtil.LENGHT_ERROR);
            d3VinInformationRecord.setVin(null);
        }
        // HAS PATTERN
        if (!vin.equals("") && !vin.matches(RecordFormatUtil.REGEX_BASIC_VIN)) {
            issueListD3.add("D3: VIN " + d3VinInformationRecord.getVin() + " " + RecordFormatUtil.PATTERN_ERROR);
        }

        /* VALIDATE YEAR */
        String year = d3VinInformationRecord.getYear().trim();
        if (!orderType.equals("VM") && !orderType.equals("VMY") && year.equals("")) {
            // IS REQUIRED IF THE ORDER TYPE IS DIFERENT THAN VM OR VMY
            issueListD3.add("D3: YEAR " + RecordFormatUtil.REQUIRED_ERROR);
        }
        // HAS LENGTH
        if (!year.equals("") && year.length() != 4) {
            issueListD3.add("D3: YEAR " + d3VinInformationRecord.getYear() + " " + RecordFormatUtil.LENGHT_ERROR);
        }
        // HAS PATTERN
        if (!year.equals("") && !year.matches(RecordFormatUtil.REGEX_YEAR)) {
            issueListD3.add("D3: YEAR " + d3VinInformationRecord.getYear() + " " + RecordFormatUtil.PATTERN_ERROR);
        }

        /* VALIDATE ORDER TYPE */
        if (orderType.equals("")) {
            // IS REQUIRED
            issueListD3.add("D3: ORDER TYPE " + RecordFormatUtil.REQUIRED_ERROR);
        } else {
            // EXIST IN THE DATABASE(CATALOG)
            try {
                if (dcOrderTypeService.findOneByCode(orderType) == null) {
                    issueListD3.add("D3: ORDER TYPE IS NOT FOUND IN THE CATALOG");
                }
            } catch (Exception ex) {
                throw new DataCollectBusinessLogicException(ex.toString());
            }
        }

        /* VALIDATE ORDER STATUS */
        byte orderStatus = d3VinInformationRecord.getOrderStatus();
        // IS REQUIRED
        if (orderStatus == 0) {
            issueListD3.add("D3: ORDER STATUS " + RecordFormatUtil.REQUIRED_ERROR);
        } else {
            // EXIST IN THE DATABASE(CATALOG)
            try {
                if (dcOrderStatusService.findOne(new BigDecimal(d3VinInformationRecord.getOrderStatus())) == null) {
                    issueListD3.add("D3: ORDER STATUS IS NOT IN ORDER STATUS CATALOG");
                }
            } catch (Exception ex) {
                throw new DataCollectBusinessLogicException(ex.toString());
            }
        }

        /* VALIDATE OPENING DATE */
        // IS REQUIRED
        String openDate = d3VinInformationRecord.getOpeningDate();
        if (openDate.equals("")) {
            issueListD3.add("D3: OPENING DATE " + RecordFormatUtil.REQUIRED_ERROR);
        } else {
            // HAS LENGTH
            if (openDate.length() != 8) {
                issueListD3.add("D3: OPENING DATE " + RecordFormatUtil.LENGHT_ERROR);
            }
            // HAS PATTERN
            if (!openDate.matches(RecordFormatUtil.REGEX_DATE_FORMAT)) {
                issueListD3.add("D3: OPENING DATE " + RecordFormatUtil.PATTERN_ERROR);
            }
        }

        /* VALIDATE CLOSING DATE */
        // IS REQUIRED IF THE ORDER TYPE IS DIFERENT 1
        String closeDate = d3VinInformationRecord.getClosedDate();
        if (orderStatus != 0 && orderStatus != 1 && closeDate.equals("")) {
            issueListD3.add("D3: CLOSE DATE " + RecordFormatUtil.REQUIRED_ERROR);
        }
        // HAS LENGTH
        if (!closeDate.equals("") && closeDate.length() != 8) {
            issueListD3.add("D3: CLOSE DATE " + RecordFormatUtil.LENGHT_ERROR);
        }
        // HAS PATTERN
        if (!closeDate.equals("") && !closeDate.matches(RecordFormatUtil.REGEX_DATE_FORMAT)) {
            issueListD3.add("D3: CLOSE DATE " + RecordFormatUtil.PATTERN_ERROR);
        }

        return issueListD3;
    }

    public List<String> replaceValuesByDefault(D3VinInformationRecord d3VinInformationRecord) throws Exception {
        String orderType = d3VinInformationRecord.getOrderType();
        List<String> issueListD3 = new ArrayList<>();

        /* VALIDATE PLATE */
        String plate = d3VinInformationRecord.getCarPlate();
        if (!orderType.equals("VMY") && !orderType.equals("VM") && orderType.equals("I") && plate.equals("")) {
            // IS REQUIRED IF THE ORDER TYPE IS DIFERENT THAN VM, VMY OR I
            d3VinInformationRecord.setCarPlate(null);
        } else if (orderType.equals("VMY") && orderType.equals("VM")) {
            d3VinInformationRecord.setCarPlate(plate);
        }
        if (plate.length() > 15) {
            // IF LENGHT IS HIGHER
            d3VinInformationRecord.setCarPlate(null);
            issueListD3.add("WARNING D3: CAR PLATE " + plate + " " + RecordFormatUtil.LENGHT_ERROR);
        }
        if (!plate.matches(RecordFormatUtil.REGEX_CHAR_N_NUM)) {
            // IF FORMAT IS INVALID
            d3VinInformationRecord.setCarPlate(null);
            issueListD3.add("WARNING D3: CAR PLATE " + plate + " " + RecordFormatUtil.PATTERN_ERROR);
        }

        /* VALIDATE ENGINE */
        // IS REQUIRED IF THE ORDER TYPE IS DIFERENT THAN VM
        String engine = d3VinInformationRecord.getEngine();
        if (!orderType.equals("VM") && engine.equals("")) {
            d3VinInformationRecord.setEngine(null);
            issueListD3.add("WARNING D3: ENGINE " + RecordFormatUtil.REQUIRED_ERROR);
        }
        // HAS LENGHT
        if (engine.length() > 15) {
            d3VinInformationRecord.setEngine(null);
            issueListD3.add("WARNING D3: ENGINE " + engine + " " + RecordFormatUtil.LENGHT_ERROR);
        }
        // IF FORMAT IS INVALID
        if (!engine.matches(RecordFormatUtil.REGEX_CHAR_N_NUM)) {
            d3VinInformationRecord.setEngine(null);
            issueListD3.add("WARNING D3: ENGINE " + engine + " " + RecordFormatUtil.PATTERN_ERROR);
        }


        /* VALIDATE DPTS ADVISOR */
        String dptsAdvisor = d3VinInformationRecord.getDptsAdvisor();
        if (!orderType.equals("")) {
            DcOrderType typeOrder = dcOrderTypeService.findOneByCode(orderType);
            if (typeOrder != null && typeOrder.getDcSvcOrderVinList() != null && !typeOrder.getDcServicesTypes().isEmpty()) {
                if (this.dealerNumber >= 10000 && this.dealerNumber < 30000) {
                    Boolean isService = typeOrder.getDcServicesTypes()
                            .stream()
                            .anyMatch((serviceType) -> serviceType.getName().equals(ServiceTypeEnum.SE.toString()));
                    if (isService) {
                        if (orderType.equals("BP")) {
                            if (!(dptsAdvisor.matches(RecordFormatUtil.REGEX_DPTS_ADVISOR) || dptsAdvisor.matches(RecordFormatUtil.REGEX_DPTS_ADVISORBP))) {
                                d3VinInformationRecord.setDptsAdvisor(dptsAdvisor);
                            }
                        } else if (orderType.equals("VM") || orderType.equals("VMY")) {
                            if (dptsAdvisor.matches(RecordFormatUtil.REGEX_DPTS_ADVISOR)) {
                                d3VinInformationRecord.setDptsAdvisor(null);
                            }
                        } else if (dptsAdvisor.matches(RecordFormatUtil.REGEX_DPTS_ADVISOR)) {
                            d3VinInformationRecord.setDptsAdvisor(dptsAdvisor);
                        } else {
                            d3VinInformationRecord.setDptsAdvisor(null);
                            issueListD3.add("WARNING D3: DPTS ADVISOR " + dptsAdvisor + " " + RecordFormatUtil.FORMAT_ERROR);
                        }
                    } else {
                        issueListD3.add("WARNING D3: THE TYPE OF ORDER IS INVALID " + RecordFormatUtil.FORMAT_ERROR);
                    }
                } else if (this.dealerNumber >= 32000 && this.dealerNumber < 33000) {
                    Boolean isBp = typeOrder.getDcServicesTypes()
                            .stream()
                            .anyMatch((serviceType) -> serviceType.getName().equals(ServiceTypeEnum.BP.toString()));
                    if (isBp) {
                        if (orderType.equals("BP")) {
                            if (dptsAdvisor.matches(RecordFormatUtil.REGEX_DPTS_ADVISORBP)) {
                                d3VinInformationRecord.setDptsAdvisor(dptsAdvisor);
                            } else {
                                d3VinInformationRecord.setDptsAdvisor(null);
                                issueListD3.add("WARNING D3: DPTS ADVISOR " + dptsAdvisor + " " + RecordFormatUtil.FORMAT_ERROR);
                            }
                        } else if (dptsAdvisor.matches(RecordFormatUtil.REGEX_DPTS_ADVISOR)) {
                            d3VinInformationRecord.setDptsAdvisor(dptsAdvisor);
                        } else {
                            d3VinInformationRecord.setDptsAdvisor(null);
                            issueListD3.add("WARNING D3: DPTS ADVISOR " + dptsAdvisor + " " + RecordFormatUtil.FORMAT_ERROR);
                        }
                    } else {
                        issueListD3.add("WARNING D3: THE TYPE OF ORDER IS INVALID " + RecordFormatUtil.FORMAT_ERROR);
                    }
                } else {
                    issueListD3.add("WARNING D3: THE TYPE OF ORDER DOES NOT APPLY TO THE RANGE OF DEALER SEND " + RecordFormatUtil.FORMAT_ERROR);
                }
            }
        } else {
            issueListD3.add("WARNING D3: ORDER TYPE IS EMPTY");
        }
        /* VALIDATE MODEL */
        String model = d3VinInformationRecord.getModel();
        if (!orderType.equals("VM") && !orderType.equals("VMY") && model.equals("")) {
            // IS REQUIRED IF THE ORDER TYPE IS DIFERENT THAN VM OR VMY
            issueListD3.add("WARNING D3: MODEL " + RecordFormatUtil.REQUIRED_ERROR);
        } else if (model.length() > 15) {
            issueListD3.add("WARNING D3: MODEL " + model + " " + RecordFormatUtil.LENGHT_ERROR);
        }
        return issueListD3;
    }

    public void setDealer(String dealerNumber) {
        this.dealerNumber = new Long(dealerNumber);
    }

    @Override
    public <S extends BaseBeanIoRecord> List<String> validateWithDependencies(
            D3VinInformationRecord d3VinInformationRecord, List<S> beanIoRelatedRecords)
            throws DataCollectBusinessLogicException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <S extends BaseBeanIoRecord> List<String> validateWithDependencies(D3VinInformationRecord beanIoRecord,
            S beanIoRelatedRecords) throws DataCollectBusinessLogicException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
