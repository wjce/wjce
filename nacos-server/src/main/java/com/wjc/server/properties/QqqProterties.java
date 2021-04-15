package com.wjc.server.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author wjc
 * @description
 * @date 2020/12/9
 */
@Data
@Configuration
@ConfigurationProperties(prefix="qqq")
public class QqqProterties {
    private String qqq;
}
