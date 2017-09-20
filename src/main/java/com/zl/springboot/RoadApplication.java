package com.zl.springboot;

import com.zl.springboot.config.RoteConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@EnableConfigurationProperties({RoteConfig.class})
@ServletComponentScan
@MapperScan(basePackages = {"com.zl.springboot.security.dao"})
public class RoadApplication {
    public static void main(String[] args) {
        SpringApplication.run(RoadApplication.class, args);
    }
}
