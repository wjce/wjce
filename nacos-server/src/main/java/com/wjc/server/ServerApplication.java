package com.wjc.server;

import com.wjc.server.config.UserProperties;
import com.wjc.server.properties.JobProperties;
import com.wjc.server.properties.QqqProterties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author wjc
 * @description
 * @date 2020/10/5
 */

@EnableConfigurationProperties({JobProperties.class, UserProperties.class, QqqProterties.class})
@EnableTransactionManagement
@MapperScan(basePackages = {"com.wjc.server.mapper"})
@ComponentScan(basePackages = {"com.wjc.server.*"})
@EnableDiscoveryClient
@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ServerApplication.class);
        String projectName = applicationContext.getEnvironment().getProperty("project.name");
        String userName = applicationContext.getEnvironment().getProperty("user.name");
        String userAge = applicationContext.getEnvironment().getProperty("user.age");
        System.err.println("project name :" + projectName + "userName: " +userName+"; age: "+userAge);
        System.err.println(applicationContext.getEnvironment().getProperty("spring.datasource.username"));
    }


}
