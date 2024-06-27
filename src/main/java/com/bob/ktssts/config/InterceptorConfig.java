package com.bob.ktssts.config;

import com.bob.ktssts.jwt.JWTInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new JWTInterceptor())
				.addPathPatterns("/**")//全部路径
				.excludePathPatterns("/api/login")// 登陆不需要token认证
				.excludePathPatterns("/api/test");
	}
}

