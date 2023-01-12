/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.service.util;
/*
 * Honda de Mexico 2018
 * All rights reserved
 *
 *
 *
 * @author Cesar Martinez <cesar_x_martinez@hdm.honda.com>
 */
public class HtmlUtils {
    
    public final static String HTML_DEFAULT_LINE_SEPARATOR = "<br/>";
    
    public static String transformLineSeparatorToHTML(String src){
        // Replace Windows and UNIX Line Separator
        String dest = src.replaceAll("\\r\\n|\\r|\\n", HTML_DEFAULT_LINE_SEPARATOR);
        return dest;
    }
    
}
