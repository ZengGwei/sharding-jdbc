package com.zgw.shardingjdbc.shardingjdbc.jdbctest;

import io.shardingsphere.api.config.rule.ShardingRuleConfiguration;
import io.shardingsphere.api.config.rule.TableRuleConfiguration;
import io.shardingsphere.api.config.strategy.InlineShardingStrategyConfiguration;
import io.shardingsphere.shardingjdbc.api.ShardingDataSourceFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;


public class ShardJdbcTest {

  public static void main(String[] args) throws SQLException {
      Map<String, DataSource> dataSourceMap = new HashMap<>();

      BasicDataSource dataSource1 = new BasicDataSource();
      dataSource1.setDriverClassName("com.mysql.jdbc.Driver");
      dataSource1.setUrl("jdbc:mysql://localhost:3306/shard0");
      dataSource1.setUsername("root");
      dataSource1.setPassword("1234");
      dataSourceMap.put("ds0",dataSource1);

      BasicDataSource dataSource2 = new BasicDataSource();
      dataSource2.setDriverClassName("com.mysql.jdbc.Driver");
      dataSource2.setUrl("jdbc:mysql://localhost:3306/shard1");
      dataSource2.setUsername("root");
      dataSource2.setPassword("1234");
      dataSourceMap.put("ds1",dataSource2);

    TableRuleConfiguration orderTableRuleConfig = new TableRuleConfiguration();
    orderTableRuleConfig.setLogicTable("t_order");
    orderTableRuleConfig.setActualDataNodes("ds${0..1}.order${0..1}");

    //配置分库分表 策略

    orderTableRuleConfig.setDatabaseShardingStrategyConfig(
        new InlineShardingStrategyConfiguration("order_id","ds${order_id % 2}"));
    orderTableRuleConfig.setTableShardingStrategyConfig(new InlineShardingStrategyConfiguration("order_id","ds${order_id % 2}"));

    //配置分片规则
    ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
    shardingRuleConfig.getTableRuleConfigs().add(orderTableRuleConfig);

    Map<String,Object> map=new HashMap<>();

    DataSource dataSource = ShardingDataSourceFactory
        .createDataSource(dataSourceMap, shardingRuleConfig, map, new Properties());
//    String sql = "SELECT * FROM `order` WHERE  user_id=?";
    String sql = "INSERT INTO `order` (`order_id`, `user_id`, `create_time`, `total_price`) VALUES (?, ?, now(), ?);";

   try( Connection connection = dataSource.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(sql)){
      preparedStatement.setInt(1, 22);
      preparedStatement.setInt(2, 22);
      preparedStatement.setInt(3, 22);
      System.out.println();
      try (ResultSet rs = preparedStatement.executeQuery()) {
        while (rs.next()) {
//          System.out.println("order_id---------" + rs.getInt(1));
//          System.out.println("user_id---------" + rs.getInt(2));
//          System.out.println("create_time---------" + rs.getTime(3));
//          System.out.println("total_price---------" + rs.getInt(4));
        }
      }
    }



  }

}
