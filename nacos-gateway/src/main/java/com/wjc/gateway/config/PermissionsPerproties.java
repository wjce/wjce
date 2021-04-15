package com.wjc.gateway.config;

import com.wjc.gateway.factory.YamlPropertySourceFactory;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: wjc
 * @createDate: 2020/9/9 13:44
 * @description:
 */
@Data
@Component
@PropertySource(factory = YamlPropertySourceFactory.class,value= {"classpath:permission.yml"})
@ConfigurationProperties(prefix = "permissions", ignoreUnknownFields = false)
public class PermissionsPerproties {

    private List<String> ignoreUrls;
}
