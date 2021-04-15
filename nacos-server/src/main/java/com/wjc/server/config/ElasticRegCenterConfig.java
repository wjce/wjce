package com.wjc.server.config;

import org.apache.shardingsphere.elasticjob.reg.base.CoordinatorRegistryCenter;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperConfiguration;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wjc
 * @description
 * @date 2020/10/23
 */
@Configuration
public class ElasticRegCenterConfig {
    @Bean(initMethod = "init")
    public CoordinatorRegistryCenter regCenter(
            @Value("${elasticJob.regCenter.serverLists}") final String serverList,
            @Value("${elasticJob.regCenter.namespace}") final String namespace) {
        return new ZookeeperRegistryCenter(new ZookeeperConfiguration(serverList, namespace));
    }
}
