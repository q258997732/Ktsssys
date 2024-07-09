package com.bob.ktssts.service;

import org.springframework.stereotype.Service;

@Service
public interface ApiUserService {
	public String login(String user, String pass);
}
