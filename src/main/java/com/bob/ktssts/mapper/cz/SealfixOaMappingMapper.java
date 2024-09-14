package com.bob.ktssts.mapper.cz;

import com.bob.ktssts.entity.cz.SealfixOaMapping;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
* @author Huang
* @description 针对表【sealfix_oa_mapping】的数据库操作Mapper
* @createDate 2024-08-22 16:04:38
* @Entity com.bob.ktssts.entity.cz.SealfixOaMapping
*/
public interface SealfixOaMappingMapper {

    int deleteByPrimaryKey(String id);

    int insert(SealfixOaMapping record);

    int insertSelective(SealfixOaMapping record);

    SealfixOaMapping selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SealfixOaMapping record);

    int updateByPrimaryKey(SealfixOaMapping record);

    // 根据tradeno获取
    List<SealfixOaMapping> selectByTradeNo(String tradeNo);

}
