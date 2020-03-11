package com.zgw.shardingjdbc.shardingjdbc;


import com.zgw.shardingjdbc.shardingjdbc.service.UserService;
import com.zgw.shardingjdbc.shardingjdbc.sharding.entity.UserInfo;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan(basePackages = "com.zgw.shardingjdbc.shardingjdbc.sharding.mapper")
public class ShardingJdbcApplicationTests {

  @Resource
  UserService userService;

  @Test
  public void select(){
    UserInfo userInfo1= userService.getUserInfoByUserId(1L);
    System.out.println("------userInfo1:"+userInfo1);

    UserInfo userInfo2= userService.getUserInfoByUserId(2L);
    System.out.println("------userInfo2:"+userInfo2);
  }

  @Test
  public void insert(){
    userService.insert();
  }

}
