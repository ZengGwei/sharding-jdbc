package com.zgw.shardingjdbc.shardingjdbc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.zgw.shardingjdbc.shardingjdbc.sharding.mapper")
public class ShardingJdbcApplication {

  public static void main(String[] args) {
    SpringApplication.run(ShardingJdbcApplication.class, args);
  }

}
