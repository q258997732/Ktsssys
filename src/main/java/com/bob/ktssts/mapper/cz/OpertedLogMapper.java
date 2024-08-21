package com.bob.ktssts.mapper.cz;

import com.bob.ktssts.entity.cz.OpertedLog;

/**
* @author Huang
* @description 针对表【operted_log】的数据库操作Mapper
* @createDate 2024-08-21 15:35:31
* @Entity com.bob.ktssts.entity.cz.OpertedLog
*/
public interface OpertedLogMapper {

    int deleteByPrimaryKey(Long id);

    int insert(OpertedLog record);

    int insertSelective(OpertedLog record);

    OpertedLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OpertedLog record);

    int updateByPrimaryKey(OpertedLog record);

}
