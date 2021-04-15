package com.wjc.server.config;

import lombok.Data;

/**
 * @author wjc
 * @description
 * @date 2020/10/23
 */
@Data
public class JobEntity {

    /**
     * 任务名称
     */
    private String jobName;
    /**
     * 分片数量
     */
    private int shardingTotalCount = 1;
    /**
     * cron表达式
     */
    private String cron;
    /**
     * 当前分片参数
     */
    private String shardingItemParameters;
    /**
     * 当前任务参数
     */
    private String jobParameter;
    /**
     * 任务类型 0.SimpleJob 1.DataflowJob 2.ScriptJob
     */
    private Integer type = 0;
    /**
     * 任务说明
     */
    private String description;

}
