package com.bob.ktssts.mapper.cz;


import com.bob.ktssts.entity.cz.StampMapping;

/**
* @author Huang
* @description 针对表【stamp_mapping】的数据库操作Mapper
* @createDate 2024-08-21 17:11:05
* @Entity .entity.com.bob.ktssts.entity.cz.StampMapping
*/
public interface StampMappingMapper {

    int deleteByPrimaryKey(Long id);

    int insert(StampMapping record);

    int insertSelective(StampMapping record);

    StampMapping selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StampMapping record);

    int updateByPrimaryKey(StampMapping record);

}
