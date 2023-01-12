/*
 * Honda de Mexico 2018
 * All rights reserved
 */
package com.honda.hdm.datacollect.model.entity.comm;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Cesar Martinez <cesar_x_martinez@hdm.honda.com>
 */
@Component
public class EmailInstance {
    
    private List<String> to;
    private List<String> cc;
    private List<String> bcc;
    private String subject;
    private String body;
    private boolean asHtml = true;

    public EmailInstance() {
    }

    public List<String> getTo() {
        if (to==null)
            to = new ArrayList<>();
        return to;
    }

    public void setTo(List<String> to) {
        this.to = to;
    }

    public List<String> getCc() {
        if (cc==null)
            cc = new ArrayList<>();
        return cc;
    }

    public void setCc(List<String> cc) {
        this.cc = cc;
    }

    public List<String> getBcc() {
        if (bcc==null)
            bcc = new ArrayList<>();
        return bcc;
    }

    public void setBcc(List<String> bcc) {
        this.bcc = bcc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isAsHtml() {
        return asHtml;
    }

    public void setAsHtml(boolean asHtml) {
        this.asHtml = asHtml;
    }

    @Override
    public String toString() {
        return "EmailInstance{" + "to=" + to + ", cc=" + cc + ", bcc=" + bcc + ", subject=" + subject + ", body=" + body + ", asHtml=" + asHtml + '}';
    }
    
    
}