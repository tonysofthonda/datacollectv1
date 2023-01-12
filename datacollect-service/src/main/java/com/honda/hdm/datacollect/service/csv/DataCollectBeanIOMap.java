/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.service.csv;

import com.honda.hdm.datacollect.model.dto.csv.DataCollectInterfaceWrapper;
import org.beanio.StreamFactory;
import org.beanio.builder.CsvParserBuilder;
import org.beanio.builder.StreamBuilder;
import org.beanio.stream.csv.CsvParserConfiguration;
import org.springframework.stereotype.Component;

/**
 * Defines Mapping via code, instead of using an Apache BeanIO XML Mapping file.
 * @link http://beanio.org/2.1/docs/reference/index.html
 * 
 *
 * 
 * @author Cesar Martinez <cesar_x_martinez@hdm.honda.com>
 * @since Aug 27, 2018
 *
 */
@Component
public class DataCollectBeanIOMap {
    
    private StreamFactory ioDataCollectFactory;
    private static final char ATTR_DEFAULT_DELIMITER = '|';
    private static final String COMMENT_LINE_DEFAULT_CHAR = "#";
    private static final String STREAM_FORMAT_CSV = "csv";
    public static final String STREAM_MAP_NAME = "dataCollectStreamMap";

    public DataCollectBeanIOMap() {
    }
    
    public void initialize(){
        ioDataCollectFactory = StreamFactory.newInstance();

        // define Stream Layout and Type (CSV)
        StreamBuilder builder = new StreamBuilder(STREAM_MAP_NAME)
            .format(STREAM_FORMAT_CSV)
            .parser(createCsvParserBuilder())
            .strict() // required for allowing record groups with minOccurs="1"
            //.resourceBundle(MESSAGES_FILE)
            ;
        
        // define Record Types
        defineBeanIOGroupsAndRecords(builder);
        
        // configure validation Messages on correspondant language
        configureResourceBundle(builder);

        // define stream mapping
        ioDataCollectFactory.define(builder);
    }
    
    /**
     * Creates a CSV Parser Builder
     * @link http://beanio.org/2.0/docs/reference/index.html#CSVStreamFormat
     * @return 
     */
    private CsvParserBuilder createCsvParserBuilder(){
        CsvParserBuilder csvParserBuilder = new CsvParserBuilder()
            .delimiter(ATTR_DEFAULT_DELIMITER)
            .enableComments(COMMENT_LINE_DEFAULT_CHAR);   
        
        return csvParserBuilder;
    }
    
    /**
     * Defines BeanIO Groups and Records
     * @param streamBuilder 
     */
    private void defineBeanIOGroupsAndRecords(StreamBuilder streamBuilder){
        streamBuilder
            .addGroup(DataCollectInterfaceWrapper.class);
    }
    
    /**
     * Look up for the file dataCollectValidationMessages.properties
     * 
     * References:
     *      http://beanio.org/2.0/docs/reference/index.html#StreamValidation
     *      http://beanio.org/2.0/docs/reference/index.html#B
     */
    private void configureResourceBundle(StreamBuilder streamBuilder){
        streamBuilder.resourceBundle("dataCollectValidationMessages");
    }

    public StreamFactory getIoDataCollectFactory() {
        return ioDataCollectFactory;
    }
    
}
