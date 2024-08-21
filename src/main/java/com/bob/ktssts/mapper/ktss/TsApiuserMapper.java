package com.bob.ktssts.mapper.ktss;

import com.bob.ktssts.entity.ktss.TsApiuser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TsApiuserMapper {

    int deleteByPrimaryKey(String id);

    int insert(TsApiuser record);

    int insertSelective(TsApiuser record);

    TsApiuser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TsApiuser record);

    int updateByPrimaryKey(TsApiuser record);

    TsApiuser verifyUser(String user, String pass);

}
