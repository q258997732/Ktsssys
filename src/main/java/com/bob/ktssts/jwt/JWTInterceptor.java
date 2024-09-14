package com.bob.ktssts.jwt;


import com.bob.ktssts.exception.UnauthorizedException;
import com.bob.ktssts.util.TokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JWTInterceptor implements HandlerInterceptor
{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		//从请求头内获取token
		String token = request.getHeader("authorization");
		//验证令牌，如果令牌不正确会出现异常会被全局异常处理
		if(token == null || token.isEmpty()){
			throw new UnauthorizedException("token为空");
		}
		return TokenUtil.VerifyJWTToken(token);
	}

}
