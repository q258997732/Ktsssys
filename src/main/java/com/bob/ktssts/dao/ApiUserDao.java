package com.bob.ktssts.dao;

import com.bob.ktssts.domain.ApiUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ApiUserDao {

	ApiUser getUser(@Param("user")String user, @Param("pass") String pass);

	int insert(ApiUser record);

	int insertSelective(ApiUser record);

	ApiUser selectByPrimaryKey(@Param("id") String id);

	int updateByPrimaryKeySelective(ApiUser record);

	int updateByPrimaryKey(ApiUser record);

}
