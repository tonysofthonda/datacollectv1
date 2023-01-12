/*
 * Honda de Mexico 2018
 * All rights reserved
 */
package com.honda.hdm.datacollect.api;

import com.honda.hdm.datacollect.repository.base.BaseRepositoryImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 *
 * @author Cesar Martinez <cesar_x_martinez@hdm.honda.com>
 *
 */
@EntityScan("com.honda.hdm")
@ComponentScan("com.honda.hdm")
@EnableJpaRepositories(basePackages = {"com.honda.hdm"}, repositoryBaseClass = BaseRepositoryImpl.class)
@SpringBootApplication
public class DataCollectApiApplication extends SpringBootServletInitializer {

    private static final Logger LOGGER = LogManager.getLogger(DataCollectApiApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DataCollectApiApplication.class, args);
    }

}
