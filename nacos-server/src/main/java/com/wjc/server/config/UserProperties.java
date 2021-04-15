package com.wjc.server.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author wjc
 * @description
 * @date 2020/12/9
 */
@Data
@Configuration
@ConfigurationProperties(prefix="user")
public class UserProperties {
    private String name;
    private Integer age;

}
