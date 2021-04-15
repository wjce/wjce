package com.wjc.server.config;

import org.apache.shardingsphere.elasticjob.api.ElasticJob;
import org.apache.shardingsphere.elasticjob.api.JobConfiguration;
import org.apache.shardingsphere.elasticjob.lite.api.bootstrap.impl.ScheduleJobBootstrap;
import org.apache.shardingsphere.elasticjob.reg.base.CoordinatorRegistryCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wjc
 * @description
 * @date 2020/10/23
 */
@Configuration
public class ElasticJobConfig {

    @Autowired
    private CoordinatorRegistryCenter regCenter;
    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private JobEntityConfig jobEntityConfig;

    @PostConstruct
    public void executor(){
        List<JobConfiguration> list = getJobConfiguration();
        list.forEach(job -> new ScheduleJobBootstrap(regCenter, (ElasticJob)applicationContext.getBean(job.getJobName()), job).schedule());
    }

    public List<JobConfiguration> getJobConfiguration(){
        List<JobEntity> jobEntityList = jobEntityConfig.getJobs();
        List<JobConfiguration> list = new ArrayList<>(jobEntityList.size());
        jobEntityList.forEach(jobEntity -> list.add(JobConfiguration.newBuilder(jobEntity.getJobName(), jobEntity.getShardingTotalCount()).cron(jobEntity.getCron()).description(jobEntity.getDescription()).shardingItemParameters(jobEntity.getShardingItemParameters()).build()));
        return list;
    }
}
