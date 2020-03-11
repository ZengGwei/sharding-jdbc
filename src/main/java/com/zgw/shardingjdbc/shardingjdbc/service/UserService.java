package com.zgw.shardingjdbc.shardingjdbc.service;

import com.zgw.shardingjdbc.shardingjdbc.sharding.entity.UserInfo;
import com.zgw.shardingjdbc.shardingjdbc.sharding.mapper.UserInfoMapper;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Resource
  UserInfoMapper userInfoMapper;

  public  static  Long userId =1L;

  public void insert(){
    for (int i=1;i<=100;i++){
      UserInfo userInfo = new UserInfo();
      userInfo.setUserId(userId);
      userInfo.setAccount("account" + i);
      userInfo.setPassword("password" + i);
      userInfo.setUserName("name" + i);
      userId++;
      userInfoMapper.insert(userInfo);
    }
  }
  public UserInfo getUserInfoByUserId(Long id){
    return userInfoMapper.selectByPrimaryKey(id);
  }

  public List<UserInfo> selectByRange(Long firstId, Long lastId){
    return userInfoMapper.selectByRange(firstId, lastId);
  }


}
