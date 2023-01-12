package com.honda.hdm.datacollect.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.honda.hdm.datacollect.web.http.interceptor.HttpRequestInterceptor;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter{
	
	@Autowired
	HttpRequestInterceptor requestInterceptor;	
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestInterceptor).addPathPatterns("/**");
    }
}
