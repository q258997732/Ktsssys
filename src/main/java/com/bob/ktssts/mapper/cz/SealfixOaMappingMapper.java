package com.bob.ktssts.mapper.cz;

import com.bob.ktssts.entity.cz.SealfixOaMapping;

/**
* @author Huang
* @description 针对表【sealfix_oa_mapping】的数据库操作Mapper
* @createDate 2024-08-22 16:04:38
* @Entity com.bob.ktssts.entity.cz.SealfixOaMapping
*/
public interface SealfixOaMappingMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SealfixOaMapping record);

    int insertSelective(SealfixOaMapping record);

    SealfixOaMapping selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SealfixOaMapping record);

    int updateByPrimaryKey(SealfixOaMapping record);

}
