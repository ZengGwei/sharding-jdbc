package com.zgw.shardingjdbc.shardingjdbc.sharding.mapper;

import com.zgw.shardingjdbc.shardingjdbc.sharding.entity.Config;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ConfigMapper {
    int deleteByPrimaryKey(Integer configId);

    int insert(Config record);

    int insertSelective(Config record);

    Config selectByPrimaryKey(Integer configId);

    int updateByPrimaryKeySelective(Config record);

    int updateByPrimaryKey(Config record);
}