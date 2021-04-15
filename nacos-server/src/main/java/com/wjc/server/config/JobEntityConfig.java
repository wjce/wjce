package com.wjc.server.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wjc
 * @description
 * @date 2020/10/23
 */
@Data
@Component
@ConfigurationProperties(prefix = "elastic-job")
public class JobEntityConfig {
    private List<JobEntity> jobs = new ArrayList<>();
}
