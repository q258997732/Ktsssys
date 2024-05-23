package com.bob.ktssts.mapper;

import com.bob.ktssts.domain.ApiUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ApiUserMapper {

	ApiUser getUser(@Param("user")String user, @Param("pass") String pass);

	int deleteByPrimaryKey(String id);

	int insert(ApiUser record);

	int insertSelective(ApiUser record);

	ApiUser selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(ApiUser record);

	int updateByPrimaryKey(ApiUser record);

	@Select("SELECT permission from restful.ktssts_user where user = #{username}")
	String getPermissionByUserString(String username);

	@Select("SELECT role from restful.ktssts_user where user = #{username}")
	String getRoleByUserString(String username);

	ApiUser getUserByUsername(String username);

}
