package com.wjc.server.algorithm;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * @author wjc
 * @description 分表规则
 * @date 2020/12/1
 */
public class UserTablePreciseShardingAlgorithm implements PreciseShardingAlgorithm<Long> {

    public UserTablePreciseShardingAlgorithm() {
    }

    /**
     * ds0.t_user0, ds0.t_user1
     * ds1.t_user2, ds1.t_user3
     * ds2.t_user4, ds2.t_user5
     */
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {

        Long value = preciseShardingValue.getValue();  //value 为代码中 id 的真实数据，比如 插入，id 为7,则 value 为7
        int length = collection.size();
        value = value%length;
        System.out.println(value);

        for (String each : collection) { //each 为t_manager_0  t_manager_1 配置的正式的表名
            int i = Integer.parseInt(each.substring(each.length()-1));
            if (value == i%length) {
                return each;
            }
        }
        throw new UnsupportedOperationException();

    }

}
