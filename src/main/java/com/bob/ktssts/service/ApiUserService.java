package com.bob.ktssts.service;

public interface ApiUserService {

	String getToken(String username, String password);

	String getUserById(String id);

}
