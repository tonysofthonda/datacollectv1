/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.service.csv;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.beanio.BeanReaderErrorHandlerSupport;
import org.beanio.BeanReaderException;
import org.beanio.InvalidRecordException;
import org.beanio.MalformedRecordException;
import org.beanio.RecordContext;
import org.beanio.UnexpectedRecordException;
import org.beanio.UnidentifiedRecordException;
import org.springframework.stereotype.Component;

@Component
public class DcErrorCollector extends BeanReaderErrorHandlerSupport {

    private static final String LOG_PREFIX = "Error Captured ::: ";
    private static final Logger LOGGER = LogManager.getLogger(DcErrorCollector.class);
    private final LinkedList<String> errorList = new LinkedList<>();
        
    public void clearMessages(){
        this.errorList.clear();
    }
    
    public void setFileName(String fileName){
        this.errorList.addFirst("File  ## " + fileName + " ## \n");
    }
    
    public void addError(String errorMsg){
        this.errorList.add(errorMsg + "\n");
    }
    
    public void addErrors(List<String> errorList){
        errorList.stream().forEach(errorStr -> {
            addError(errorStr);
        });
    }
    
    public List<String> getErrors(){
        return this.errorList;
    }
    
          
    @Override
    public void invalidRecord(InvalidRecordException ex) throws Exception {
        
        for (String msg : geterrorMessageList(createErrorMessage(ex))){
        String error = LOG_PREFIX + "InvalidRecord: " + msg;
        LOGGER.warn(error);
        addError(error);
        }
        
        
    }

    @Override
    public void malformedRecord(MalformedRecordException ex) throws Exception {
        for (String msg : geterrorMessageList(createErrorMessage(ex))){
            String error = LOG_PREFIX + "Malformed Record: " + msg;
            LOGGER.warn(error);
            addError(error);
        }
    }

    @Override
    public void unexpectedRecord(UnexpectedRecordException ex) throws Exception {
        for (String msg : geterrorMessageList(createErrorMessage(ex))){
            String error = LOG_PREFIX + "Unexpected Record: "  + msg;
            LOGGER.warn(error);
            addError(error);
        }
    }

    @Override
    public void unidentifiedRecord(UnidentifiedRecordException ex) throws Exception {
        for (String msg : geterrorMessageList(createErrorMessage(ex))){
            String error = LOG_PREFIX + "Unidentified Record: "   + msg;
            LOGGER.warn(error);
            addError(error);
        }
    }

    @Override
    public void fatalError(BeanReaderException ex) throws Exception {
        String msg = "Fatal Error: " + createErrorMessage(ex);
        LOGGER.error(msg);
        addError(msg);        
    }

    /**
     * Creates an error message using the exception to get the RecordContext
     * from which a meaningful error message can be constructed.
     *
     * @param ex the exception containing the error information.
     *
     * @return a string describing the error(s).
     */
    protected String createErrorMessage(final BeanReaderException ex) {
        final String message = ex.getMessage();
        final StringBuilder errorMessage = new StringBuilder(message.length() * 5);

        // if a bean object is mapped to a record group,
        // the exception may contain more than one record
        for (int i = 0, j = ex.getRecordCount(); i < j; i++) {
            final RecordContext recordContext = ex.getRecordContext(i);
            final String recordName = recordContext.getRecordName();
            final String text = recordContext.getRecordText();
            final long lineNumber = recordContext.getLineNumber();
//            errorMessage.append(String.format("%s: %s%n", message, text));

            if (recordContext.hasRecordErrors()) {
                for (final String error : recordContext.getRecordErrors()) {
                    errorMessage.append(String.format("Line %d - Record '%s' - %s%n", lineNumber, recordName, error));
                }
            }
            if (recordContext.hasFieldErrors()) {
                for (final String field : recordContext.getFieldErrors().keySet()) {
                    for (final String error : recordContext.getFieldErrors(field)) {
                        errorMessage.append(String.format("Line %d - Record '%s' - Field '%s' - %s%n", lineNumber, recordName , field, error));
                    }
                }
            }
        }

        return errorMessage.toString();
    }
    
    public List<String> geterrorMessageList(String errorMessage){
        List<String> errorMessageList = Arrays.asList(errorMessage.split("\\r?\\n"));
    return errorMessageList;
    }
    
    public String getCapturedErrorsAsString(){
        return String.join("\n", this.errorList);
    }
}
