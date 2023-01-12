package com.honda.hdm.datacollect.service.csv;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.honda.hdm.datacollect.model.entity.*;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.honda.hdm.datacollect.model.dto.csv.B1MonthlyFinancialInfoRecord;
import com.honda.hdm.datacollect.model.dto.csv.C2ClientInformationRecord;
import com.honda.hdm.datacollect.model.dto.csv.D3VinInformationRecord;
import com.honda.hdm.datacollect.model.dto.csv.DataCollectInterfaceWrapper;
import com.honda.hdm.datacollect.model.dto.csv.E4LaborInformationRecord;
import com.honda.hdm.datacollect.model.dto.csv.F5PartInformationRecord;
import com.honda.hdm.datacollect.model.dto.csv.G6ServiceOrderTotalRecord;
import com.honda.hdm.datacollect.model.dto.csv.H7InvoiceInformationRecord;
import com.honda.hdm.datacollect.model.dto.csv.ServiceOrderRecordGroup;
import com.honda.hdm.datacollect.model.dto.dbconst.DcDefaultCatalogueValues;
import com.honda.hdm.datacollect.model.entity.comm.EmailInstance;
import com.honda.hdm.datacollect.model.exception.DataCollectProcessorDBException;
import com.honda.hdm.datacollect.service.config.CustomCfgProperties;
import com.honda.hdm.datacollect.service.domain.IDcDealerService;
import com.honda.hdm.datacollect.service.domain.IDcTerchiefService;
import com.honda.hdm.datacollect.service.domain.impl.DcClientService;
import com.honda.hdm.datacollect.service.domain.impl.DcInFileLogErrorService;
import com.honda.hdm.datacollect.service.domain.impl.DcIncomeFileService;
import com.honda.hdm.datacollect.service.domain.impl.DcInvoiceStatusService;
import com.honda.hdm.datacollect.service.domain.impl.DcModelService;
import com.honda.hdm.datacollect.service.domain.impl.DcMonthFinInfoService;
import com.honda.hdm.datacollect.service.domain.impl.DcOperationCodeService;
import com.honda.hdm.datacollect.service.domain.impl.DcOrderProcessService;
import com.honda.hdm.datacollect.service.domain.impl.DcOrderStatusService;
import com.honda.hdm.datacollect.service.domain.impl.DcOrderTypeService;
import com.honda.hdm.datacollect.service.domain.impl.DcSvcInvoiceService;
import com.honda.hdm.datacollect.service.domain.impl.DcSvcLaborService;
import com.honda.hdm.datacollect.service.domain.impl.DcSvcOrderVinService;
import com.honda.hdm.datacollect.service.domain.impl.DcSvcPartService;
import com.honda.hdm.datacollect.service.domain.impl.DcSvcTotalService;
import com.honda.hdm.datacollect.service.domain.impl.DcVinMasterService;
import com.honda.hdm.datacollect.service.util.MailSenderService;


/**
 *
 * @author Cesar Martinez <cesar_x_martinez@hdm.honda.com>
 * @since Aug 28, 2018
 *
 * Ref:
 * https://mylearnbyexample.com/2017/09/16/2-camel-data-transformation-with-beanio/
 *
 */
@Component
public class DcFileInProcessor implements Processor {

    private static final Logger LOGGER = LogManager.getLogger(DcFileInProcessor.class);
    
    @Lazy
    @Autowired
    private IDcDealerService dcDealerService;
    
    @Lazy
    @Autowired
    private IDcTerchiefService dcTerchiefService;
        
    @Lazy
    @Autowired
    private DcMonthFinInfoService dcMonthFinInfoService;
    
    @Lazy
    @Autowired
    private DcClientService dcClientService;
    
    @Lazy
    @Autowired
    private DcOrderTypeService dcOrderTypeService;
    
    @Lazy
    @Autowired
    private DcOrderStatusService dcOrderStatusService;
    
    @Lazy
    @Autowired
    private DcModelService dcModelService;
    
    @Lazy
    @Autowired
    private DcSvcOrderVinService dcSvcOrderVinService;
    
    @Lazy
    @Autowired
    private DcIncomeFileService dcIncomeFileService;
    
    @Lazy
    @Autowired
    private DcSvcLaborService dcSvcLaborService;
    
    @Lazy
    @Autowired
    private DcOperationCodeService dcOperationCodeService;
    
    @Lazy
    @Autowired
    private DcSvcPartService dcSvcPartService;
    
    @Lazy
    @Autowired
    private DcSvcTotalService dcSvcTotalService;
    
    @Lazy
    @Autowired
    private DcSvcInvoiceService dcSvcInvoiceService;
    
    @Lazy
    @Autowired
    private DcInvoiceStatusService dcInvoiceStatusService;
    
    @Lazy
    @Autowired
    private B1BusinessLogicValidatorImpl b1Validator;
    
    @Lazy
    @Autowired
    private C2BusinessLogicValidatorImpl c2Validator;
    
    @Lazy
    @Autowired
    private D3BusinessLogicValidatorImpl d3Validator;
    
    @Lazy
    @Autowired
    private E4BusinessLogicValidatorImpl e4Validator;
    
    @Lazy
    @Autowired
    private F5BusinessLogicValidatorImpl f5Validator;
    
    @Lazy
    @Autowired
    private G6BusinessLogicValidatorImpl g6Validator;
    
    @Lazy
    @Autowired
    private H7BusinessLogicValidatorImpl h7Validator;
    
    @Lazy
    @Autowired
    private MailSenderService emailService; 
   
    @Lazy
    @Autowired
    private DcErrorCollector errorCollector;
    
    @Lazy
    @Autowired
    private DcInFileLogErrorService dcInFileLogErrorService;
        
    @Lazy
    @Autowired
    private DcVinMasterService dcVinMasterService;
    
    @Lazy
    @Autowired
    private DcOrderProcessService orderProcessService;
    
    @Lazy
    @Autowired
    private CustomCfgProperties custom;
    
    private SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
            
    /**
     * End-point for processing the file once has been converted.
     * 
     * Reference:
     * https://access.redhat.com/documentation/en-us/red_hat_jboss_fuse/6.2/html/apache_camel_component_reference/idu-file2
     *
     * @param exchange
     * @throws java.lang.Exception
     */
    @Override
    public void process(Exchange exchange) throws Exception {
        try{
            Message in = exchange.getIn();
            ArrayList<DataCollectInterfaceWrapper> objs = in.getBody(ArrayList.class);
            String fileName = in.getHeader("CamelFileNameOnly", String.class);
            
            long fileLength = in.getHeader("CamelFileLength", Long.class);
            String dealerNumber = fileName.substring(0, 5);
            in.setHeader("dealerNumber", dealerNumber);
            
            List<String> emails;
            errorCollector.setFileName(fileName);
            DcDealer dcDealer = dcDealerService.findOneByDealerNumber(dealerNumber);
            if(dcDealer != null) {
            	//If dealer exist
            	if (CollectionUtils.isEmpty(objs)) {
                    String msg = "THE FILE IS MALFORMED, PLEASE CHECK ALL THE REQUIRED FIELDS";
                    LOGGER.warn(msg);
                    errorCollector.addError(msg);
                }else {
                	 e4Validator.setDealer(dealerNumber);
                	 d3Validator.setDealer(dealerNumber);
                	 DataCollectInterfaceWrapper dcInterface = objs.get(0);
                     LOGGER.info("TRANSFORMED DATA: " + objs.toString());
                    
                     this.processFileContent(dcInterface);                   
                     DcIncomeFile incomeFile= storeDataCollectInterface(dcInterface,fileName, fileLength, errorCollector.getErrors(), dcDealer);
                     this.saveProcessOrders(dcInterface, incomeFile);
                     this.addCorrectlyOrders(dcInterface);
                }
            	emails = getEmailsDealer(dealerNumber);
            }else {
            	emails = new ArrayList<>();
            	emails.add(custom.getMail().getTo());
            	errorCollector.addError("DEALER " + dealerNumber + " DOES NOT EXIST IN DATABASE");
            	errorCollector.addError("FILE NOT PROCESSED");
            }
            sendEmail(emails);
        }finally{
            LOGGER.info("File processed");
            errorCollector.clearMessages();
        }
    }
    
    private void addCorrectlyOrders(DataCollectInterfaceWrapper dcInterface) {
    	StringBuilder validOrders = new StringBuilder()
    			.append("----------------------------------------------------------------")
    			.append("\nCORRECTLY PROCESSED ORDERS => \n");
        boolean existOrders = false;
        AtomicInteger totalOrders = new AtomicInteger(0);
        for(ServiceOrderRecordGroup service: dcInterface.getServiceOrderRecordGroupList()) {
        	if(service.getValidadoOK()) {
        		validOrders.append(service.getD3VinInformationRecord().getServiceOrder());
        		validOrders.append(",");
        		totalOrders.getAndIncrement();
        		existOrders = true;
        	}
        	if(totalOrders.get() == 8) {
        		validOrders.append("\n");
        		totalOrders.getAndSet(0);
        	}
        }
        if(existOrders) {
        	validOrders.deleteCharAt(validOrders.length() - 1);
        	errorCollector.addError(validOrders.toString());
        }
    }
    
    private void saveProcessOrders(DataCollectInterfaceWrapper dcInterface, DcIncomeFile dcIncomeFile) {
    	for(ServiceOrderRecordGroup service: dcInterface.getServiceOrderRecordGroupList()) {
    		String orderNumber = service.getD3VinInformationRecord().getServiceOrder();
    		String vin = service.getD3VinInformationRecord().getVin();
    		
    		DcOrderProcess orderProcess = orderProcessService.findOneByOrderNumberAndVin(orderNumber,vin);
        	if(!service.getValidadoOK() && orderProcess == null) {
        		DcOrderProcess newOrderProcess= new DcOrderProcess();
        		newOrderProcess.setVin(vin);
        		newOrderProcess.setOrderNumber(orderNumber);
        		newOrderProcess.setIncomeFileError(dcIncomeFile);
        		newOrderProcess.setStatus("E");
        		orderProcessService.save(newOrderProcess);
        	}else if(service.getValidadoOK() && orderProcess != null && orderProcess.getStatus().equals("E")){
        		orderProcess.setStatus("P");
        		orderProcess.setIncomeFileCorrect(dcIncomeFile);
        		orderProcessService.save(orderProcess);
        	}else if(!service.getValidadoOK() && orderProcess != null) {
        		orderProcess.setStatus("E");
        		orderProcess.setIncomeFileError(dcIncomeFile);
        		orderProcessService.save(orderProcess);
        	}
        }
    }
    
    private void processFileContent(DataCollectInterfaceWrapper dcInterface) throws Exception {
    	List<String> b1Validate = b1Validator.validate(dcInterface.getB1());
    	errorCollector.addErrors(b1Validate);
        if (!CollectionUtils.isEmpty(dcInterface.getServiceOrderRecordGroupList())){            
            for(ServiceOrderRecordGroup serviceOrderRecordGroup: dcInterface.getServiceOrderRecordGroupList()){
                    ArrayList<String> orderIssueList = new ArrayList<>();
                    String orderNumber = serviceOrderRecordGroup.getD3VinInformationRecord().getServiceOrder();
                    errorCollector.addError("---------------------ORDER => " + orderNumber +" ------------------");
                    orderIssueList.addAll(c2Validator.validate(serviceOrderRecordGroup.getC2ClientInformationRecord()));
                    orderIssueList.addAll(d3Validator.validate(serviceOrderRecordGroup.getD3VinInformationRecord()));
                    orderIssueList.addAll(e4Validator.validateWithDependencies(serviceOrderRecordGroup.getE4LaborInfoList(), serviceOrderRecordGroup.getD3VinInformationRecord()));
                    orderIssueList.addAll(f5Validator.validateWithDependencies(serviceOrderRecordGroup.getF5PartInformationList(), serviceOrderRecordGroup.getD3VinInformationRecord()));
                    orderIssueList.addAll(g6Validator.validateWithDependencies(serviceOrderRecordGroup.getG6ServiceOrderTotalRecord(), serviceOrderRecordGroup.getD3VinInformationRecord()));                      
                    orderIssueList.addAll(h7Validator.validateWithDependencies(serviceOrderRecordGroup.getH7InvoiceInfoList(), serviceOrderRecordGroup.getD3VinInformationRecord()));
                    errorCollector.addErrors(c2Validator.replaceValuesByDefault(serviceOrderRecordGroup.getC2ClientInformationRecord()));
                    errorCollector.addErrors(d3Validator.replaceValuesByDefault(serviceOrderRecordGroup.getD3VinInformationRecord()));
                    errorCollector.addErrors(e4Validator.replaceValuesByDefault(serviceOrderRecordGroup.getE4LaborInfoList()));
                    errorCollector.addErrors(h7Validator.replaceValuesByDefault(serviceOrderRecordGroup.getH7InvoiceInfoList()));
               if(!orderIssueList.isEmpty()){
                   serviceOrderRecordGroup.setValidadoOK(Boolean.FALSE);//Identificador de que no debe guardarse en la BD esta orden de servicio por tener errores
               }else {
            	   orderIssueList = new ArrayList<>();
               }
               errorCollector.addErrors(orderIssueList);
            }
        }
    }

//    @Transactional
    private DcIncomeFile storeDataCollectInterface(DataCollectInterfaceWrapper dcInterface, String fileName, long fileLength, List<String> errorList, DcDealer dcDealer) throws DataCollectProcessorDBException, Exception{
        DcIncomeFile dcIncomeFile = createFileRecord(dcDealer, fileName, fileLength);  
        for (String error : errorList){
            
            DcInFileLogError fileError = new DcInFileLogError();
            fileError.setDcIncomeFileId(dcIncomeFile);
            fileError.setDescription(error);
            dcInFileLogErrorService.save(fileError);
        }
        createOrUpdateDcMonthlyFinancialInfo(dcDealer, dcInterface.getB1());
        createOrUpdateDcSvcOrderVins(dcDealer, dcIncomeFile, dcInterface.getServiceOrderRecordGroupList());  
        return dcIncomeFile;
    }
    
    private DcIncomeFile createFileRecord(DcDealer dcDealer, String fileName, Long fileLength){
        DcIncomeFile dcIncomeFile = new DcIncomeFile();
        dcIncomeFile.setFileName(fileName);
        dcIncomeFile.setLength(BigInteger.valueOf(fileLength));
        dcIncomeFile.setDcDealerId(dcDealer);
        
        return dcIncomeFileService.save(dcIncomeFile);
    }

    private DcMonthFinInfo createOrUpdateDcMonthlyFinancialInfo(DcDealer dcDealer, B1MonthlyFinancialInfoRecord b1MonthFinRecord) throws Exception {
        DcMonthFinInfo dcMonthFinInfo = null;
        
        if (b1MonthFinRecord == null || Integer.parseInt(b1MonthFinRecord.getMonthYear()) == 0) {
            return dcMonthFinInfo;
        }
        BigInteger month = BigInteger.valueOf(Integer.parseInt(b1MonthFinRecord.getMonthYear().substring(0, 2)));
        BigInteger year = BigInteger.valueOf(Integer.parseInt(b1MonthFinRecord.getMonthYear().substring(2)));

        dcMonthFinInfo = dcMonthFinInfoService.findOneByDcDealerIdAndMonthNumberAndYearNumber(dcDealer, month, year);

        if (dcMonthFinInfo == null) {
            // new record
            dcMonthFinInfo = new DcMonthFinInfo();
            dcMonthFinInfo.setDcDealerId(dcDealer);
            dcMonthFinInfo.setMonthNumber(month);
            dcMonthFinInfo.setYearNumber(year);
        }

        // fill for new or update
        dcMonthFinInfo.setMonthTotalExpenses(Double.parseDouble(b1MonthFinRecord.getMonthTotalExpenses()));
        dcMonthFinInfo.setMonthSellExpenses(Double.parseDouble(b1MonthFinRecord.getMonthSellExpenses()));
        dcMonthFinInfo.setMonthProfit(Double.parseDouble(b1MonthFinRecord.getMonthProfit()));
        dcMonthFinInfo.setMonthLaborSum(Double.parseDouble(b1MonthFinRecord.getMonthLaborSum()));

        // save
        dcMonthFinInfo = dcMonthFinInfoService.save(dcMonthFinInfo);
        
        return dcMonthFinInfo;
    }

    private List<DcSvcOrderVin> createOrUpdateDcSvcOrderVins(DcDealer dcDealer, DcIncomeFile dcIncomeFile, List<ServiceOrderRecordGroup> serviceOrderRecordGroupList) throws DataCollectProcessorDBException, Exception {
        List<DcSvcOrderVin> dcSvcOrderVins = new ArrayList<>();
        
        for (ServiceOrderRecordGroup svcOrdRecordGroup :  serviceOrderRecordGroupList) {
            if(svcOrdRecordGroup.getValidadoOK()){
                DcClient dcClient = createOrUpdateDcClient(svcOrdRecordGroup.getC2ClientInformationRecord());
                DcSvcOrderVin dcSvcOrderVin = createOrUpdateDcSvcOrderVin(dcDealer, dcClient, dcIncomeFile, svcOrdRecordGroup.getD3VinInformationRecord());
                createDcSvcLabor(dcSvcOrderVin, svcOrdRecordGroup.getE4LaborInfoList());
                createDcSvcPart(dcSvcOrderVin, svcOrdRecordGroup.getF5PartInformationList());
                createDcSvcTotal(dcSvcOrderVin, svcOrdRecordGroup.getG6ServiceOrderTotalRecord());
                createOrUpdateDcSvcInvoice(dcSvcOrderVin, svcOrdRecordGroup.getH7InvoiceInfoList());

                dcSvcOrderVins.add(dcSvcOrderVin);
            }
        }
        
        return dcSvcOrderVins;
    }

    private DcClient createOrUpdateDcClient(C2ClientInformationRecord c2ClientInformationRecord) {
        DcClient dcClient = null;
        
        if (c2ClientInformationRecord != null){ 
            // due to there is no primary key on layout for distinguish against 
            // database (i.e. RFC, CURP), 
            // the record cannot be searched first. So that, it is always
            // created as a new record.
            dcClient = new DcClient();
           
            dcClient.setRfc(DcDefaultCatalogueValues.RFC_GENERIC);
            dcClient.setFirstName(c2ClientInformationRecord.getFirstName());
            dcClient.setLastName(c2ClientInformationRecord.getLastName());
            dcClient.setLastNameB(c2ClientInformationRecord.getLastNameB());
            dcClient.setGender(c2ClientInformationRecord.getGender());
            dcClient.setPrivacy(c2ClientInformationRecord.getPrivacy());
            dcClient.setEmail(c2ClientInformationRecord.getEmail());
            dcClient.setPhone(c2ClientInformationRecord.getPhone());
            dcClient.setAddrStreet(c2ClientInformationRecord.getAddrStreet());
            dcClient.setAddrExtNum(c2ClientInformationRecord.getAddrExtNum());
            dcClient.setAddrIntNum(c2ClientInformationRecord.getAddrIntNum());
            dcClient.setAddrNeighborhood(c2ClientInformationRecord.getAddrNeighborhood());
            dcClient.setAddrPostcode(c2ClientInformationRecord.getAddrPostCode());
            dcClient.setDcCityId(DcDefaultCatalogueValues.DC_CITY_ID_DEFAULT.toBigInteger());
            dcClient.setDcStateId(c2ClientInformationRecord.getIdState());
            
            dcClient = dcClientService.save(dcClient);
        }
        
        return dcClient;
    }

    private DcSvcOrderVin createOrUpdateDcSvcOrderVin(DcDealer dcDealer, DcClient dcClient, DcIncomeFile dcIncomeFile, D3VinInformationRecord d3VinInformationRecord) throws DataCollectProcessorDBException, Exception {
        DcSvcOrderVin dcSvcOrderVin = null;
        
        if (d3VinInformationRecord != null) {
            dcSvcOrderVin = new  DcSvcOrderVin();
            
            DcOrderType dcOrderType;
            try{
                dcOrderType = dcOrderTypeService.findOneByCode(StringUtils.trimAllWhitespace(d3VinInformationRecord.getOrderType()));
            }catch(Exception ex){
                throw new DataCollectProcessorDBException(ex.getMessage());
            }
            if (dcOrderType==null)
                throw new DataCollectProcessorDBException("Order Type on D3 record does not correspond to any existent on database");
            
            DcOrderStatus dcOrderStatus = dcOrderStatusService.getOne(BigDecimal.valueOf(d3VinInformationRecord.getOrderStatus()));
            if (dcOrderStatus == null)
                throw new DataCollectProcessorDBException("Order Status on D3 record does not correspond to any existent on database");
            
            
            dcSvcOrderVin.setSvcOrderNum(d3VinInformationRecord.getServiceOrder());
            dcSvcOrderVin.setDcDealerId(dcDealer);
            dcSvcOrderVin.setDcOrderTypeId(dcOrderType);
            dcSvcOrderVin.setDcModelId(defaultModelValue(d3VinInformationRecord.getModel(),d3VinInformationRecord.getYear(), d3VinInformationRecord.getVin()));
            dcSvcOrderVin.setDcClientId(dcClient);
            dcSvcOrderVin.setMileage(d3VinInformationRecord.getMileage());
            dcSvcOrderVin.setVin(d3VinInformationRecord.getVin());
            dcSvcOrderVin.setLicensePlate(d3VinInformationRecord.getCarPlate());
            dcSvcOrderVin.setEin(d3VinInformationRecord.getEngine());
            dcSvcOrderVin.setDcOrderStatusId(dcOrderStatus);
            dcSvcOrderVin.setDptsAssessor(d3VinInformationRecord.getDptsAdvisor());
            dcSvcOrderVin.setDateOpen(formatter.parse(d3VinInformationRecord.getOpeningDate()));
            if(!d3VinInformationRecord.getClosedDate().equals("")) {
            	dcSvcOrderVin.setDateClose(formatter.parse(d3VinInformationRecord.getClosedDate()));
            }
            dcSvcOrderVin.setDcIncomeFileId(dcIncomeFile);
            
            dcSvcOrderVin = dcSvcOrderVinService.save(dcSvcOrderVin);
        }
        
        return dcSvcOrderVin;
    }
    
    /**
     * The model is assignan by default NA when no exist in DC_MODEL table
     * @param dcModelCode
     * @param dcYear
     * @param vinId
     * @return DcModel
     */
    private DcModel defaultModelValue(String dcModelCode,String dcYear, String vinId){
        DcModel dcModel = null;
        try{
            dcModel = dcModelService.findOneByCodeAndYear(dcModelCode, dcYear);
            if (dcModel == null){
            	// IF NOT EXIST IN DC_MODEL, SEARCH THE VIN
            	DcVinMaster dcVin = dcVinMasterService.findByVin(vinId);
            	if(dcVin != null) {
            		dcModel = dcModelService.findOneByCodeAndYear(dcVin.getModelId().trim(), dcVin.getModelYear());
            	}
            	if(dcModel == null) {
            		//IF THE MODEL DOESNT EXIST IN THE VIN MASTER PUT THE MODEL WIHT THE DEFAULT VALUE (NA)
            		dcModel = dcModelService.findOneByCode("NA");
            	}
            }
        }catch(Exception ex){
            LOGGER.warn("Error while searching DcModel by code.", ex);
        }
        return dcModel;
    }
    
    private List<DcSvcLabor> createDcSvcLabor(DcSvcOrderVin dcSvcOrderVin, List<E4LaborInformationRecord> e4LaborInfoList) throws Exception {
        List<DcSvcLabor> dcSvcLabors = new ArrayList<>();
        
        if (e4LaborInfoList != null && !e4LaborInfoList.isEmpty()){
            for (E4LaborInformationRecord e4 : e4LaborInfoList){
                DcSvcLabor dcSvcLabor = new DcSvcLabor();
                dcSvcLabor.setDescription(e4.getDescription());
                dcSvcLabor.setHours(BigDecimal.valueOf(e4.getHours()));
                dcSvcLabor.setUnitPrice(e4.getUnitPrice());
                dcSvcLabor.setSubtotal(e4.getSubtotal());
                dcSvcLabor.setDiscountPercent(e4.getDiscountPercent());
                dcSvcLabor.setDiscountAmount(BigDecimal.ZERO);
                dcSvcLabor.setNetTotal(e4.getNetTotal());
                dcSvcLabor.setLaborCost(e4.getLaborCost());
                //IF DPTS DOEST EXIST ADD DEFAULT
                dcSvcLabor.setDptsTechnician(e4.getDptsTechnician());
                dcSvcLabor.setDcOperationCodeId(dcOperationCodeService.findOneByCode(e4.getOperationCode()));
                dcSvcLabor.setDcSvcOrderVinId(dcSvcOrderVin);

                dcSvcLabors.add(dcSvcLabor);
            }

            //save all
            if (!dcSvcLabors.isEmpty()) {
                dcSvcLaborService.save(dcSvcLabors);
            }
        }
        
        return dcSvcLabors;
    }

    private List<DcSvcPart> createDcSvcPart(DcSvcOrderVin dcSvcOrderVin, List<F5PartInformationRecord> h7InvoiceInformationRecord) {
        List<DcSvcPart> dcSvcParts = new ArrayList<>();
        
        if (h7InvoiceInformationRecord!=null && h7InvoiceInformationRecord.size()>0){
            
            for (F5PartInformationRecord f5 : h7InvoiceInformationRecord){
                DcSvcPart dcSvcPart = new DcSvcPart();

                dcSvcPart.setPartNumber(f5.getPartNumber());
                dcSvcPart.setDescription(f5.getDescription());
                dcSvcPart.setQuantity(f5.getQuantity().toBigInteger());
                dcSvcPart.setListPrice(f5.getListPrice());
                dcSvcPart.setSubtotal(f5.getSubtotal());
                dcSvcPart.setDiscountPercent(f5.getDiscountPercent());
                dcSvcPart.setDiscountAmount(f5.getDiscountAmount());
                dcSvcPart.setNetTotal(f5.getNetTotal());
                dcSvcPart.setDcSvcOrderVinId(dcSvcOrderVin);

                dcSvcParts.add(dcSvcPart);
            }

            //save all
            if (dcSvcParts.size()>0)
                dcSvcPartService.save(dcSvcParts);
        }
        
        return dcSvcParts;
    }

    private DcSvcTotal createDcSvcTotal(DcSvcOrderVin dcSvcOrderVin, G6ServiceOrderTotalRecord g6ServiceOrderTotalRecord) {
        if(g6ServiceOrderTotalRecord == null){
            return null;            
        }
        DcSvcTotal dcSvcTotal = new DcSvcTotal();
        dcSvcTotal.setLaborTotal(g6ServiceOrderTotalRecord.getLaborTotal());
        dcSvcTotal.setPartTotal(g6ServiceOrderTotalRecord.getPartTotal());
        dcSvcTotal.setDiscountTotal(g6ServiceOrderTotalRecord.getDiscountTotal());
        dcSvcTotal.setSubtotal(g6ServiceOrderTotalRecord.getSubtotal());
        dcSvcTotal.setTaxTotal(g6ServiceOrderTotalRecord.getTaxTotal());
        dcSvcTotal.setNetTotal(BigDecimal.ZERO);//g6ServiceOrderTotalRecord.getNetTotal());
        dcSvcTotal.setDcSvcOrderVinId(dcSvcOrderVin);
        
        dcSvcTotal = dcSvcTotalService.save(dcSvcTotal);
        
        return dcSvcTotal;
    }

    private List<DcSvcInvoice> createOrUpdateDcSvcInvoice(DcSvcOrderVin dcSvcOrderVin, List<H7InvoiceInformationRecord> h7InvoiceInfoList) throws DataCollectProcessorDBException, ParseException {
        List<DcSvcInvoice> dcSvcInvoices = new ArrayList<>();
        
        if (h7InvoiceInfoList != null && !h7InvoiceInfoList.isEmpty()){
            
            for (H7InvoiceInformationRecord h7 : h7InvoiceInfoList){
                DcSvcInvoice dcSvcInvoice = new DcSvcInvoice();
                
                DcInvoiceStatus dcInvoiceStatus = null;
                try {
                    dcInvoiceStatus = dcInvoiceStatusService.findOneByName(h7.getStatus());
                }catch(Exception ex){
                    throw new DataCollectProcessorDBException(ex.getMessage());
                }
                if (dcInvoiceStatus == null)
                    throw new DataCollectProcessorDBException("The Invoice Status value " + h7.getStatus() +  " used at H7 record does not correspond to any existent on database");

                dcSvcInvoice.setFolio(h7.getFolio());
                dcSvcInvoice.setDate(formatter.parse(h7.getDate()));
                dcSvcInvoice.setCustomerName(h7.getCustomerName());
                dcSvcInvoice.setAddrPostcode(h7.getAddrPostCode());
                dcSvcInvoice.setTotal(h7.getTotal());
                dcSvcInvoice.setCustomerRfc(h7.getCustomerRFC());
                dcSvcInvoice.setInvoiceUuid("");
                dcSvcInvoice.setDcInvoiceStatusId(dcInvoiceStatus);
                dcSvcInvoice.setDcSvcOrderVinId(dcSvcOrderVin);

                dcSvcInvoices.add(dcSvcInvoice);
            }

            //save all
            if (dcSvcInvoices.size()>0)
                dcSvcInvoiceService.save(dcSvcInvoices);
        }
        
        return dcSvcInvoices;
    }
    
    private List<String> getEmailsDealer(String dealerNumber) {
    	List<String> mailList = new ArrayList<>();
        try{
        	List<DcContact> dealerContacts = dcDealerService.getEnabledContacts(dealerNumber); 
            dealerContacts.forEach((contact) -> {
                mailList.add(contact.getEmail());
            });
       
        	Long idTerrChief = dcDealerService.findOneByDealerNumber(dealerNumber).getDcTerchief().getId();
            List<DcContact> terchiefContacts = dcTerchiefService.getEnabledContacts(idTerrChief);
            terchiefContacts.forEach((contact) -> {
                mailList.add(contact.getEmail());
            });
        }catch(RuntimeException ex){
            LOGGER.error(ex.getMessage());
        } 
        return mailList;
    }
    
    private void sendEmail(List<String> mailList) {
    	EmailInstance emailInstance = new EmailInstance();                            
        emailInstance.setBody(errorCollector.getCapturedErrorsAsString());
        emailInstance.setTo(mailList);
        emailService.setMailInstance(emailInstance);
        emailService.sendMail();    
        
        LOGGER.info("Email sent with list of errors");
    }
}
