package com.zgw.shardingjdbc.shardingjdbc.sharding.mapper;

import com.zgw.shardingjdbc.shardingjdbc.sharding.entity.UserInfo;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface UserInfoMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    List<UserInfo> selectByRange(@Param("firstId")Long firstId,@Param("lastId") Long lastId);
}