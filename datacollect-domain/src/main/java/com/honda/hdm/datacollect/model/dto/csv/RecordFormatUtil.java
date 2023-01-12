/*
 * Honda de Mexico 2018.
 * All rights reserved.
 */

package com.honda.hdm.datacollect.model.dto.csv;


/**
 *
 * @author Cesar Martinez <cesar_x_martinez@hdm.honda.com>
 * @since  Aug 29, 2018
 *
 */
public class RecordFormatUtil {

    /**
     * Only positive
     * Integer part: 1-9 digits.
     * Separator: Dot, optional.
     * Decimal part: 1-4 digits, optional.
     */
    public final static String REGEX_DECIMAL_OPTIONAL_9INT_4DEC = "[0-9]{1,9}([.][0-9]{1,4})?";
    
    
    public final static String REGEX_DECIMAL_OPTIONAL_3INT_2DEC = "[0-9]{1,3}([.][0-9]{1,2})?";
    
    /**
     * Can be positive or negative
     * Integer part: 1-9 digits.
     * Separator: Dot, optional.
     * Decimal part: 1-4 digits, optional.
     */
    public final static String REGEX_DECIMAL_OPTIONAL_9INT_4DEC_REAL = "(-)?[0-9]{1,9}([.][0-9]{1,4})?";
    
    public final static String REGEX_FIVE_DIGITS = "\\d{5}";
    
    public final static String REGEX_MONTH_YEAR = "^(0[1-9]|1[012])(19|20)\\d\\d$";
    
    public final static String REGEX_MONTH_YEAR_OR_ZERO = "(^(0[1-9]|1[012])(19|20)\\d\\d$)|(0)";
    
    public final static String REGEX_ONE_TO_TEN_DIGITS = "^\\d{1,10}";
    
    public final static String REGEX_DATE_FORMAT = "(^(0[1-9]|1[0-9]|2[0-9]|3[01])(0[1-9]|1[012])(19|20)\\d\\d$)|(0)";
    
    public final static String REGEX_YEAR = "^(19|20)\\d\\d$";
    
    public final static String REGEX_DPTS_ADVISOR = "^(T)([0-9]{6})$";
    public final static String REGEX_DPTS_ADVISORBP = "^(BP)([0-9]{6})$";
    
    /**
     * Only accepts Letters and numbers
     */
    public final static String REGEX_CHAR_N_NUM = "^[a-zA-Z0-9]*$";
    
    /**
     * Only accepts Letters and numbers
     */
    public final static String REGEX_BASIC_VIN = "^([A-Z0-9]{17})$";
    
    public final static String REGEX_VALID_EMAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    
    public final static String REGEX_TELEPHONS_WITH_EXT = "((^|,)([0-9]{10}|[0-9]{10} (^|ext) ([0-9]{1,6})))+$";
    
    public final static String REGEX_GENDER = "^[H,M,E]$";
    
    public final static String REGEX_PRIVACY = "^[S,N]$";
    
    public final static String REGEX_STATUS_INVOICE = "^[A,C]$";
    
    /*
     * ONLY ACCEPTS 1 DIGINT FROM 1 TO 4
     * */
    
    public final static String REGEX_ORDER_STATUS = "^\\b[1-4]\\b$";
    
    public final static String REGEX_RFC = "^[a-zA-Z0-9&'\"<>]*$";
    
    /*
     * GENERAL STIRNGS FOR ERRORS
     * 
     * */
    
    public final static String LENGHT_ERROR = "LENGHT IS INVALID";
    
    public final static String FORMAT_ERROR = "FORMAT IS INVALID";
    
    public final static String PATTERN_ERROR = "PATTERN IS INVALID";
    
    public final static String REQUIRED_ERROR = "VALUE IS REQUIRED";
}
