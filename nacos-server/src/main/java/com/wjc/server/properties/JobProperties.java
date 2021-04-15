package com.wjc.server.properties;

import com.wjc.server.entity.JobEntity;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wjc
 * @description
 * @date 2020/12/9
 */
@Data
@Configuration
@ConfigurationProperties(prefix="elastic-jobs")
public class JobProperties {
    private List<JobEntity> jobs = new ArrayList<>();
}
