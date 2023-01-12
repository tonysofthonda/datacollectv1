/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.honda.hdm.datacollect.api;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.ldap.LdapAuthenticationProviderConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.springframework.stereotype.Component;

import com.honda.hdm.datacollect.model.entity.auth.DcLdapGroup;
import com.honda.hdm.datacollect.service.config.CustomCfgProperties;
import com.honda.hdm.datacollect.service.domain.impl.auth.DcLdapGroupService;
//import com.honda.hdm.datacollect.web.auth.handler.LoginSuccessHandler;

/**
 *
 * @author VJC80439
 */
//@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

   // @Autowired
   // private LoginSuccessHandler successHandler;

    @Autowired
    Environment env;

    private static String ldapURL;
    private static String ldapUserDn;
    private static String ldapPassword;
    private static String ldapReferal;
    private static String ldapBase;
    private static String ldapUserSearchFilter;

    public SpringSecurityConfig(CustomCfgProperties customProperties) {
        ldapURL = customProperties.getLdap().getUrl();
        ldapUserDn = customProperties.getLdap().getUserDn();
        ldapPassword = customProperties.getLdap().getPassword();
        ldapReferal = customProperties.getLdap().getReferral();
        ldapBase = customProperties.getLdap().getBase();
        ldapUserSearchFilter = customProperties.getLdap().getUserSearchFilter();
    }
    
    @Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**/*.{js,html,css,png,woff}");
	}

    @Bean
    protected void filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().disable();

        http.csrf().disable().authorizeHttpRequests()
                .antMatchers("/img/**", "/css/**", "/js/**", "/font/**").permitAll().and()
                .formLogin()
              //  .successHandler(successHandler)
                .loginPage("/login/login").permitAll().and()
                .logout().permitAll();

    }

//    @Configuration
//    protected static class AuthenticationConfiguration extends
//            GlobalAuthenticationConfigurerAdapter {

        @Autowired
        CustomLdapAuthoritiesPopulator ldapAuthoritiesPopulator;

        @Override
        public void configure(AuthenticationManagerBuilder auth) throws Exception {

            DefaultSpringSecurityContextSource contextSource = new DefaultSpringSecurityContextSource(ldapURL);
            contextSource.setUserDn(ldapUserDn);
            contextSource.setPassword(ldapPassword);
            contextSource.setReferral(ldapReferal);
            contextSource.setBase(ldapBase);
            contextSource.afterPropertiesSet();

            LdapAuthenticationProviderConfigurer<AuthenticationManagerBuilder> ldapAuthenticationProviderConfigurer = auth.ldapAuthentication();

            ldapAuthenticationProviderConfigurer
                    .userSearchFilter(ldapUserSearchFilter)
                    .rolePrefix("")
                    .ldapAuthoritiesPopulator(ldapAuthoritiesPopulator)
                    .contextSource(contextSource);
        }
    //}

    @Component
    public static class CustomLdapAuthoritiesPopulator implements LdapAuthoritiesPopulator {

        @Autowired
        DcLdapGroupService ldapGroupService;

        @Override
        public Collection<? extends GrantedAuthority> getGrantedAuthorities(DirContextOperations userData, String userName) {
            Collection<GrantedAuthority> authorities = new HashSet<>();

            List<String> memberOf = Arrays.asList(userData.getStringAttributes("memberOf"));
            memberOf.forEach(ldapGroup -> {
                authorities.add(new SimpleGrantedAuthority(new DistinguishedName(ldapGroup).removeLast().getValue()));
            });

            return authorities;
        }
    }

}
