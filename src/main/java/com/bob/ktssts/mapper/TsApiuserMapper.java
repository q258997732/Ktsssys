package com.bob.ktssts.mapper;

import com.bob.ktssts.entity.TsApiuser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TsApiuserMapper {

    int deleteByPrimaryKey(String id);

    int insert(TsApiuser record);

    int insertSelective(TsApiuser record);

    TsApiuser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TsApiuser record);

    int updateByPrimaryKey(TsApiuser record);

}
