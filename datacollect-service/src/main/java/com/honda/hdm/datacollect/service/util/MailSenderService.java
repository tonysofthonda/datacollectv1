/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.service.util;

import com.honda.hdm.datacollect.model.entity.comm.EmailInstance;
import com.honda.hdm.datacollect.service.config.CustomCfgProperties;
import java.util.Arrays;
import java.util.logging.Level;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author VJC80413
 */
@Service
public class MailSenderService {

    @Autowired
    private JavaMailSender sender;
    
    @Autowired
    CustomCfgProperties custom;
    
    @Value("${spring.profiles.active}")
    private String environment;
    
    EmailInstance mailInstance;
    String DEFAULT_SUBJECT  = "Data Collect Log notification";    
    String DEFAULT_FROM = "data_collect@hdm.honda.com";

    final static Logger LOGGER = LogManager.getLogger(MailSenderService.class);
   
    
    public void sendLogMail() {

        mailInstance.setTo(Arrays.asList((custom.getMail().getTo()).split(";")));        
        mailInstance.setCc(Arrays.asList((custom.getMail().getCc()).split(";")));
        mailInstance.setBcc(Arrays.asList((custom.getMail().getBcc()).split(";")));
    
        if (!isValid()) return;          
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
    
        LOGGER.warn("Log: "+custom.getMail().getTo() );
        
        try 
        {
            helper.setFrom(DEFAULT_FROM);

            // add destinations
            if (!CollectionUtils.isEmpty(mailInstance.getTo()))
                helper.setTo(mailInstance.getTo().toArray(new String[mailInstance.getTo().size()]));
            if (!CollectionUtils.isEmpty(mailInstance.getCc()))
                helper.setCc(mailInstance.getCc().toArray(new String[mailInstance.getCc().size()]));
            if (!CollectionUtils.isEmpty(mailInstance.getBcc()))
                helper.setBcc(mailInstance.getBcc().toArray(new String[mailInstance.getBcc().size()]));

            // add subject and body   
            if (StringUtils.isNotBlank(mailInstance.getSubject())){
                helper.setSubject(mailInstance.getSubject());
            }else
                helper.setSubject(setMailTo());

            if (mailInstance.isAsHtml()){
                transformLineSeparatorToHTML();
                helper.setText(mailInstance.getBody(), true);
            }else{
                helper.setText(mailInstance.getBody(), false);
            }        
        } catch (MessagingException ex) {
            java.util.logging.Logger.getLogger(MailSenderService.class.getName()).log(Level.SEVERE, null, ex);
        }
        sender.send(message);
    }
    
    public void sendMail(){
                
        if (!isValid()) return; 
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        
        try 
        {
            helper.setFrom(DEFAULT_FROM);

            // add destinations
            if (!CollectionUtils.isEmpty(mailInstance.getTo()))
                helper.setTo(mailInstance.getTo().toArray(new String[mailInstance.getTo().size()]));
            if (!CollectionUtils.isEmpty(mailInstance.getCc()))
                helper.setCc(mailInstance.getCc().toArray(new String[mailInstance.getCc().size()]));
            if (!CollectionUtils.isEmpty(mailInstance.getBcc()))
                helper.setBcc(mailInstance.getBcc().toArray(new String[mailInstance.getBcc().size()]));

            // add subject and body   
            if (StringUtils.isNotBlank(mailInstance.getSubject())){
                helper.setSubject(mailInstance.getSubject());
            }else
                helper.setSubject(setMailTo());

            if (mailInstance.isAsHtml()){
                transformLineSeparatorToHTML();
                helper.setText(mailInstance.getBody(), true);
            }else{
                helper.setText(mailInstance.getBody(), false);
            }        
        } catch (MessagingException ex) {
            java.util.logging.Logger.getLogger(MailSenderService.class.getName()).log(Level.SEVERE, null, ex);
        }
        sender.send(message);
    }
    
    private String setMailTo() {
    	return environment.equals("qa") ? DEFAULT_SUBJECT + " QA ENVIRONMENT" : ""; 
    }
    
    private boolean isValid(){
        boolean isValid = true;
        if (CollectionUtils.isEmpty(mailInstance.getTo())
                && CollectionUtils.isEmpty(mailInstance.getCc())
                && CollectionUtils.isEmpty(mailInstance.getBcc())){
            LOGGER.warn("Email has 0 destinations, will be ignored.");
            isValid = false;
        }
        
        if (StringUtils.isBlank(mailInstance.getBody())){
            LOGGER.warn("Email body is empty or null, will be ignored.");
            isValid = false;
        }
        return isValid;
    }
    
    private void transformLineSeparatorToHTML(){
        // Replace Windows and UNIX Line Separator
        String tranformedBody = HtmlUtils.transformLineSeparatorToHTML(mailInstance.getBody());
        mailInstance.setBody(tranformedBody);
    }
    
    public EmailInstance getMailInstance() {
        return mailInstance;
    }        

    public void setMailInstance(EmailInstance mailInstance) {
        this.mailInstance = mailInstance;
    }

    @Override
    public String toString() {
        return "MailSenderService{" + "mailInstance=" + mailInstance + '}';
    }
    
    
    
}
