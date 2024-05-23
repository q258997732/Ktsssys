package com.bob.ktssts.service;

import com.bob.ktssts.domain.ApiUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ApiUserRealm extends AuthorizingRealm {

	@Autowired
	ApiUserService apiUserService;

	/* 检测用户权限使用 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		/* 初始化变量 */
		String username = (String) principalCollection.getPrimaryPrincipal();
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		// 测试输出
		System.out.print("username: " + (String) principalCollection.getPrimaryPrincipal());
		System.out.print("principalCollection: " + principalCollection);
		// 通过username获取用户
		ApiUser apiUser = apiUserService.getUserByUsername(username);

		/* 获取用户角色并装入授权中 */
		if (apiUser != null && apiUser.getRole() != null)
			simpleAuthorizationInfo.addRole(apiUser.getRole());

		/* 获取用户权限并装入授权中 */
		if (apiUser != null && apiUser.getPermission() != null && !apiUser.getPermission().isEmpty()) {
			Set<String> permission = new HashSet<String>(Arrays.asList(apiUser.getPermission().split(",")));
			simpleAuthorizationInfo.addStringPermissions(permission);
		}
		return simpleAuthorizationInfo;
	}

	/* 验证账号密码是否正确 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		/* 提取token */
		String tokenStr = (String) authenticationToken.getCredentials();
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			JsonNode jsonNode = objectMapper.readTree(tokenStr);
			String token = jsonNode.get("user").textValue();
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}

		/* 验证token是否合法 */
		ApiUser apiUser = apiUserService.getUserByUsername("");


		return null;
	}
}
