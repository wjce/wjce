package com.wjc.server.algorithm;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * @author wjc
 * @description 分库规则
 * @date 2020/12/1
 */
public class UserDBPreciseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {

    public UserDBPreciseShardingAlgorithm() {
    }

    /**
     *
     */
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {

        Long value = preciseShardingValue.getValue();
        value = value%3;
        System.out.println(value);
        for (String each : collection) {
            if (each.endsWith(String.valueOf(value))) {
                return each;
            }
        }
        throw new UnsupportedOperationException();

    }
}
