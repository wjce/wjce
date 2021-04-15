//package com.orarcle.server.config;
//
//import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
//import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
//import org.apache.commons.dbcp.BasicDataSource;
//import org.apache.commons.io.FileUtils;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.shardingsphere.api.config.sharding.ShardingRuleConfiguration;
//import org.apache.shardingsphere.api.config.sharding.TableRuleConfiguration;
//import org.apache.shardingsphere.api.config.sharding.strategy.InlineShardingStrategyConfiguration;
//import org.apache.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
//import org.apache.shardingsphere.shardingjdbc.api.yaml.YamlShardingDataSourceFactory;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.util.ResourceUtils;
//
//import javax.sql.DataSource;
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Properties;
//
///**
// * @author wjc
// * @description
// * @date 2020/11/12
// */
//@Configuration
//@MapperScan(basePackages = "com.orarcle.server.dao", sqlSessionFactoryRef = "shardingSqlSessionFactory")
//public class ShardingDataSourceConfig {
//
//    @Primary
//    @Bean("shardingDataSource")
//    public DataSource dataSource(){
//        try {
//            DataSource dataSource = YamlShardingDataSourceFactory.createDataSource(ResourceUtils.getFile("classpath:sharding.yml"));
//            return dataSource;
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return null;
////        Map<String, DataSource> dataSourceMap = new HashMap<>();
////
////        // 配置第一个数据源
////        BasicDataSource dataSource1 = new BasicDataSource();
////        dataSource1.setDriverClassName("com.mysql.cj.jdbc.Driver");
////        dataSource1.setUrl("jdbc:mysql://localhost:3306/ds0?serverTimezone=Asia/Shanghai&useSSL=false&useUnicode=true&zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=utf-8");
////        dataSource1.setUsername("root");
////        dataSource1.setPassword("123");
////        dataSourceMap.put("ds0", dataSource1);
////
////        // 配置第二个数据源
////        BasicDataSource dataSource2 = new BasicDataSource();
////        dataSource2.setDriverClassName("com.mysql.cj.jdbc.Driver");
////        dataSource2.setUrl("jdbc:mysql://localhost:3306/ds1?serverTimezone=Asia/Shanghai&useSSL=false&useUnicode=true&zeroDateTimeBehavior=convertToNull&autoReconnect=true&characterEncoding=utf-8");
////        dataSource2.setUsername("root");
////        dataSource2.setPassword("123");
////        dataSourceMap.put("ds1", dataSource2);
////
////        // 配置Order表规则
////        TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration("t_order","ds${0..1}.t_order${0..1}");
////
////        // 配置分库 + 分表策略
////        orderTableRuleConfig.setDatabaseShardingStrategyConfig(new InlineShardingStrategyConfiguration("user_id", "ds${user_id % 2}"));
////        orderTableRuleConfig.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("id", "t_order${id % 2}"));
////
////        // 配置分片规则
////        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
////        shardingRuleConfig.getTableRuleConfigs().add(orderTableRuleConfig);
////
////        // 省略配置order_item表规则...
////        // ...
////
////        // 获取数据源对象
////        try {
////            DataSource dataSource = ShardingDataSourceFactory.createDataSource(dataSourceMap, shardingRuleConfig, new Properties());
////            return dataSource;
////        }catch (SQLException e){
////            e.printStackTrace();
////        }
////        return null;
//    }
//
//    @Primary
//    @Bean
//    public JdbcTemplate jdbcTemplate(){
//        return new JdbcTemplate(dataSource());
//    }
//
//    @Primary
//    @Bean("shardingSqlSessionFactory")
//    public SqlSessionFactory sqlSessionFactory(@Qualifier("shardingDataSource") DataSource datasource, MybatisPlusProperties mybatisPlusProperties)
//            throws Exception {
//        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
//        bean.setDataSource(datasource);
//        bean.setConfiguration(mybatisPlusProperties.getConfiguration());
//        bean.setMapperLocations(mybatisPlusProperties.resolveMapperLocations());
//        bean.setTypeAliasesPackage(mybatisPlusProperties.getTypeAliasesPackage());
//        bean.setMapperLocations(
//                new PathMatchingResourcePatternResolver().getResources("classpath*:mybatis/*/*Mapper.xml"));
//        return bean.getObject();
//    }
//
//    @Primary
//    @Bean("shardingSqlSessionTemplate")
//    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("shardingSqlSessionFactory") SqlSessionFactory sessionfactory) {
//        return new SqlSessionTemplate(sessionfactory);
//    }
//}
