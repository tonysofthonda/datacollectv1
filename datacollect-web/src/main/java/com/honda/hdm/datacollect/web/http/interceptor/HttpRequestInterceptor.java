package com.honda.hdm.datacollect.web.http.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.honda.hdm.datacollect.web.auth.UserPermissionEvaluator;
import com.honda.hdm.datacollect.web.auth.UserPermissionHandler;
import com.honda.hdm.datacollect.web.navigation.NavigationHelper;

@Component
public class HttpRequestInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LogManager.getLogger(HttpRequestInterceptor.class);

    @Autowired
    NavigationHelper navigationHelper;

    @Autowired
    UserPermissionHandler userPermissionHandler;

    @Autowired
    UserPermissionEvaluator userPermissionEvaluator;

    @Override
    public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler, final ModelAndView modelAndView) throws Exception {
        if (modelAndView != null && userPermissionHandler.isPermissionsLoaded()) {
            modelAndView.addObject("menuCategories", navigationHelper.getMenuCategories());
            modelAndView.addObject("userPermissionEvaluator", userPermissionEvaluator);
        }

    }

}
