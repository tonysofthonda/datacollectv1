/*
 * Honda de Mexico 2018.
 * All rights reserved.
 */
package com.honda.hdm.datacollect.model.dto.csv;

import java.util.regex.Pattern;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Cesar Martinez <cesar_x_martinez@hdm.honda.com>
 */
public class RecordFormatUtilTest {
    
    public RecordFormatUtilTest() {
    }
    
    @Test
    public void testRegexDecimalOptional9Int4dec() {
        String regExp = RecordFormatUtil.REGEX_DECIMAL_OPTIONAL_9INT_4DEC;
        
        //should pass
        assertTrue(Pattern.matches(regExp,"222222222.2222"));
        assertTrue(Pattern.matches(regExp, "1.123"));
        assertTrue(Pattern.matches(regExp, "0.1"));
        assertTrue(Pattern.matches(regExp, "100"));
        assertTrue(Pattern.matches(regExp, "111111111.1111"));
        
        //should fail
        assertFalse(Pattern.matches(regExp, "-22222222.2222"));
        assertFalse(Pattern.matches(regExp, "1,5"));
        assertFalse(Pattern.matches(regExp, "11111.222.333"));
        assertFalse(Pattern.matches(regExp, "3222222222.2222"));

    }

    @Test
    public void testRegexDecimalOptional9Int4decReal() {
        String regExp = RecordFormatUtil.REGEX_DECIMAL_OPTIONAL_9INT_4DEC_REAL;
        
        //should pass
        assertTrue(Pattern.matches(regExp,"222222222.2222"));
        assertTrue(Pattern.matches(regExp, "1.123"));
        assertTrue(Pattern.matches(regExp, "0.1"));
        assertTrue(Pattern.matches(regExp, "100"));
        assertTrue(Pattern.matches(regExp, "111111111.1111"));
        assertTrue(Pattern.matches(regExp, "-22222222.2222"));
        
        //should fail
        assertFalse(Pattern.matches(regExp, "1,5"));
        assertFalse(Pattern.matches(regExp, "11111.222.333"));
        assertFalse(Pattern.matches(regExp, "3222222222.2222"));

    }
    
    @Test
    public void testRegexFiveDigits() {
        String regExp = RecordFormatUtil.REGEX_FIVE_DIGITS;
        
        //should pass
        assertTrue(Pattern.matches(regExp,"12345"));
        assertTrue(Pattern.matches(regExp,"01111"));
        //should fail
        assertFalse(Pattern.matches(regExp,"999999999"));
        assertFalse(Pattern.matches(regExp,"123"));
        assertFalse(Pattern.matches(regExp,"ABCD"));
    }
    
    @Test
    public void testRegexMonthYear() {
        String regExp = RecordFormatUtil.REGEX_MONTH_YEAR;
        
        //should pass
        assertTrue(Pattern.matches(regExp,"012018"));
        assertTrue(Pattern.matches(regExp,"122020"));
        assertTrue(Pattern.matches(regExp,"082019"));
        assertTrue(Pattern.matches(regExp,"102017"));
        
        //should fail
        assertFalse(Pattern.matches(regExp,"132018"));
        assertFalse(Pattern.matches(regExp,"ABCD"));
        assertFalse(Pattern.matches(regExp,"123"));
        assertFalse(Pattern.matches(regExp,"002021"));
        assertFalse(Pattern.matches(regExp,"52021"));
        assertFalse(Pattern.matches(regExp,"0"));
    }
    
    @Test
    public void testRegexMonthYearOrZero() {
        String regExp = RecordFormatUtil.REGEX_MONTH_YEAR_OR_ZERO;
        
        //should pass
        assertTrue(Pattern.matches(regExp,"012018"));
        assertTrue(Pattern.matches(regExp,"122020"));
        assertTrue(Pattern.matches(regExp,"082019"));
        assertTrue(Pattern.matches(regExp,"102017"));
        assertTrue(Pattern.matches(regExp,"0"));
        
        //should fail
        assertFalse(Pattern.matches(regExp,"132018"));
        assertFalse(Pattern.matches(regExp,"ABCD"));
        assertFalse(Pattern.matches(regExp,"123"));
        assertFalse(Pattern.matches(regExp,"002021"));
        assertFalse(Pattern.matches(regExp,"52021"));
    }
    
}
