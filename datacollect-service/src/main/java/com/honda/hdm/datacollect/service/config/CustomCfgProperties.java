/*
 * Honda de Mexico 2018
 * All rights reserved
 */
package com.honda.hdm.datacollect.service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Cesar Martinez <cesar_x_martinez@hdm.honda.com>
 */
@Configuration
@ConfigurationProperties(prefix = "custom")
public class CustomCfgProperties {
    
    // custom.ldap....
    private Ldap ldap;
    // custom.dataCollectConfig....
    private DataCollectConfig dataCollectConfig;    
    
    private Mail mail;

    public Ldap getLdap() {
        return ldap;
    }

    public void setLdap(Ldap ldap) {
        this.ldap = ldap;
    }

    public DataCollectConfig getDataCollectConfig() {
        return dataCollectConfig;
    }

    public void setDataCollectConfig(DataCollectConfig dataCollectConfig) {
        this.dataCollectConfig = dataCollectConfig;
    }
    
        public Mail getMail() {
        return mail;
    }

    public void setMail(Mail mail) {
        this.mail = mail;
    }
 
    public static class Ldap {
        // custom.ldap.url
        private String url;
        // custom.ldap.userDn
        private String userDn;
        private String password;
        private String referral;
        private String base;
        private String userSearchFilter;
        private String[] authorities;
        private String useAuthoritiesForAuthorization;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUserDn() {
            return userDn;
        }

        public void setUserDn(String userDn) {
            this.userDn = userDn;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getReferral() {
            return referral;
        }

        public void setReferral(String referral) {
            this.referral = referral;
        }

        public String getBase() {
            return base;
        }

        public void setBase(String base) {
            this.base = base;
        }

        public String getUserSearchFilter() {
            return userSearchFilter;
        }

        public void setUserSearchFilter(String userSearchFilter) {
            this.userSearchFilter = userSearchFilter;
        }
        
        public String[] getAuthorities() {
            return authorities;
        }

        public void setAuthorities(String[] authorities) {
            this.authorities = authorities;
        }
        
        public String getUseAuthoritiesForAuthorization() {
            return useAuthoritiesForAuthorization;
        }

        public void setUseAuthoritiesForAuthorization(String useAuthoritiesForAuthorization) {
            this.useAuthoritiesForAuthorization = useAuthoritiesForAuthorization;
        }
    }
          
    public static class DataCollectConfig {
        
        private String interfacePathIn;
        private String interfacePathError;
        private String interfacePathDone;

        public String getInterfacePathIn() {
            return interfacePathIn;
        }

        public void setInterfacePathIn(String interfacePathIn) {
            this.interfacePathIn = interfacePathIn;
        }
        
        public String getInterfacePathError() {
            return interfacePathError;
        }

        public void setInterfacePathError(String interfacePathError) {
            this.interfacePathError = interfacePathError;
        }

        public String getInterfacePathDone() {
            return interfacePathDone;
        }

        public void setInterfacePathDone(String interfacePathDone) {
            this.interfacePathDone = interfacePathDone;
        }
        
    }
    
    public static class Mail {
        
        private String to;                
        private String cc;        
        private String bcc;

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public String getCc() {
            return cc;
        }

        public void setCc(String cc) {
            this.cc = cc;
        }

        public String getBcc() {
            return bcc;
        }

        public void setBcc(String bcc) {
            this.bcc = bcc;
        }
    }    
}