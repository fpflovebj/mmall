package com.zl.springboot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;


/**
 * 读取 通用配置
 */
@Configuration
public class CommonConfig implements EnvironmentAware{
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${spring.application.name:1}")
    private String appName;
    @Value("${server.port:8080}")
    private String port;

    @PostConstruct
    public void init(){
        logger.info("method1:spring.application.name-"+this.appName);
        logger.info("method1:server.port-"+this.port);
    }

    @Override
    public void setEnvironment(Environment environment) {
        logger.info("method2:"+environment.getProperty("spring.application.name"));
        logger.info("method2:"+environment.getProperty("server.port"));
    }
}
