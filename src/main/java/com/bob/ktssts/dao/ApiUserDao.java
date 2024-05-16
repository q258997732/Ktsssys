package com.bob.ktssts.dao;

import com.bob.ktssts.domain.ApiUser;
import org.apache.ibatis.annotations.Param;

public interface ApiUserDao {

//	public String getAuthenticate(@Param("userName") String username, @Param("pass") String password);

	public ApiUser getUser(@Param("userName") String username, @Param("pass") String password);

}
